import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JTesting {
    @Test
    public void testname(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 10),
                new Process(0, 3, 40)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(20,processes[0].getTurnAroundTime());
        assertEquals(15,processes[0].getWaitingTime());
        assertEquals(4,processes[0].getResponseTime());

        // Process 2
        assertEquals(17,processes[1].getTurnAroundTime());
        assertEquals(14,processes[1].getWaitingTime());
        assertEquals(8,processes[1].getResponseTime());
    }

}
