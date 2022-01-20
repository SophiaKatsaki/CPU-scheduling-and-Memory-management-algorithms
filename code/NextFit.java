import java.util.ArrayList;

public class NextFit extends MemoryAllocationAlgorithm
{
    /*this variable stores the beginning address of the last fit of a process to the
    memory.It is by default set to -1, because the first time Next Fit
    executes, it behaves exactly like the First Fit algorithm, and therefore
    it starts traversing the available memory slots starting from the first place
    of the memory.
    */
    private int last=-1;


    public NextFit(int[] availableBlockSizes)
    {
        super(availableBlockSizes);
    }

    /* This method decides whether a process fits in a slot or not.

       If it fits, Next Fit algorithm chooses the first available slot that is greater than
       or equal to the size of memory the process requires, after the last slot that a process was loaded.

       It can be considered as a rotating First Fit algorithm,beacuse they both examine slots in the same way,but
       usually with a different start slot.
    */

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {

       /*
       The address is by default set to -1, in case the process does not fit in any of the
       slots in the memory slots.
       */

        boolean fit = false;
        int address = -1;

        /* a counter that helps us not to execute the algorithm more times than the amount of the
        available slots. The algorithm exits the loop when all the slots were checked for memory
        (in worst-case scenario).
        */
        int numberOfExecution=1;

        // Get the current available memory state.
        ArrayList<MemorySlot> availableSlots = this.mapAvailableMemory(currentlyUsedMemorySlots);

        /* We begin from the next slot that is available after the last slot that the previous
           process was loaded.
           Search for that slot.
           Initialize to 0, if it isn't found, it is presumed to be after the end, so in the beginning.
         */
        int indexOfCurrentSlot = 0;
        for (int i = 0; i < availableSlots.size(); i++){
            if (availableSlots.get(i).getStart() > last){
                indexOfCurrentSlot = i;
                break;
            }
        }

        while(numberOfExecution<=availableSlots.size())
        {
            int sizeOfAvailableMemoryOfSlot = availableSlots.get(indexOfCurrentSlot).getEnd() - availableSlots.get(indexOfCurrentSlot).getStart() + 1;
            if (sizeOfAvailableMemoryOfSlot >= p.getMemoryRequirements())
            {
                fit = true;

                /*The address is set to the start address of the certain
                available slot.
                */
                address = availableSlots.get(indexOfCurrentSlot).getStart();

                /*
                    In the "last" attribute, the begging address of the last fitted memory slot is stored.
                 */
                last = address;
                break;
            }

            //updating the index before next execution
            indexOfCurrentSlot = (indexOfCurrentSlot + 1) % availableSlots.size();

            //updating the number of times that Next Fit was executed
            numberOfExecution++;

        }
        return address;
    }

}
