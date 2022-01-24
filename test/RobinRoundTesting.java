import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 18 tests with the Robin Round schedule algorithm to some different occasions.
 *
 * The first 11 use FirstFit algorithm as the memory allocation algorithm, then 3 use NextFit, then 3 use
 * BestFit and the last 1 use WorstFit.
 */

public class RobinRoundTesting {
    @BeforeEach
    public void setUp(){
        CPU.clock = 0; // Re-initialize clock time.
    }

    /**
     * quantum = 3.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest1(){
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

    /**
     * default quantum.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void FFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 5),
                new Process(1, 6, 9),
                new Process(2, 3, 5),
                new Process(3, 1, 5),
                new Process(4, 5, 12),
                new Process(6, 4, 10)
        };
        final int[] availableBlockSizes = {30, 43, 10, 25, 8, 33}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(69,processes[0].getTurnAroundTime());
        assertEquals(64,processes[0].getWaitingTime());
        assertEquals(7,processes[0].getResponseTime());

        // Process 2
        assertEquals(77,processes[1].getTurnAroundTime());
        assertEquals(71,processes[1].getWaitingTime());
        assertEquals(10,processes[1].getResponseTime());

        // Process 3
        assertEquals(46,processes[2].getTurnAroundTime());
        assertEquals(43,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(15,processes[3].getTurnAroundTime());
        assertEquals(14,processes[3].getWaitingTime());
        assertEquals(14,processes[3].getResponseTime());
    }

    /**
     * quantum = 1.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest3(){
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

    /**
     * default quantum.
     * FirstFit algorithm as the memory allocation algorithm.
     * One process has enormous memory requirements (cannot fit in), but the rests can fit.
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest4(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 200),
                new Process(0, 2, 2),
                new Process(0, 10, 20),
                new Process(0, 5, 10)
        };
        final int[] availableBlockSizes = {15, 10, 100, 10}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(5,processes[1].getResponseTime());

        // Process 3
        assertEquals(46,processes[2].getTurnAroundTime());
        assertEquals(36,processes[2].getWaitingTime());
        assertEquals(8,processes[2].getResponseTime());

        // Process 4
        assertEquals(39,processes[3].getTurnAroundTime());
        assertEquals(34,processes[3].getWaitingTime());
        assertEquals(11,processes[3].getResponseTime());
    }

    /**
     * default quantum.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void FFtest5() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(46,processes[0].getTurnAroundTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(22,processes[1].getTurnAroundTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(34,processes[2].getTurnAroundTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(43,processes[3].getTurnAroundTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * quantum = 2.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void FFtest6(){
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

    /**
     * quantum = 3.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void FFtest7(){
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

    /**
     * quantum = 2.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process and there is a time gap between the arrival of the two processes.
     */

    @Test
    public void FFtest8(){
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

    /**
     * quantum = 2.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process.
     */

    @Test
    public void FFtest9(){
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

        // Process 1
        assertEquals(5,processes[0].getTurnAroundTime());
        assertEquals(3,processes[0].getWaitingTime());
        assertEquals(3,processes[0].getResponseTime());

        // Process 2
        assertEquals(6,processes[1].getTurnAroundTime());
        assertEquals(3,processes[1].getWaitingTime());
        assertEquals(3,processes[1].getResponseTime());
    }

    /**
     * quantum = 2.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * One process that arrives late.
     */

    @Test
    public void FFtest10(){
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

    /**
     * quantum = 2.
     * FirstFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process and there is a time gap between the arrival of the two processes.
     */

    @Test
    public void FFtest11(){
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

    /**
     * default quantum.
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void NFtest1() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(46,processes[0].getTurnAroundTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(22,processes[1].getTurnAroundTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(34,processes[2].getTurnAroundTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(43,processes[3].getTurnAroundTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * default quantum.
     * BestFit algorithm as the memory allocation algorithm.
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(46,processes[0].getTurnAroundTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(22,processes[1].getTurnAroundTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(34,processes[2].getTurnAroundTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(43,processes[3].getTurnAroundTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * default quantum.
     * NextFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for each process.
     */

    @Test
    public void NFtest3() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(46,processes[0].getTurnAroundTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(22,processes[1].getTurnAroundTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(34,processes[2].getTurnAroundTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(43,processes[3].getTurnAroundTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }

    /**
     * default quantum.
     * BestFit algorithm as the memory allocation algorithm.
     * Big memory requirements (processes cannot fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest1(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 200),
                new Process(0, 2, 50),
                new Process(0, 10, 200),
                new Process(0, 5, 100)
        };
        final int[] availableBlockSizes = {30, 45, 10, 25}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin();
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

        // Process 3
        assertEquals(-1,processes[2].getTurnAroundTime());
        assertEquals(-1,processes[2].getWaitingTime());
        assertEquals(-1,processes[2].getResponseTime());

        // Process 4
        assertEquals(-1,processes[3].getTurnAroundTime());
        assertEquals(-1,processes[3].getWaitingTime());
        assertEquals(-1,processes[3].getResponseTime());
    }

    /**
     * default quantum.
     * BestFit algorithm as the memory allocation algorithm.
     * One process has enormous memory requirements (cannot fit in), but the rests can fit.
     * Same arrival time for the processes.
     */

    @Test
    public void BFtest2(){
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 8, 200),
                new Process(0, 2, 2),
                new Process(0, 10, 20),
                new Process(0, 5, 10)
        };
        final int[] availableBlockSizes = {15, 10, 100, 10}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(-1,processes[0].getTurnAroundTime());
        assertEquals(-1,processes[0].getWaitingTime());
        assertEquals(-1,processes[0].getResponseTime());

        // Process 2
        assertEquals(15,processes[1].getTurnAroundTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(5,processes[1].getResponseTime());

        // Process 3
        assertEquals(46,processes[2].getTurnAroundTime());
        assertEquals(36,processes[2].getWaitingTime());
        assertEquals(8,processes[2].getResponseTime());

        // Process 4
        assertEquals(39,processes[3].getTurnAroundTime());
        assertEquals(34,processes[3].getWaitingTime());
        assertEquals(11,processes[3].getResponseTime());
    }

    /**
     * quantum = 3.
     * BestFit algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Different arrival time for each process and there is a time gap between the arrival of the two processes.
     */

    @Test
    public void BFtest3() {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(2, 4, 2),
                new Process(12, 3, 39)
        };
        final int[] availableBlockSizes = {32, 44, 58, 2}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new BestFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);
        Scheduler scheduler = new RoundRobin(3);
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        assertEquals(3,processes[0].getResponseTime());
        assertEquals(3,processes[0].getWaitingTime());
        assertEquals(7,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(3,processes[1].getResponseTime());
        assertEquals(3,processes[1].getWaitingTime());
        assertEquals(6,processes[1].getTurnAroundTime());
    }

    /**
     * default quantum.
     * Worst algorithm as the memory allocation algorithm.
     * Small memory requirements (processes can fit in).
     * Same arrival time for the processes.
     */

    @Test
    public void WFtest1() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();


        // Process 1
        assertEquals(46,processes[0].getTurnAroundTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(6,processes[0].getResponseTime());

        // Process 2
        assertEquals(22,processes[1].getTurnAroundTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(9,processes[1].getResponseTime());

        // Process 3
        assertEquals(34,processes[2].getTurnAroundTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(12,processes[2].getResponseTime());

        // Process 4
        assertEquals(43,processes[3].getTurnAroundTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(15,processes[3].getResponseTime());
    }
}
