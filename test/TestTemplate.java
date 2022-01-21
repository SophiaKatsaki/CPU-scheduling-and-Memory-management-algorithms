import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestTemplate {

    // TODO: REMOVE setUp, test1-4()
    @BeforeEach
    public void setUp(){
        System.out.println("setUp Test");
    }

    @Test
    public void TestRR_Q2(){
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

    @Test
    public void test1(){
        System.out.println("Test1 Success");
        assertEquals(1, 1);
    }

    @Test
    public void test2(){
        System.out.println("Test2 Success");
        assertFalse(false);
    }

    @Test
    public void test3(){
        System.out.println("Test3 Fail");
        assertEquals(1, 2);
    }

    @Test
    public void test4(){
        System.out.println("Test4 Fail");
        fail();
    }


}
