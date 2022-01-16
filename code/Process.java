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

    public void waitInBackground()
    {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */

    }

    public double getWaitingTime()
    {
        /* The waiting time of a process is the total time spent by the process
        waiting for CPU.It is calculated by substracting the burst time
        *of the certain process from its turnaround time*/

        double waitingTime= getTurnAroundTime()-this.burstTime;
        return waitingTime;
    }

    public double getResponseTime()
    {
        /* The response time of a process is the time spent between the arrival
        * of the process and its first execution. It is calculated by substracting
        * the arrival time of the process from the time of its first execution*/

        double responseTime=getPCB().getStartTimes().get(0)-this.arrivalTime;
        return responseTime;
    }


    public double getTurnAroundTime()
    {
        /* The turn around time of a process is the time interval from the time of
         * arrival of the process to the time of the completion of said process. It is
         * calculated by substracting the arrival time of the process from the time that
         * the certain process was completed. Here, turnaround time is by default set to -1,
         * in case the process that we want to calculate its turn around time, is not
         * completed yet, and therefore its state is not equal to TERMINATED.
        */

        double turnAroundTime=-1;
        if (getPCB().getState().equals(ProcessState.TERMINATED))
        {
            turnAroundTime=getPCB().getStopTimes().get(getPCB().getStopTimes().size()-1)-this.arrivalTime;
        }
        return turnAroundTime;
    }
}
