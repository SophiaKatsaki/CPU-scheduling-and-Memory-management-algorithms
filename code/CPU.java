import java.util.ArrayList;

public class CPU
{
    public static int clock = 0; // this should be incremented on every CPU cycle
    private Scheduler scheduler;
    private MMU mmu;
    private Process[] processes;
    private ArrayList<Process> processesList;
    
    public CPU(Scheduler scheduler, MMU mmu, Process[] processes)
    {
        this.scheduler = scheduler;
        this.mmu = mmu;
        this.processes = processes;
        this.processesList = new ArrayList<>();
    }

    public void run()
    {
        //C.C. = Clock Cycle

        // Inserting the given processes in an Arraylist
        for(Process aProcess : processes){
            /* We keep only the processes that can fit at any of the Available Block Sizes
               If a process can't fit in the memory, then we discard this process
             */
            if(fitsAtMemory(aProcess)) {
                processesList.add(aProcess);
            }
        }

        /* sorting the processes according to their arrival time */
        sortProcesses(processesList);

        Process previousProcess = null; // The executed process of the previous C.C. (in case it doesn't exist, its value is null)
        Process currentProcess = null; // The executed process of the current C.C. (in case it doesn't exist, its value is null)

        if(scheduler.getChecksEveryCycleForNewProcesses()){ //if the CPU requests for a process in every Clock Cycle (C.C.)
            do {

                if(currentProcess != null && currentProcess.getBurstTime() == currentProcess.getPCB().getCurrentTotalTimeRun()){
                    currentProcess.waitInBackground(); // Running --> Terminated
                }

                loadProcessesIntoMemory(); // load all the processes that have come until this C.C.

                currentProcess = scheduler.getNextProcess(); // Request the next process from the scheduler

                if (currentProcess == null){ //if there's no process to be executed, then do no-op
                    if (previousProcess != null){
                        previousProcess = null;
                    }

                    tick();
                    continue; // No Operation
                }

                if (previousProcess==null){ //if there wasn't an executed process before
                    tick(); // 2 C.C. required (Ready ---> Running)
                    tick();
                    currentProcess.run(); //run the current process
                }
                else if (previousProcess.getPCB().getPid() != currentProcess.getPCB().getPid()) { //if the previous executed process is not the same with the current one
                    previousProcess.waitInBackground(); //previous process halts (Ready or Terminated)
                    tick(); // 2 C.C. required
                    tick();
                    currentProcess.run(); //run the current process
                }

                previousProcess = currentProcess; // Update previousProcess's value
                incrementTimeRun(currentProcess); // Update currently running process's total time run.
                tick(); // process was executed for 1 C.C.

            } while (!(currentProcess == null && processesList.isEmpty())); // If there are no processes in readyQueue neither in newQueue, then there's nothing else to do
        } else {
            do {

                if(currentProcess != null && currentProcess.getBurstTime() == currentProcess.getPCB().getCurrentTotalTimeRun()){
                    currentProcess.waitInBackground(); // Running --> Terminated
                }

                loadProcessesIntoMemory(); // load all the processes that have come until this C.C.

                currentProcess = scheduler.getNextProcess(); // Request the next process from the scheduler

                if (previousProcess != null){
                    previousProcess.waitInBackground(); //previous process halts (Ready or Terminated)
                    previousProcess = null;
                }

                if (currentProcess == null) { //if there's no process to be executed, then do no-op
                    tick();
                    continue; // No Operation
                }

                previousProcess = currentProcess; // Update previousProcess's value
                tick(); // 2 C.C. required
                tick();
                currentProcess.run();//run the current process

                while (currentProcess != null && (previousProcess.getPCB().getPid() == currentProcess.getPCB().getPid())) {
                    incrementTimeRun(currentProcess);
                    tick(); // process was executed for 1 C.C.
                    currentProcess = scheduler.getNextProcess(); // Request the next process from the scheduler
                }

            } while (!(currentProcess == null && processesList.isEmpty())); // If there are no processes in readyQueue neither in newQueue, then there's nothing else to do
        }


    }

    /*
     * Increments the currentTotalTimeRun accumulator of p's PCB by one.
     */
    private void incrementTimeRun(Process p){
        p.getPCB().setCurrentTotalTimeRun( p.getPCB().getCurrentTotalTimeRun() + 1 );
    }



    /*
     * This method loads processes into Memory and updates accordingly the system's state
     */
    private void loadProcessesIntoMemory(){
        for (int i = 0; i < processesList.size();){
            Process aProcess = processesList.get(i);

            if(aProcess.getArrivalTime() <= clock){
                if(mmu.loadProcessIntoRAM(aProcess)){
                    processesList.remove(i); // Remove the process from New Queue
                    scheduler.addProcess(aProcess); //Inserting the process to Ready Queue
                    aProcess.getPCB().setState(ProcessState.READY,clock); // Updating the process's state
                    tick(); // 1 c.c. is required
                    continue;
                }
            }
            i++;
        }
    }


    /*
     * This method sorts the various processes using the selection sort
     * Sorting criteria: Arrival Time
     */
    private void sortProcesses(ArrayList<Process> processesList) {

        for (int i = 0; i < processesList.size()-1; i++){
            // Find the minimum element in unsorted array
            int min = i;
            for (int j = i+1; j < processesList.size(); j++) {
                if (processesList.get(j).getArrivalTime() < processesList.get(min).getArrivalTime()) {
                    min = j;
                }
            }
            Process temp = processesList.get(min);
            processesList.set(min,processesList.get(i));
            processesList.set(i,temp);
        }
    }

    /*
     * This method checks if the given process fits at any of the available Block Sizes
     */
    private boolean fitsAtMemory(Process aProcess) {
        int[] availableBlockSizes = mmu.getAvailableBlockSizes();
        for(int i=0; i<availableBlockSizes.length; i++){
            if(aProcess.getMemoryRequirements() <= availableBlockSizes[i]){
                return true;
            }
        }
        return false;
    }

    public void tick()
    {
        clock+=1;
    }
}
