import java.util.ArrayList;

public abstract class Scheduler {

    protected ArrayList<Process> processes; // the list of processes to be executed

    // A flag variable that defines if we should check for a New Process in every Clock Cycle
    // Its value depends on the scheduling algorithm that is being used
    protected boolean checksEveryCycleForNewProcesses;
    
    public Scheduler() {
        this.processes = new ArrayList<Process>();
    }

    /* the addProcess() method should add a new process to the list of
     * processes that are candidates for execution. This will probably
     * differ for different schedulers */
    public abstract void addProcess(Process p);
    
    /* the removeProcess() method should remove a process from the list
     * of processes that are candidates for execution. Common for all
     * schedulers. */
    public void removeProcess(Process p) {
        // Searching the processes list for the process to be removed.
        for(int i = 0; i < processes.size(); i++){
            Process currentProcess = processes.get(i);
            // A process is uniquely identified by its assigned pid.
           if (currentProcess.getPCB().getPid() == p.getPCB().getPid()){
               processes.remove(i);
               // The target process has been removed, stop the search and return.
               break;
           }
        }

        // If it is not found, nothing will happen.
    }
    
    /* the getNextProcess() method should return the process that should
     * be executed next by the CPU */
    public abstract Process getNextProcess();
}
