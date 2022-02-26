package JobScheduling;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling1 extends BackTracking<Integer> {

    private int[] profits;
    private int[] deadlines;
    private int[] executionTimes;
    private int[] finalX;
    public int finalProfit;
    private int finalExecutionTimes;
    private int[] currentNodeProfit;
    private int[] currentNodeExecutionTimes;

    public JobScheduling1(int[] p, int[] d, int[] e) {
        super(new Integer[p.length]);
        profits = p;
        deadlines = d;
        executionTimes = e;
        sortJS();
        finalX = new int[p.length];
        finalProfit = -1;
        finalExecutionTimes = -1;
        currentNodeExecutionTimes = new int[p.length];
        currentNodeProfit = new int[p.length];
        currentNodeExecutionTimes[0] = 0;
        currentNodeProfit[0] = 0;
    }

    private void sortJS() {
        double[][] data = new double[profits.length][4];
        for (int i = 0; i < profits.length; i++) {
            data[i][0] = executionTimes[i];
            data[i][1] = profits[i];
            data[i][2] = deadlines[i];
        }
        Arrays.sort(data, Comparator.comparingDouble(a -> a[2]));
        for (int i = 0; i < profits.length; i++) {
            executionTimes[i] = (int) data[i][0];
            profits[i] = (int) data[i][1];
            deadlines[i] = (int) data[i][2];
        }
    }

    @Override
    protected Integer[] nodeValues(int k) {
        return new Integer[]{1, 0};
    }

    private double boundRight(int k) {
        if (k > 0) {
            currentNodeProfit[k] = currentNodeProfit[k - 1];
            currentNodeExecutionTimes[k] = currentNodeExecutionTimes[k - 1];
        } else {
            currentNodeProfit[0] = 0;
            currentNodeExecutionTimes[0] = 0;
        }
        int currentProfit = currentNodeProfit[k];

        for (int i = k + 1; i < x.length; i++) {
            currentProfit += profits[i];
        }
        return (currentProfit > finalProfit) ? 1 : 0;
    }

    private double boundLeft(int k) {
        if (k > 0) {
            currentNodeProfit[k] = currentNodeProfit[k - 1] + profits[k];
            currentNodeExecutionTimes[k] = currentNodeExecutionTimes[k - 1] + executionTimes[k];
        } else {
            currentNodeProfit[0] = profits[0];
            currentNodeExecutionTimes[0] = executionTimes[0];
        }
        int currentExecutionTimes = currentNodeExecutionTimes[k];
        return (deadlines[k] >= currentExecutionTimes) ? 1 : 0;
    }

    @Override
    protected double bound(int k) {
        return (x[k] == 1) ? boundLeft(k) : boundRight(k);
    }

    @Override
    protected boolean isSolution(int k) {
        return k == profits.length - 1;
    }

    @Override
    protected void solutionFound(int k) {
        int currentProfit = currentNodeProfit[k];
        int totalExecutionTimes = currentNodeExecutionTimes[k];

        if ((currentProfit > finalProfit) && (k == profits.length - 1)) {
            finalProfit = currentProfit;
            finalExecutionTimes = totalExecutionTimes;

            for (int i = 0; i <= k; i++) finalX[i] = x[i];
        }
    }

    public void report() {
        for (int i = 0; i < finalX.length; i++) {
            if (finalX[i] == 1) {
                System.out.println(executionTimes[i] + " -> " + profits[i]);
            }
        }
        System.out.println("Total Execution Times = " + finalExecutionTimes + " Total Profit = " + finalProfit);
        System.out.println("Number of nodes generated: " + numberOfNodes);
    }
}
