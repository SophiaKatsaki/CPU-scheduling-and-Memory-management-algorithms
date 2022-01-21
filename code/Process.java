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

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getMemoryRequirements()
    {
        return this.memoryRequirements;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void run()
    {
        /*There is the need to check if the current state of
        * the process is READY, in order for the process to start
        * running.
        * */

        if (this.pcb.getState().equals(ProcessState.READY))
        {
            /* Every time a process starts running, the state of the process is set
             * to RUNNING.*/

            this.pcb.setState(ProcessState.RUNNING, CPU.clock);
        }


    }

    public void waitInBackground()
    {
        /*Firstly,there is the need to check that the state of the process is
        indeed RUNNING*/

        if (this.pcb.getState().equals(ProcessState.RUNNING))
        {
            /*If the burst time of the process is equal to its total runtime,
            the state of said process is set to TERMINATED.
            */

            if (this.pcb.getCurrentTotalTimeRun()==this.burstTime)
            {
                this.pcb.setState(ProcessState.TERMINATED, CPU.clock);
            }


            /*Else, the state of
            the process is set to READY,once again.*/

            else
            {
                this.pcb.setState(ProcessState.READY, CPU.clock);
            }
        }

    }


    public double getWaitingTime()
    {
     /* The waiting time of a process is the total time spent by the process
     waiting for CPU.It is calculated by subtracting the burst time
     *of the certain process from its turnaround time*/

        double waitingTime= getTurnAroundTime()-this.burstTime;
        return waitingTime;
    }

    public double getResponseTime()
    {
        /* The response time of a process is the time spent between the arrival
        * of the process and its first execution. It is calculated by subtracting
        * the arrival time of the process from the time of its first execution*/

        double responseTime=getPCB().getStartTimes().get(0)-this.arrivalTime;
        return responseTime;
    }


    public double getTurnAroundTime()
    {
        /* The turn around time of a process is the time interval from the time of
        * arrival of the process to the time of the completion of said process. It is
        * calculated by subtracting the arrival time of the process from the time that
        * the certain process was completed. Here, turnaround time is by default set
        * to -1, in case the process that we want to calculate its turn around time,
        * is not completed yet, and therefore its state is not equal to TERMINATED.
        */

        double turnAroundTime=-1;
        if (getPCB().getState().equals(ProcessState.TERMINATED))
        {
            turnAroundTime=getPCB().getStopTimes().get(getPCB().getStopTimes().size()-1)-this.arrivalTime;
        }
        return turnAroundTime;
    }
}
