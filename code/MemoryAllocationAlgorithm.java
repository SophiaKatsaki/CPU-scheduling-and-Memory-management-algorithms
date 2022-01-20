import java.util.ArrayList;

public abstract class MemoryAllocationAlgorithm
{

    protected final int[] availableBlockSizes;

    public MemoryAllocationAlgorithm(int[] availableBlockSizes) {
        this.availableBlockSizes = availableBlockSizes;
    }

    public abstract int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots);

    protected ArrayList<MemorySlot> mapAvailableMemory(ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        /*
         * "Discovers" the potentially available memory slots for new processes and returns the list of them.
         */

        // The list that will, eventually, contain the free memory slots.
        ArrayList<MemorySlot> availableMemorySlots = new ArrayList<>();

        // Iterating through the memory address ranges (that the available blocks form) and create initial available slots (that are present only when memory is empty).
        // Those slots will be split into smaller when used sub-slots are eventually removed from them.
        for (int currentMemPosition = 0, b_i = 0; b_i < availableBlockSizes.length; b_i++) { // b_i is the iterator of blocks and currentMemPosition stores the current "position" in the total memory.
            availableMemorySlots.add(new MemorySlot(
                    currentMemPosition, currentMemPosition + availableBlockSizes[b_i] - 1, currentMemPosition, currentMemPosition + availableBlockSizes[b_i] - 1
            ));

            currentMemPosition += availableBlockSizes[b_i]; // The new block begins in the address neighbouring the end of the previous one.
        }

        // From the available slots, remove the sub-slots that are used leaving behind free slots (that may be split again
        // in a future iteration).
        for (MemorySlot cu : currentlyUsedMemorySlots){
            for (int i = 0; i < availableMemorySlots.size(); i++){

                // The memory slot used in the current iteration of free slots.
                MemorySlot currentMemSlot = availableMemorySlots.get(i);

                // If cu is a part of the current availableMemorySlot, currentMemSlot.
                if (cu.getStart() >= currentMemSlot.getStart() && cu.getEnd() <= currentMemSlot.getEnd()){
                    // The beginning and ending address of the slot that will (potentially) remain in the left of the current free slot
                    //  after it is split with the cu removed from it.
                    int leftSlotStart, leftSlotEnd;

                    // The beginning and ending address of the slot that will (potentially) remain in the right of the current free slot
                    //  after it is split with the cu removed from it.
                    int rightSlotStart, rightSlotEnd;

                    // Check if we have a left remainder slot
                    if (cu.getStart() == currentMemSlot.getStart()){
                        leftSlotStart = leftSlotEnd = -1;
                    } else {
                        leftSlotStart = currentMemSlot.getStart();
                        leftSlotEnd = cu.getStart() - 1;
                    }

                    // Check if we have a right remainder slot
                    if (cu.getEnd() == currentMemSlot.getEnd()){
                        rightSlotStart = rightSlotEnd = -1;
                    } else {
                        rightSlotStart = cu.getEnd() + 1;
                        rightSlotEnd = currentMemSlot.getEnd();
                    }

                    // Remove the whole current free slot and (potentially) replace it with up to two remainder
                    // slots (the left and/or right remainder)
                    availableMemorySlots.remove(i);

                    // Insert the left sub-slot (if it exists)
                    if (leftSlotStart!= -1 && leftSlotEnd!=-1){
                        availableMemorySlots.add(i,
                                new MemorySlot(leftSlotStart, leftSlotEnd, currentMemSlot.getBlockStart(), currentMemSlot.getBlockEnd())
                        );

                        i++;
                    }

                    // Insert the right sub-slot (if it exists)
                    if (rightSlotStart!= -1 && rightSlotEnd!=-1){
                        availableMemorySlots.add(i,
                                new MemorySlot(rightSlotStart, rightSlotEnd, currentMemSlot.getBlockStart(), currentMemSlot.getBlockEnd())
                        );
                    }


                    break; // Current used slot accounted for, onto the next used slot.
                }
            }
        }

        return availableMemorySlots;
    }


}
