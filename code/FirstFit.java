import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm
{
    
    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {
        /* This method decides whether a process fits in a slot or not.If it fits,
        First Fit algorithm chooses the first available slot that is greater than
        or equal to the size of memory the process requires. The address is by default
        set to -1, in case the process does not fit in any of the slots in the memory
        slots,and changes its value if the process does fit a slot. The address then,
        is set to the start adress of the certain available slot.
        */
        boolean fit = false;
        int address = -1;

        int counterOfPlaceInArraylistCurrenltyUsedMemorySlots=0;
        for (MemorySlot memorySlot:currentlyUsedMemorySlots)
        {

            int sizeOfAvailableBlockOfSLot= memorySlot.getEnd()-memorySlot.getStart();
            if ((sizeOfAvailableBlockOfSLot>=p.getMemoryRequirements()))
            {
                fit = true;
                address = memorySlot.getStart();
                //currentlyUsedMemorySlots.get(counterOfPlaceInArraylistCurrenltyUsedMemorySlots).setStart(memorySlot.getStart()+p.getMemoryRequirements());
                break;
            }
            counterOfPlaceInArraylistCurrenltyUsedMemorySlots++;
        }
        return address;
    }

}
