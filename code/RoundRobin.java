public class RoundRobin extends Scheduler {

    private int quantum;

    // Stores the instance of the currently running process.
    private Process runningProcess;

    // Stores the currently running process's, remaining quantum time.
    private int remainingQuantumTime;

    public RoundRobin() {
        this.quantum = 1; // default quantum

        // Default values
        runningProcess = null;
        remainingQuantumTime = 0;
    }
    
    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        // The new process is just added in the end of the queue.
        processes.add(p);
    }
    
    public Process getNextProcess() {
        // If there is a currently running process.
        if (runningProcess != null){
            // The first process in processes queue should always be the (previously) running process.
            // If it is not or the process queue is empty, means that the runningProcess process has been removed by removeProcess
            //  and the running process should be disregarded.
            if (processes.isEmpty() || runningProcess.getPCB().getPid() != processes.get(0).getPCB().getPid()){
                runningProcess = null;
                remainingQuantumTime = quantum;
            }
            // If the running process has finished its burst time, it's halted
            else if (runningProcess.getBurstTime() == runningProcess.getPCB().getCurrentTotalTimeRun()) {
                processes.remove(0); // Remove the running (should be the first in the ready queue) process.
                runningProcess.waitInBackground(); // Terminate old process.

                // Initialize for the next process
                runningProcess = null;
                remainingQuantumTime = quantum;
            }
            // The running process has completely depleted its quantum time. It is moved to the end of the queue and
            // removed from being the current one.
            else if (remainingQuantumTime == 0){
                processes.remove(0); // Remove the running (should be the first in the ready queue) process.
                processes.add(runningProcess); // Placing the currently running process in the end of the queue.

                // Initialize for the next process as if one is not currently running.
                runningProcess = null;
                remainingQuantumTime = quantum;
            }
        }

        // There is a currently running process, and it has not finished its burst time.
        if (runningProcess != null){
            // Assuming the process returned will run exactly for one quantum:
            remainingQuantumTime--; // Updating current quantum.
            incrementTimeRun(runningProcess); // Update currently running process' total time run.
            return runningProcess;
        } else { // No process running, get the next available from the readyQueue.
            if (processes.isEmpty()){ // If the readyQueue is empty, then return null.
                return null;
            } else { // Setting the process next in line as running one and returning it.
                runningProcess = processes.get(0);
                remainingQuantumTime--; // Updating current quantum.
                incrementTimeRun(runningProcess); // Update currently running process' total time run.
                return runningProcess;
            }
        }
    }

    /*
     * Increments the currentTotalTimeRun accumulator of p's PCB by one.
     */
    private void incrementTimeRun(Process p){
        p.getPCB().setCurrentTotalTimeRun( p.getPCB().getCurrentTotalTimeRun() + 1 );
    }
}
