public class SRTF extends Scheduler {

    // Stores the instance of the currently running process.
    private Process runningProcess;

    public SRTF() {
        // Initialization of runningProcess to default null.
        runningProcess = null;
    }

    public void addProcess(Process p) {
        // The new process is just added in the end of the queue.
        processes.add(p);
    }
    
    public Process getNextProcess() {

            // If it has finished, then it is halted
            if (runningProcess != null && runningProcess.getBurstTime() == runningProcess.getPCB().getCurrentTotalTimeRun()) {
                removeProcess(runningProcess); // Remove the running process.
                runningProcess.waitInBackground(); // Terminate old process.
                runningProcess = null; // Initialization for the next process
            }

            if(!processes.isEmpty()) { // if the readyQueue is not empty
                // Finding the process with the Shortest Remaining Time
                int minimumRemainingTime = Integer.MAX_VALUE;
                for (Process aProcess : processes) {
                    int remainingTime = calculateRemainingTime(aProcess);
                    if (remainingTime <= minimumRemainingTime) {
                        minimumRemainingTime = remainingTime;
                        runningProcess = aProcess;
                    }
                }
                incrementTimeRun(runningProcess); // Update currently running process' total time run.
                return runningProcess;
            }
            else { // if readyQueue is empty, then there is no process to be executed
                return null;
            }
    }

    /*
     * Remaining execution time of a process is equal to its Burst Time minus its current total runtime.
     */
    private int calculateRemainingTime(Process aProcess) {
        return aProcess.getBurstTime() - aProcess.getPCB().getCurrentTotalTimeRun();
    }

    /*
     * Increments the currentTotalTimeRun accumulator of p's PCB by one.
     */
    private void incrementTimeRun(Process p){
        p.getPCB().setCurrentTotalTimeRun( p.getPCB().getCurrentTotalTimeRun() + 1 );
    }
}
