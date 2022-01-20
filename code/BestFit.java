import java.util.ArrayList;

public class BestFit extends MemoryAllocationAlgorithm {

    /**
     * The constructor that initializes our variables by using the constructor of MemoryAllocationAlgorithm.
     * @param availableBlockSizes is the block sizes that are available.
     */

    public BestFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    /**
     * Method that makes sure if is possible to fit a process in a memory slot.
     *
     * @param p is the current process
     * @param currentlyUsedMemorySlots is the memory slots that we currently have.
     * @return the memory address where the process was loaded into if the process fits. In case the process
     *         doesn't fit, it returns -1.
     */

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;


        int minAvailableSlotSize = -1; // the size of available blocks that is currently chosen to be the
        // place that the process will be (initialize to -1 because we haven't
        // found any place).

        // Get the current available memory state.
        ArrayList<MemorySlot> availableSlots = this.mapAvailableMemory(currentlyUsedMemorySlots);

        // Searching in every memory slot that is currently being used for a place that will fit the current process.

        for (MemorySlot memorySlot:availableSlots) {

            int sizeOfCurrentSlot = memorySlot.getEnd() - memorySlot.getStart() + 1; // size of available blocks

            // If the size of available blocks is enough for the current process and we don't have any address, we
            // consider the start of that available blocks as the address. So we found the first possible place and
            // it is considered as the "worst fit".
            if ((sizeOfCurrentSlot >= p.getMemoryRequirements()) && address==-1) {
                fit = true;
                address = memorySlot.getStart();
                minAvailableSlotSize = sizeOfCurrentSlot;
            }

            // If we are not in the "first possible place", then we found another possible place and we compare it
            // to the chosen one. If it is better, we change the "worst fit" to the newest worst fit and its address.
            if (!(address==-1)) {
                if (sizeOfCurrentSlot >= p.getMemoryRequirements() && sizeOfCurrentSlot <
                        minAvailableSlotSize) {
                    address = memorySlot.getStart();
                    sizeOfAvailableBlocksChosen = sizeOfAvailableBlocks;
                }
            }
        }

        // If there is a fit, we return the address, otherwise we return the code-number of "-1".
        if (fit)
            return address;
        else
            return -1;
    }

}
