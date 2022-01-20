import java.util.ArrayList;

public class ProcessControlBlock
{
    private final int pid;
    private ProcessState state;
    // the following two ArrayLists should record when the process starts/stops
    // for statistical purposes
    private ArrayList<Integer> startTimes; // when the process starts running
    private ArrayList<Integer> stopTimes;  // when the process stops running

    private static int pidTotal= 0;

    // Current total runtime of the process.
    private int currentTotalTimeRun;

    public ProcessControlBlock()
    {
        this.state = ProcessState.NEW;
        this.startTimes = new ArrayList<Integer>();
        this.stopTimes = new ArrayList<Integer>();

        //increasing the number of processes arrived by incrementing pidtotal
        pidTotal++;

        //the unique pid for every process is basically the order of its arrival
        this.pid = pidTotal;

        currentTotalTimeRun =0;
    }

    public ProcessState getState() {
        return this.state;
    }

    public void setState(ProcessState state, int currentClockTime)
    {
        /*Setting the state of the process from NEW to READY */

        if ((this.state.equals(ProcessState.NEW)) && (state.equals(ProcessState.READY)))
        {
            this.state= ProcessState.READY;
        }

        /*Setting the state of the process from READY to RUNNING.The current time
        of the clock is added to the Arraylist "startTimes", which stores the times
        that the certain process started running.*/

        if ((this.state.equals(ProcessState.READY)) && (state.equals(ProcessState.RUNNING)))
        {
            this.state= ProcessState.RUNNING;
            this.startTimes.add(currentClockTime);
        }

        /*Setting the state of the process from RUNNING to READY.The current time
        of the clock is added to the Arraylist "stopTimes",which stores the times
        that the process stopped running.*/

        if((this.state.equals(ProcessState.RUNNING)) && (state.equals(ProcessState.READY)))
        {
            this.state= ProcessState.READY;
            this.stopTimes.add(currentClockTime);

        }

        /*Setting the state of the process from RUNNING to TERMINATED.The current time
        of the clock is added to the Arraylist "stopTimes". */

        if((this.state.equals(ProcessState.RUNNING)) && (state.equals(ProcessState.TERMINATED)))
        {
            this.state= ProcessState.TERMINATED;
            this.stopTimes.add(currentClockTime);
        }
    }

    public int getCurrentTotalTimeRun()
    {
        return currentTotalTimeRun;
    }

    public void setCurrentTotalTimeRun(int currentTotalTimeRun)
    {
        this.currentTotalTimeRun = currentTotalTimeRun;
    }

    public int getPid() {
        return this.pid;
    }

    public ArrayList<Integer> getStartTimes() {
        return startTimes;
    }

    public ArrayList<Integer> getStopTimes() {
        return stopTimes;
    }

}