import java.util.ArrayList;

public class JSProgram {
    public static void main(String[] args) {
        test(20, 100);
    }

    private static void test(int n, int maxRep) {
        for (int num = 4; num <= n; num += 2) {
            System.out.println("Testing n=" + num);
            for (int rep = 0; rep < maxRep; rep++) {
                int[] profits = generateArray(num, 1, 30);
                int[] deadlines = generateArray(num, 15, 30);
                int[] executionTimes = generateArray(num, 1, 10);
                JobSchedulingBB js = new JobSchedulingBB(deadlines, profits, executionTimes);
                double jsLC = js.solve(Strategy.LC);
                double jsLIFO = js.solve(Strategy.LIFO);
                double jsFIFO = js.solve(Strategy.FIFO);
                int blindVal = JobSchedulingBlind.JSBlindIterative(deadlines, profits, executionTimes, new ArrayList<>());
                if (jsLC != blindVal) {
                    System.out.println("Error on LC");
                    System.exit(0);
                }
                if (jsLIFO != blindVal) {
                    System.out.println("Error on LIFO");
                    System.exit(0);
                }
                if (jsFIFO != blindVal) {
                    System.out.println("Error on FIFO");
                    System.exit(0);
                }
            }
            System.out.println("Test passed for n="+n+" and "+maxRep+" repeats.\n");
        }
    }

    private static int[] generateArray(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (max - min) + min);
        }
        return array;
    }
}
