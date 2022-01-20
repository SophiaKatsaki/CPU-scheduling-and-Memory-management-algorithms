import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm
{
    
    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {
        boolean fit = false;
        /*The address is by default set to -1, in case the process does not fit
        in any of the slots in the memory slots.
        */
        int address = -1;

        int counterOfPlaceInArraylistCurrenltyUsedMemorySlots=0;
        for (MemorySlot memorySlot:currentlyUsedMemorySlots)
        {

            int sizeOfAvailableBlockOfSLot= memorySlot.getEnd()-memorySlot.getStart();
            if ((sizeOfAvailableBlockOfSLot>=p.getMemoryRequirements()))
            {
                fit = true;
                /*The address is set to the start adress of the certain
                available slot.
                */
                address = memorySlot.getStart();
                break;
            }
            counterOfPlaceInArraylistCurrenltyUsedMemorySlots++;
        }
        return address;
    }

}
