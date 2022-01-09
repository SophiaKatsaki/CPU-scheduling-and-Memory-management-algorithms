public class Process
{
    private ProcessControlBlock pcb;
    private int arrivalTime;
    private int burstTime;
    private int memoryRequirements;
    
    public Process(int arrivalTime, int burstTime, int memoryRequirements)
    {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.memoryRequirements = memoryRequirements;
        this.pcb = new ProcessControlBlock();
    }
    
    public ProcessControlBlock getPCB() {
        return this.pcb;
    }

    public int getMemoryRequirements()
    {
        return this.memoryRequirements;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void run() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process starts running */
        
    }
    
    public void waitInBackground() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */
        
    }

    public double getWaitingTime()
    {
        /* TODO: you need to add some code here
         * and change the return value */
        double waitingTime= getTurnAroundTime()-this.burstTime;
        return waitingTime;
    }

    public double getResponseTime()
    {
        /* TODO: you need to add some code here
         * and change the return value */
        double responseTime=getPCB().getStartTimes().get(0)-this.arrivalTime;
        return responseTime;
    }

    public double getTurnAroundTime()
    {
        /* TODO: you need to add some code here
         * and change the return value */
        double turnAroundTime=-1;
        if (getPCB().getState().equals(ProcessState.TERMINATED))
        {
            turnAroundTime=getPCB().getStopTimes().get(getPCB().getStopTimes().size()-1)-this.arrivalTime;
        }
        return turnAroundTime;
    }
}
