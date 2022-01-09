import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm
{
    
    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots)
    {
        boolean fit = false;
        int address = -1;

        int counterOfPlaceInArraylistCurrenltyUsedMemorySlots=0;
        for (MemorySlot memorySlot:currentlyUsedMemorySlots)
        {

            int sizeOfAvailableBlockOfSLot= memorySlot.getEnd()-memorySlot.getStart();
            if ((sizeOfAvailableBlockOfSLot>=p.getMemoryRequirements()) && (p.getPCB().getState().equals(ProcessState.NEW)))
            {
                fit = true;
                address = memorySlot.getStart();
                currentlyUsedMemorySlots.get(counterOfPlaceInArraylistCurrenltyUsedMemorySlots).setStart(memorySlot.getStart()+p.getMemoryRequirements());
                break;
            }
            counterOfPlaceInArraylistCurrenltyUsedMemorySlots++;
        }
        /* TODO: you need to add some code here
         * Hint: this should return the memory address where the process was
         * loaded into if the process fits. In case the process doesn't fit, it
         * should return -1. */
        return address;
    }

}
