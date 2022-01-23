import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 19 tests with the SRTF schedule algorithm to some different occasions.
 *
 * The first 5 use FirstFit algorithm as the memory allocation algorithm, then 4 use NextFit, then 5 use
 * BestFit and the last 5 use WorstFit.
 */

public class STRFTesting {
    @BeforeEach
    public void setUp(){
        CPU.clock = 0; // Re-initialize clock time.
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest1(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 50),
                new Process(0, 10, 200)
        };
        final int[] availableBlockSizes = {45, 40, 30, 35}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 5)
        };
        final int[] availableBlockSizes = {30, 44, 12, 18}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(8,processes[0].getWaitingTime());
        assertEquals(8,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
     * can fit.
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest3(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 500),
                new Process(0, 10, 20),
                new Process(0, 5, 10)
        };
        final int[] availableBlockSizes = {10, 5, 60, 75}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(20,processes[0].getTurnAroundTime());
        assertEquals(12,processes[0].getWaitingTime());
        assertEquals(12,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());

        // Process 3
        assertEquals(32,processes[2].getTurnAroundTime());
        assertEquals(22,processes[2].getWaitingTime());
        assertEquals(22,processes[2].getResponseTime());

        // Process 4
        assertEquals(10,processes[3].getTurnAroundTime());
        assertEquals(5,processes[3].getWaitingTime());
        assertEquals(5,processes[3].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest4() {
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
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(26,processes[0].getTurnAroundTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(21,processes[0].getResponseTime());

        // Process 2
        assertEquals(8,processes[1].getTurnAroundTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(6,processes[1].getResponseTime());

        // Process 3
        assertEquals(13,processes[2].getTurnAroundTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(10,processes[2].getResponseTime());

        // Process 4
        assertEquals(19,processes[3].getTurnAroundTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * FirstFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest5(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 100),
                new Process(0, 3, 400)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());
    }

    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void NFtest1(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 5)
        };
        final int[] availableBlockSizes = {30, 44, 12, 18}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new NextFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(8,processes[0].getWaitingTime());
        assertEquals(8,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void NFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 50),
                new Process(0, 10, 200)
        };
        final int[] availableBlockSizes = {45, 40, 30, 35}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new NextFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());
    }

    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void NFtest3(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 5)
        };
        final int[] availableBlockSizes = {30, 44, 12, 18}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new NextFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(8,processes[0].getWaitingTime());
        assertEquals(8,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }
    /**
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void NFtest4() {
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
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(26,processes[0].getTurnAroundTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(21,processes[0].getResponseTime());

        // Process 2
        assertEquals(8,processes[1].getTurnAroundTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(6,processes[1].getResponseTime());

        // Process 3
        assertEquals(13,processes[2].getTurnAroundTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(10,processes[2].getResponseTime());

        // Process 4
        assertEquals(19,processes[3].getTurnAroundTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in), but memory is full.
     * Different arrival time for the processes.
     */

    @Test
    public void BFtest1() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 4, 44),
                new Process(1, 3, 44),
                new Process(2, 2, 32)
        };
        final int[] availableBlockSizes = {32, 44}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(14,processes[0].getTurnAroundTime());
        assertEquals(10,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(19,processes[1].getTurnAroundTime());
        assertEquals(16,processes[1].getWaitingTime());
        assertEquals(16,processes[1].getResponseTime());

        // Process 3
        assertEquals(7,processes[2].getTurnAroundTime());
        assertEquals(5,processes[2].getWaitingTime());
        assertEquals(5,processes[2].getResponseTime());

    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
     * can fit in.
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 500),
                new Process(0, 10, 20),
                new Process(0, 5, 10)
        };
        final int[] availableBlockSizes = {10, 5, 60, 75}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(20,processes[0].getTurnAroundTime());
        assertEquals(12,processes[0].getWaitingTime());
        assertEquals(12,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());

        // Process 3
        assertEquals(32,processes[2].getTurnAroundTime());
        assertEquals(22,processes[2].getWaitingTime());
        assertEquals(22,processes[2].getResponseTime());

        // Process 4
        assertEquals(10,processes[3].getTurnAroundTime());
        assertEquals(5,processes[3].getWaitingTime());
        assertEquals(5,processes[3].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest3(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 50),
                new Process(0, 10, 200)
        };
        final int[] availableBlockSizes = {45, 40, 30, 35}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest4(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 5)
        };
        final int[] availableBlockSizes = {30, 44, 12, 18}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(8,processes[0].getWaitingTime());
        assertEquals(8,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest5() {
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
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(26,processes[0].getTurnAroundTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(21,processes[0].getResponseTime());

        // Process 2
        assertEquals(8,processes[1].getTurnAroundTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(6,processes[1].getResponseTime());

        // Process 3
        assertEquals(13,processes[2].getTurnAroundTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(10,processes[2].getResponseTime());

        // Process 4
        assertEquals(19,processes[3].getTurnAroundTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in), but memory is full.
     * Different arrival time for the processes.
     */

    @Test
    public void WFtest1() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 4, 44),
                new Process(1, 3, 44),
                new Process(2, 2, 32)
        };
        final int[] availableBlockSizes = {32, 44}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(14,processes[0].getTurnAroundTime());
        assertEquals(10,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(19,processes[1].getTurnAroundTime());
        assertEquals(16,processes[1].getWaitingTime());
        assertEquals(16,processes[1].getResponseTime());

        // Process 3
        assertEquals(7,processes[2].getTurnAroundTime());
        assertEquals(5,processes[2].getWaitingTime());
        assertEquals(5,processes[2].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void WFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 50),
                new Process(0, 10, 200)
        };
        final int[] availableBlockSizes = {45, 40, 30, 35}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void WFtest3(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 5)
        };
        final int[] availableBlockSizes = {30, 44, 12, 18}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(16,processes[0].getTurnAroundTime());
        assertEquals(8,processes[0].getWaitingTime());
        assertEquals(8,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(4,processes[1].getWaitingTime());
        assertEquals(4,processes[1].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
     * can fit in.
     * Same arrival time for the processes.
     */

    @Test
    public void WFtest4(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 20),
                new Process(0, 2, 500),
                new Process(0, 10, 20),
                new Process(0, 5, 10)
        };
        final int[] availableBlockSizes = {10, 5, 60, 75}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(20,processes[0].getTurnAroundTime());
        assertEquals(12,processes[0].getWaitingTime());
        assertEquals(12,processes[0].getResponseTime());

        // Process 2
        assertEquals(-1,processes[1].getTurnAroundTime());
        assertEquals(-1,processes[1].getWaitingTime());
        assertEquals(-1,processes[1].getResponseTime());

        // Process 3
        assertEquals(32,processes[2].getTurnAroundTime());
        assertEquals(22,processes[2].getWaitingTime());
        assertEquals(22,processes[2].getResponseTime());

        // Process 4
        assertEquals(10,processes[3].getTurnAroundTime());
        assertEquals(5,processes[3].getWaitingTime());
        assertEquals(5,processes[3].getResponseTime());
    }

    /**
     * WorstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void WFtest5() {
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
        Scheduler scheduler = new SRTF();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(26,processes[0].getTurnAroundTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(21,processes[0].getResponseTime());

        // Process 2
        assertEquals(8,processes[1].getTurnAroundTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(6,processes[1].getResponseTime());

        // Process 3
        assertEquals(13,processes[2].getTurnAroundTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(10,processes[2].getResponseTime());

        // Process 4
        assertEquals(19,processes[3].getTurnAroundTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }
}
