import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ATesting {

    @BeforeEach
    void setUp() {
        CPU.clock = 0;
    }

    @Test
    public void CanNotFitInMemory(){
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

    @Test
    void ConcurrentProcesses_SRTF_NF() {
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
        assertEquals(21,processes[0].getResponseTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(26,processes[0].getTurnAroundTime());



        // Process 2
        assertEquals(6,processes[1].getResponseTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(8,processes[1].getTurnAroundTime());

        // Process 3
        assertEquals(10,processes[2].getResponseTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(13,processes[2].getTurnAroundTime());



        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(19,processes[3].getTurnAroundTime());
    }


    @Test
    void ConcurrentProcesses_FCFS_NF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(11,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(13,processes[1].getResponseTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(15,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(17,processes[2].getResponseTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(20,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(22,processes[3].getResponseTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(26,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_RR_NF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(46,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(9,processes[1].getResponseTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(22,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(12,processes[2].getResponseTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(34,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(43,processes[3].getTurnAroundTime());
    }


    @Test
    void ConcurrentProcesses_SRTF_FF() {
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
        assertEquals(21,processes[0].getResponseTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(26,processes[0].getTurnAroundTime());



        // Process 2
        assertEquals(6,processes[1].getResponseTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(8,processes[1].getTurnAroundTime());

        // Process 3
        assertEquals(10,processes[2].getResponseTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(13,processes[2].getTurnAroundTime());



        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(19,processes[3].getTurnAroundTime());
    }


    @Test
    void ConcurrentProcesses_FCFS_FF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(11,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(13,processes[1].getResponseTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(15,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(17,processes[2].getResponseTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(20,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(22,processes[3].getResponseTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(26,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_RR_FF() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();


        // Process 1
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(46,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(9,processes[1].getResponseTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(22,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(12,processes[2].getResponseTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(34,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(43,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_SRTF_BF() {
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
        assertEquals(21,processes[0].getResponseTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(26,processes[0].getTurnAroundTime());



        // Process 2
        assertEquals(6,processes[1].getResponseTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(8,processes[1].getTurnAroundTime());

        // Process 3
        assertEquals(10,processes[2].getResponseTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(13,processes[2].getTurnAroundTime());



        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(19,processes[3].getTurnAroundTime());
    }


    @Test
    void ConcurrentProcesses_FCFS_BF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(11,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(13,processes[1].getResponseTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(15,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(17,processes[2].getResponseTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(20,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(22,processes[3].getResponseTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(26,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_RR_BF() {
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
        Scheduler scheduler = new RoundRobin();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();


        // Process 1
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(46,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(9,processes[1].getResponseTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(22,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(12,processes[2].getResponseTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(34,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(43,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_SRTF_WF() {
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
        assertEquals(21,processes[0].getResponseTime());
        assertEquals(21,processes[0].getWaitingTime());
        assertEquals(26,processes[0].getTurnAroundTime());



        // Process 2
        assertEquals(6,processes[1].getResponseTime());
        assertEquals(6,processes[1].getWaitingTime());
        assertEquals(8,processes[1].getTurnAroundTime());

        // Process 3
        assertEquals(10,processes[2].getResponseTime());
        assertEquals(10,processes[2].getWaitingTime());
        assertEquals(13,processes[2].getTurnAroundTime());



        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(15,processes[3].getWaitingTime());
        assertEquals(19,processes[3].getTurnAroundTime());
    }


    @Test
    void ConcurrentProcesses_FCFS_WF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(6,processes[0].getWaitingTime());
        assertEquals(11,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(13,processes[1].getResponseTime());
        assertEquals(13,processes[1].getWaitingTime());
        assertEquals(15,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(17,processes[2].getResponseTime());
        assertEquals(17,processes[2].getWaitingTime());
        assertEquals(20,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(22,processes[3].getResponseTime());
        assertEquals(22,processes[3].getWaitingTime());
        assertEquals(26,processes[3].getTurnAroundTime());
    }

    @Test
    void ConcurrentProcesses_RR_WF() {
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
        assertEquals(6,processes[0].getResponseTime());
        assertEquals(41,processes[0].getWaitingTime());
        assertEquals(46,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(9,processes[1].getResponseTime());
        assertEquals(20,processes[1].getWaitingTime());
        assertEquals(22,processes[1].getTurnAroundTime());



        // Process 3
        assertEquals(12,processes[2].getResponseTime());
        assertEquals(31,processes[2].getWaitingTime());
        assertEquals(34,processes[2].getTurnAroundTime());

        // Process 4
        assertEquals(15,processes[3].getResponseTime());
        assertEquals(39,processes[3].getWaitingTime());
        assertEquals(43,processes[3].getTurnAroundTime());
    }

    @Test
    void idle_Time_RR_BF() {
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

    @Test
    void MemIsFull() {
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
        assertEquals(3,processes[0].getResponseTime());
        assertEquals(10,processes[0].getWaitingTime());
        assertEquals(14,processes[0].getTurnAroundTime());

        // Process 2
        assertEquals(16,processes[1].getResponseTime());
        assertEquals(16,processes[1].getWaitingTime());
        assertEquals(19,processes[1].getTurnAroundTime());

        // Process 3
        assertEquals(5,processes[2].getResponseTime());
        assertEquals(5,processes[2].getWaitingTime());
        assertEquals(7,processes[2].getTurnAroundTime());

    }
}
