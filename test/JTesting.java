import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JTesting {


    @BeforeEach
    public void setUp(){
        CPU.clock = 0; // Re-initialize clock time.
    }

    @Test
    public void TestRR_Q1(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 10),
                new Process(0, 3, 40)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(1);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(24,processes[0].getTurnAroundTime());
        assertEquals(19,processes[0].getWaitingTime());
        assertEquals(4,processes[0].getResponseTime());

        // Process 2
        assertEquals(20,processes[1].getTurnAroundTime());
        assertEquals(17,processes[1].getWaitingTime());
        assertEquals(7,processes[1].getResponseTime());
    }

    @Test
    public void TestRR_Q3(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 10),
                new Process(0, 3, 40)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(3);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(11,processes[0].getWaitingTime());
        assertEquals(4,processes[0].getResponseTime());

        // Process 2
        assertEquals(12,processes[1].getTurnAroundTime());
        assertEquals(9,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());
    }

    @Test
    public void TestRR_Q3_3P(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 1),
                new Process(0, 3, 1),
                new Process(0, 4, 1)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(3);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(22,processes[0].getTurnAroundTime());
        assertEquals(17,processes[0].getWaitingTime());
        assertEquals(5,processes[0].getResponseTime());

        // Process 2
        assertEquals(13,processes[1].getTurnAroundTime());
        assertEquals(10,processes[1].getWaitingTime());
        assertEquals(10,processes[1].getResponseTime());

        // Process 3
        assertEquals(25,processes[2].getTurnAroundTime());
        assertEquals(21,processes[2].getWaitingTime());
        assertEquals(15,processes[2].getResponseTime());
    }

    @Test
    public void TestRR_Q2_4P(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 1),
                new Process(1, 3, 1),
                new Process(2, 4, 1),
                new Process(5, 2, 1)

        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(34,processes[0].getTurnAroundTime());
        assertEquals(29,processes[0].getWaitingTime());
        assertEquals(5,processes[0].getResponseTime());

        // Process 2
        assertEquals(26,processes[1].getTurnAroundTime());
        assertEquals(23,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(29,processes[2].getTurnAroundTime());
        assertEquals(25,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(15,processes[3].getTurnAroundTime());
        assertEquals(13,processes[3].getWaitingTime());
        assertEquals(13,processes[3].getResponseTime());
    }

    @Test
    public void TestRR_Q2_2P_Idle(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 1, 1),

                new Process(7, 2, 1)

        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(4,processes[0].getTurnAroundTime());
        assertEquals(3,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(5,processes[1].getTurnAroundTime());
        assertEquals(3,processes[1].getWaitingTime());
        assertEquals(3,processes[1].getResponseTime());
    }


    @Test
    public void TestRR_Q2_2P_LargeIdle(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 1, 1),

                new Process(100, 2, 1)

        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(4,processes[0].getTurnAroundTime());
        assertEquals(3,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(5,processes[1].getTurnAroundTime());
        assertEquals(3,processes[1].getWaitingTime());
        assertEquals(3,processes[1].getResponseTime());
    }

    @Test
    public void TestRR_1P(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(100, 2, 1)

        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(5,processes[0].getTurnAroundTime());
        assertEquals(3,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());
    }

    @Test
    public void TestRR_Q2_2P_Sequentially(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 2, 1),
                new Process(5, 3, 1)

        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(2);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // TODO: Check again: Implementation quirk of CPU: A process that has finished an in that exact moment
        // TODO, a new process arrives, the finished one waits for the new to begin to be marked as finished.

        // Process 1
        assertEquals(6,processes[0].getTurnAroundTime());
        assertEquals(4,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(3,processes[1].getWaitingTime());
        assertEquals(3,processes[1].getResponseTime());
    }

}
