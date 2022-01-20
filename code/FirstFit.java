import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm
{
    
    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    /* This method decides whether a process fits in a slot or not.

       If it fits, First Fit algorithm chooses the first available slot that is greater than
       or equal to the size of memory the process requires.

       The address is by default set to -1, in case the process does not fit in any of the slots in the memory
       slots, and changes its value if the process does fit a slot.
       The address then, is set to the start address of the certain available slot.
    */

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {
        boolean fit = false;
        /*The address is by default set to -1, in case the process does not fit
        in any of the slots in the memory slots.
        */
        int address = -1;

        // Get the current available memory state.
        ArrayList<MemorySlot> availableSlots = this.mapAvailableMemory(currentlyUsedMemorySlots);

        for (MemorySlot memorySlot : availableSlots)
        {

            int sizeOfAvailableBlockOfSLot= memorySlot.getEnd()-memorySlot.getStart();
            if ((sizeOfAvailableBlockOfSLot>=p.getMemoryRequirements()))
            {
                fit = true;
                /*The address is set to the start address of the certain
                available slot.
                */
                address = memorySlot.getStart();
                break;
            }
        }
        return address;
    }

}
