import java.util.ArrayList;

public class MMU
{
    private final int[] availableBlockSizes;
    private MemoryAllocationAlgorithm algorithm;
    private ArrayList<MemorySlot> currentlyUsedMemorySlots;
    
    public MMU(int[] availableBlockSizes, MemoryAllocationAlgorithm algorithm)
    {
        this.availableBlockSizes = availableBlockSizes;
        this.algorithm = algorithm;
        this.currentlyUsedMemorySlots = new ArrayList<MemorySlot>();
    }

    public boolean loadProcessIntoRAM(Process p)
    {
        boolean fit = false;

        cleanUp(); // Perform freeing of memory reserved for processes that have already terminated.
        // The memory occupied by finished processes is freed only when a new allocation is attempted.
        // Otherwise, it just remains in memory and does not affect anything else.

        // Trigger the underlying memory allocation algorithm to attempt to find a slot to be able to
        // fit the process into a new slot in memory.
        int newProcessAddress = algorithm.fitProcess(p, currentlyUsedMemorySlots);

        // By convention (of this project), the memory allocation algorithm will return the memory address that would be
        // the start of the process p's beginning of memory slot in the case of finding an available slot.
        // In the case of failure to do that, the invalid memory address -1 is returned.

        // If there is an available memory slot for this memory to be placed in, create the slot (thus, "reserving" the memory needed)
        // and set the fit boolean to true (if the algorithm returned a valid memory address within the total memory formed by the blocks),
        // so the calling method is informed of this event.
        if (newProcessAddress != -1) {

            // Iterating through the memory address ranges (that the available blocks form) to find the block in which available
            // memory for the process p was found.
            for (int currentMemPosition = 0, b_i = 0; b_i < availableBlockSizes.length; b_i++) { // b_i is the iterator of blocks and currentMemPosition stores the current "position" in the total memory.

                // If the process fits in the b_i-th memory block
                if (newProcessAddress >= currentMemPosition && newProcessAddress <= currentMemPosition + availableBlockSizes[b_i] - 1){
                    MemorySlot newSlot = new MemorySlot(newProcessAddress, newProcessAddress + p.getMemoryRequirements() - 1, currentMemPosition, currentMemPosition + availableBlockSizes[b_i] - 1);
                    newSlot.setProcessAssociated(p);
                    currentlyUsedMemorySlots.add(newSlot);

                    fit = true; // The required memory was reserved.
                    break; // The search of block has finished.
                }

                currentMemPosition += availableBlockSizes[b_i]; // The new block begins in the address neighbouring the end of the previous one.
            }
        }
        // If during the search, the block in which the process is to be placed is not found, the process is not fit anywhere,
        // so fit retains the value false.
        // If it is found, the fit variable gets the value true inside the if of the for loop.
        
        return fit;
    }

    public int[] getAvailableBlockSizes() {
        return availableBlockSizes;
    }

    /*
     * Frees memory slots occupied by previously terminated processes.
     */
    private void cleanUp(){
        // Scan the list of used memory slots and delete the ones that are occupied by terminated processes.
        int i = 0; // Loop iterator for currentlyUsedMemorySlots.
        while (i < currentlyUsedMemorySlots.size()){

            if (currentlyUsedMemorySlots.get(i).getProcessAssociated().getPCB().getState() == ProcessState.TERMINATED){
                currentlyUsedMemorySlots.remove(i);
                continue; // When an ArrayList element is removed, the remaining elements to the right are shifted left.
                          // So, making sure that the iterator, i, has the right value.
            }

            i++;
        }
    }
}
