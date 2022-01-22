import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class STTesting {

    @BeforeEach
    public void setUp() {
        // Re initialize clock time.
        CPU.clock = 0;
    }

    @Test
    public void SRTF_test1(){
        // FirstFit algorithm for memory.
        // Same arrival time.
        // Big memory requirements, so the processes cannot fit in.
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

    @Test
    public void SRTF_test2(){
        // NextFit algorithm for memory.
        // Same arrival time.
        // Big memory requirements, so the processes cannot fit in.
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

    @Test
    public void SRTF_test3(){
        // BestFit algorithm for memory.
        // Same arrival time.
        // Big memory requirements, so the processes cannot fit in.
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

    @Test
    public void SRTF_test4(){
        // WorstFit algorithm for memory.
        // Same arrival time.
        // Big memory requirements, so the processes cannot fit in.
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

    @Test
    public void SRTF_test5(){
        // FirstFit algorithm for memory.
        // Same arrival time.
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

    @Test
    public void SRTF_test6(){
        // NextFit algorithm for memory.
        // Same arrival time.
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

    @Test
    public void SRTF_test7(){
        // BestFit algorithm for memory.
        // Same arrival time.
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

    @Test
    public void SRTF_test8(){
        // WorstFit algorithm for memory.
        // Same arrival time.
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

    @Test
    public void SRTF_test9(){
        // FirstFit algorithm for memory.
        // Same arrival time.
        // The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
        // can fit.
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

    @Test
    public void SRTF_test10(){
        // BestFit algorithm for memory.
        // Same arrival time.
        // The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
        // can fit.
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

    @Test
    public void SRTF_test11(){
        // WorstFit algorithm for memory.
        // Same arrival time.
        // The process with the shortest burst time has enormous memory requirements (cannot fit in), but the rests
        // can fit.
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

    @Test
    public void FCFS_test1() {
        // Convoy effect.
        // FirstFit algorithm for memory.
        // Different arrival time for each process.
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

    @Test
    public void FCFS_test2() {
        // FirstFit algorithm for memory.
        // Same arrival time for 2 processes.
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

    @Test
    public void FCFS_test3() {
        // NextFit algorithm for memory.
        // Same arrival time for 2 processes.
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

    @Test
    public void FCFS_test4() {
        // BestFit algorithm for memory.
        // Different arrival time for each process.
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

    @Test
    public void FCFS_test5() {
        // BestFit algorithm for memory.
        // Different arrival time for each process.
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

    @Test
    public void FCFS_test6() {
        // FirstFit algorithm for memory.
        // Different arrival time for each process.
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

    @Test
    public void FCFS_test7() {
        // BestFit algorithm for memory.
        // Different arrival time for each process.
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

    @Test
    public void FCFS_test8() {
        // Convoy effect.
        // FirstFit algorithm for memory.
        // Two arrival times for three process.
        final Process[]processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 1, 5),
                new Process(1, 40, 50),
                new Process(0, 1, 6)
        };
        final int[] availableBlockSizes = {34, 22, 16, 55}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new FirstFit(availableBlockSizes);
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

    @Test
    public void RR_test1(){
        // FirstFit algorithm for memory.
        // Same arrival time.
        // One process has enormous memory requirements (cannot fit in), but the rests can fit.
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

    @Test
    public void RR_test2(){
        // BestFit algorithm for memory.
        // Same arrival time.
        // One process has enormous memory requirements (cannot fit in), but the rests can fit.
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

    @Test
    public void RR_test3(){
        // BestFit algorithm for memory.
        // Same arrival time.
        // Big memory requirements, so the processes cannot fit in.
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

    @Test
    public void RR_test4(){
        // FirstFit algorithm for memory.
        // Different arrival time.
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
}
