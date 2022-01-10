import java.util.ArrayList;

public class NextFit extends MemoryAllocationAlgorithm
{
    /*this variable stores the place of the last input of a process to the
    memory.It is by default set to -1, because the first time Next Fit
    executes, it behaves exactly like the First Fit algorithm, and therefore
    it starts traversing the available memory slots starting from the first place
    of the arraylist.
    */
    private int last=-1;

    public NextFit(int[] availableBlockSizes)
    {
        super(availableBlockSizes);
    }

    /* This method decides whether a process fits in a block or not.If it fits,
    Next Fit algorithm chooses the first available block that is greater than
    or equal to the size of memory the process requires,after the last block that
    a process was loaded. It can be considered as a rotating First Fit algorithm,beacuse
    they both examine blocks in the same way,but usually with a different start block.
    The address is by default set to -1, in case the process does not fit in any of the
    blocks in the memory slots,and changes its value if the process does fit a block. The
    address then,is set to the start adress of the certain available block.
   */
    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {
        boolean fit = false;
        int address = -1;
        /* a counter that helps us not to execute the algorithm more times than the amount of the
        available blocks. The algorithm exits the loop when all the blocks were checked for memory
        (in worst-case scenario).
        */
        int numberOfExecution=1;
        /* We begin from the next block that is available after the last block that the previous
        process was loaded*/
        int indexOfCurrentSlot=(last+1)%currentlyUsedMemorySlots.size();
        while(numberOfExecution<=currentlyUsedMemorySlots.size())
        {
            int sizeOfAvailableBlockOfSLot = currentlyUsedMemorySlots.get(indexOfCurrentSlot).getEnd() - currentlyUsedMemorySlots.get(indexOfCurrentSlot).getStart();
            if (sizeOfAvailableBlockOfSLot >= p.getMemoryRequirements())
            {
                fit = true;
                address = currentlyUsedMemorySlots.get(indexOfCurrentSlot).getStart();
                //currentlyUsedMemorySlots.get(indexOfCurrentSlot).setStart(currentlyUsedMemorySlots.get(indexOfCurrentSlot).getStart()+p.getMemoryRequirements());
                last = indexOfCurrentSlot;
                break;
            }

            //updating the index before next execution
            indexOfCurrentSlot = (indexOfCurrentSlot + 1) % currentlyUsedMemorySlots.size();

            numberOfExecution++;

        }
        return address;
    }

}
