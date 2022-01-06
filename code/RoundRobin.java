import java.util.ArrayList;

public class RoundRobin extends Scheduler {

    private int quantum;

    // The implementation of the ready process queue. It contains processes that can run (in the READY or RUNNING state).
    private ArrayList<Process> readyQueue;

    // Stores the instance of the currently running process.
    private Process runningProcess;

    // Stores the currently running process's, remaining quantum time.
    private int remainingQuantumTime;

    // Stores the process's total time run since it stated.
    private int totalTimeRunSinceCurrentProcessStart;
    
    public RoundRobin() {
        this.quantum = 1; // default quantum
        readyQueue = new ArrayList<>(); // Ready Queue initialization.

        // Default values
        runningProcess = null;
        remainingQuantumTime = 0;
        totalTimeRunSinceCurrentProcessStart = 0;
    }
    
    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        // The new process is just added in the end of the queue.
        readyQueue.add(p);
    }
    
    public Process getNextProcess() {
        // If there exists a currently running process.
        if (runningProcess != null) {
            // Check if the process has completed its run.
            if (runningProcess.getBurstTime() == runningProcess.getPCB().getCurrentTotalTimeRunBeforeSuspended() + totalTimeRunSinceCurrentProcessStart){
                runningProcess.waitInBackground(); // Terminate old process.

                return getFreshNextProcess();
            }

            // The running process has completely depleted its quantum time. A new process is returned and the old one
            //     is placed in the end of the queue. If there are no remaining processes, null is returned.
            if (remainingQuantumTime == 0) {
                readyQueue.add(runningProcess); // Placing the currently running process in the end of the queue.
                return getFreshNextProcess(); // Getting new process.

            // If the running process still has some quantum time left, it is returned and some of its quantum time
            // is depleted while the time run is calculated.
            } else {
                remainingQuantumTime--; // It is returned, thus it is assumed that it will run for one clock cycle.
                totalTimeRunSinceCurrentProcessStart++; // Taking into account time spent running.
                return runningProcess;
            }
        } else { // The, currently empty, ready queue gets a new process.
            return getFreshNextProcess();
        }

    }

    /*
     * It returns the next process in the ready queue that is not the current process.
     * It also initializes some class fields accordingly.
     */
    private Process getFreshNextProcess(){
        if (!readyQueue.isEmpty()){ // If there are new processes to return, it returns the next in line.
            // Initialize variables for new process
            remainingQuantumTime = quantum;
            totalTimeRunSinceCurrentProcessStart = 0;
            runningProcess = readyQueue.get(0);
            // TODO: Assuming the CPU begins running this process.
            return runningProcess;
        } else { // null returned signifies that no processes are available to be run.
            // Change to default (no process running) values
            totalTimeRunSinceCurrentProcessStart = 0;
            runningProcess = null;
            return null;
        }
    }
}
