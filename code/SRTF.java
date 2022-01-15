public class SRTF extends Scheduler {

    // Stores the instance of the currently running process.
    private Process runningProcess;

    public SRTF() {
        /* TODO: you _may_ need to add some code here */

        // Default values
        runningProcess = null;
    }

    public void addProcess(Process p) {
        /* TODO: you need to add some code here */

        // The new process is just added in the end of the queue.
        processes.add(p);
    }
    
    public Process getNextProcess() {
        /* TODO: you need to add some code here
         * and change the return value */

        if(runningProcess == null){// No process running, get the next available from the readyQueue.
            if (processes.isEmpty()){ // If the readyQueue is empty, then return null.
                return null; //no process to be executed
            }
            else { // Setting the process next in line as running one and returning it.
                runningProcess = processes.get(0);
                incrementTimeRun(runningProcess); // Update currently running process' total time run.
                return runningProcess;
            }
        }
        else {
            // If it has finished, then it is halted
            if (runningProcess.getBurstTime() == runningProcess.getPCB().getCurrentTotalTimeRun()) {
                processes.remove(runningProcess); // Remove the running process.
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
