import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 13 tests with the FCFS schedule algorithm to some different occasions.
 *
 * The first 4 use FirstFit algorithm as the memory allocation algorithm, then 2 use NextFit, then 5 use
 * BestFit and the last 2 use WorstFit.
 */

public class FCFSTesting {
    @BeforeEach
    public void setUp(){
        CPU.clock = 0; // Re-initialize clock time.
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     * Convoy Effect.
     */

    @Test
    public void FFtest1() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 8, 10),
                new Process(1, 6, 15),
                new Process(0, 3, 10)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(24, processes[0].getTurnAroundTime());
        assertEquals(16,processes[0].getWaitingTime());
        assertEquals(16,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(9,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(8,processes[2].getTurnAroundTime());
        assertEquals(5,processes[2].getWaitingTime());
        assertEquals(5,processes[2].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void FFtest2() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 2, 5),
                new Process(1, 6, 10),
                new Process(2, 4, 6),
                new Process(3, 9, 12),
                new Process(6, 12, 29)
        };
        final int[] availableBlockSizes = {10, 20, 15, 15, 55}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(8, processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(16,processes[1].getTurnAroundTime());
        assertEquals(10,processes[1].getWaitingTime());
        assertEquals(10,processes[1].getResponseTime());

        // Process 3
        assertEquals(21,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(31,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());

        // Process 5
        assertEquals(42,processes[4].getTurnAroundTime());
        assertEquals(30,processes[4].getWaitingTime());
        assertEquals(30,processes[4].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void FFtest3() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 5, 17),
                new Process(2, 2, 3),
                new Process(2, 3, 29),
                new Process(2,4,17)
        };
        final int[] availableBlockSizes = {32, 40, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(11,processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(13,processes[1].getResponseTime());

        // Process 3
        assertEquals(20,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(26,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest4() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 6, 10),
                new Process(0, 8, 10)
        };
        final int[] availableBlockSizes = {20, 50, 5, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(10, processes[0].getTurnAroundTime());
        assertEquals(4,processes[0].getWaitingTime());
        assertEquals(4,processes[0].getResponseTime());

        // Process 2
        assertEquals(20,processes[1].getTurnAroundTime());
        assertEquals(12,processes[1].getWaitingTime());
        assertEquals(12,processes[1].getResponseTime());
    }

    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void NFtest1() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 6, 40),
                new Process(0, 8, 20)
        };
        final int[] availableBlockSizes = {20, 50, 5, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new NextFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(10, processes[0].getTurnAroundTime());
        assertEquals(4,processes[0].getWaitingTime());
        assertEquals(4,processes[0].getResponseTime());

        // Process 2
        assertEquals(20,processes[1].getTurnAroundTime());
        assertEquals(12,processes[1].getWaitingTime());
        assertEquals(12,processes[1].getResponseTime());
    }

    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void NFtest2() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 5, 17),
                new Process(2, 2, 3),
                new Process(2, 3, 29),
                new Process(2,4,17)
        };
        final int[] availableBlockSizes = {32, 40, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new NextFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(11,processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(13,processes[1].getResponseTime());

        // Process 3
        assertEquals(20,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(26,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void BFtest1() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 5, 17),
                new Process(2, 2, 3),
                new Process(2, 3, 29),
                new Process(2,4,17)
        };
        final int[] availableBlockSizes = {32, 40, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(11,processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(13,processes[1].getResponseTime());

        // Process 3
        assertEquals(20,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(26,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void BFtest2() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(1, 6, 30),
                new Process(0, 8, 12)
        };
        final int[] availableBlockSizes = {5, 90, 30, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(19, processes[0].getTurnAroundTime());
        assertEquals(13,processes[0].getWaitingTime());
        assertEquals(13,processes[0].getResponseTime());

        // Process 2
        assertEquals(12,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void BFtest3() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 2, 5),
                new Process(1, 6, 10),
                new Process(2, 4, 6),
                new Process(3, 9, 12),
                new Process(6, 12, 29)
        };
        final int[] availableBlockSizes = {10, 20, 15, 15, 55}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(8, processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(16,processes[1].getTurnAroundTime());
        assertEquals(10,processes[1].getWaitingTime());
        assertEquals(10,processes[1].getResponseTime());

        // Process 3
        assertEquals(21,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(31,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());

        // Process 5
        assertEquals(42,processes[4].getTurnAroundTime());
        assertEquals(30,processes[4].getWaitingTime());
        assertEquals(30,processes[4].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Two arrival times for three processes.
     * Convoy effect.
     */

    @Test
    public void BFtest4() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 1, 5),
                new Process(1, 40, 50),
                new Process(0, 1, 6)
        };
        final int[] availableBlockSizes = {34, 22, 16, 55}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(6, processes[0].getTurnAroundTime());
        assertEquals(5,processes[0].getWaitingTime());
        assertEquals(5,processes[0].getResponseTime());

        // Process 2
        assertEquals(50,processes[1].getTurnAroundTime());
        assertEquals(10,processes[1].getWaitingTime());
        assertEquals(10,processes[1].getResponseTime());

        // Process 3
        assertEquals(9,processes[2].getTurnAroundTime());
        assertEquals(8,processes[2].getWaitingTime());
        assertEquals(8,processes[2].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void BFtest5() {
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(1, 6, 30),
                new Process(0, 8, 12)
        };
        final int[] availableBlockSizes = {15, 40, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(19, processes[0].getTurnAroundTime());
        assertEquals(13,processes[0].getWaitingTime());
        assertEquals(13,processes[0].getResponseTime());

        // Process 2
        assertEquals(12,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * WorstFir algorithm as the memory allocation algorithm.
     * Small memory requirements for some processes (processes can fit in) and big memory requirements for the
     * others (processes cannot fit in).
     * Same arrival time for each process.
     */

    @Test
    public void WFtest1() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 5, 172),
                new Process(2, 2, 3),
                new Process(2, 3, 29),
                new Process(2,4,120)
        };
        final int[] availableBlockSizes = {32, 40, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());

        // Process 3
        assertEquals(11,processes[2].getTurnAroundTime());
        assertEquals(8,processes[2].getWaitingTime());
        assertEquals(8,processes[2].getResponseTime());

        // Process 4
        assertEquals(-1,processes[3].getTurnAroundTime());
        assertEquals(-1,processes[3].getWaitingTime());
        assertEquals(-1,processes[3].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void WFtest2() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 5, 17),
                new Process(2, 2, 3),
                new Process(2, 3, 29),
                new Process(2,4,17)
        };
        final int[] availableBlockSizes = {32, 40, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(11,processes[0].getTurnAroundTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(13,processes[1].getResponseTime());

        // Process 3
        assertEquals(20,processes[2].getTurnAroundTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(17,processes[2].getResponseTime());

        // Process 4
        assertEquals(26,processes[3].getTurnAroundTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(22,processes[3].getResponseTime());
    }
}
