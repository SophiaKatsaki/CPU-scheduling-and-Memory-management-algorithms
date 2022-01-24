public class PC {

    public static void main(String[] args) {
        final Process[] processes = {
                // Process parameters are: arrivalTime, burstTime, memoryRequirements (kB)
                new Process(0, 5, 10),
                new Process(2, 2, 40),
                new Process(3, 1, 25),
                new Process(4, 3, 30)
        };
        final int[] availableBlockSizes = {15, 40, 10, 20}; // sizes in kB
        MemoryAllocationAlgorithm algorithm = new WorstFit(availableBlockSizes);
        MMU mmu = new MMU(availableBlockSizes, algorithm);        
        Scheduler scheduler = new FCFS();
        CPU cpu = new CPU(scheduler, mmu, processes);
        cpu.run();

        // Process 1
        System.out.println("For process with id=" + processes[0].getPCB().getPid() + " we have:");
        System.out.println("Turn Around Time = " + processes[0].getTurnAroundTime());
        System.out.println("Waiting Time = " + processes[0].getWaitingTime());
        System.out.println("Response Time = " + processes[0].getResponseTime());

        System.out.println();

        // Process 2
        System.out.println("For process with id=" + processes[1].getPCB().getPid() + " we have:");
        System.out.println("Turn Around Time = " + processes[1].getTurnAroundTime());
        System.out.println("Waiting Time = " + processes[1].getWaitingTime());
        System.out.println("Response Time = " + processes[1].getResponseTime());

        System.out.println();

        // Process 3
        System.out.println("For process with id=" + processes[2].getPCB().getPid() + " we have:");
        System.out.println("Turn Around Time = " + processes[2].getTurnAroundTime());
        System.out.println("Waiting Time = " + processes[2].getWaitingTime());
        System.out.println("Response Time = " + processes[2].getResponseTime());

        System.out.println();

        // Process 4
        System.out.println("For process with id=" + processes[3].getPCB().getPid() + " we have:");
        System.out.println("Turn Around Time = " + processes[3].getTurnAroundTime());
        System.out.println("Waiting Time = " + processes[3].getWaitingTime());
        System.out.println("Response Time = " + processes[3].getResponseTime());
    }

}
