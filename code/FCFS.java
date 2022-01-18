public class FCFS extends Scheduler {
    private Process currentProcess; // the current running process and its data

    /**
     * The constructor that initializes the variables.
     */

    public FCFS() {
        // Initialize the currentProcess to default null value (we start without a process).
        this.currentProcess = null;
    }

    /**
     * Method that adds the process that is given to the queue in the end.
     *
     * @param p is the newest process that will be added to the queue.
     */

    public void addProcess(Process p) {
        // Newest process added in the end of the queue (ArrayList "processes" of the class Scheduler).
        processes.add(p);
    }

    /**
     * Method that finds the next process that will be run and updates the data wherever is needed.
     *
     * @return the process that will run next (if there exists one).
     */
    
    public Process getNextProcess() {
        // Checking if there is a process that is running now.
        if (!(this.currentProcess == null)) {
            // Checking if the current total time that the current running process has been run is equal to the
            // total burst time of the current running process and if so, then that process will be removed from
            // the queue.
            if (this.currentProcess.getPCB().getCurrentTotalTimeRun() == this.currentProcess.getBurstTime()) {
                removeProcess(this.currentProcess);

                this.currentProcess.waitInBackground();
                this.currentProcess = null; // default value until the next process comes
            }
        }

        // Checking if exist more processes in the queue and gets the first one that has come as the fcfs algorithm
        // indicates. Else there are not any other processes in the queue and the null value is been returned.
        if (!processes.isEmpty()) {
            this.currentProcess = processes.get(0);

            // Updating the time from the current running process by increasing by 1.
            this.currentProcess.getPCB().setCurrentTotalTimeRun(
                    this.currentProcess.getPCB().getCurrentTotalTimeRun() + 1);

            return this.currentProcess;
        } else {
            return null;
        }
    }
}