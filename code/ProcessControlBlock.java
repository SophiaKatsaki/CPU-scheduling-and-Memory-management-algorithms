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
        /* TODO: you need to add some code here
         * Hint: every process should get a unique PID */
        this.pid = 0; // change this line
        currentTotalTimeRun =0;
    }



    public ProcessState getState() {
        return this.state;
    }
    
    public void setState(ProcessState state, int currentClockTime)
    {
        /* TODO: you need to add some code here
         * Hint: update this.state, but also include currentClockTime
         * in startTimes/stopTimes */

        // Every time this process's execution is halted, the current total running time is computed and stored.
        if (this.state==ProcessState.RUNNING && state!=ProcessState.RUNNING)
        {
            int previousHaltTime = stopTimes.isEmpty()?0:stopTimes.get(stopTimes.size()-1); // Get the timestamp of the previous halt.
            currentClockTime += currentClockTime - previousHaltTime;
        }
    }

    public int getCurrentTotalTimeRun() {
        return currentTotalTimeRun;
    }

    public void setCurrentTotalTimeRun(int currentTotalTimeRun) {
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
