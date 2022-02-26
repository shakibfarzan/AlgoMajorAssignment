package JobScheduling;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling3 extends BackTrackingOptimization<Integer> {
    private int[] profits;
    private int[] deadlines;
    private int[] executionTimes;
    private int[] currentNodeProfit;
    private int[] currentNodeExecutionTimes;

    public JobScheduling3(int[] p, int[] d, int[] e) {
        super(new Integer[p.length], new Integer[p.length]);
        profits = p;
        deadlines = d;
        executionTimes = e;
        sortJS();
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
    protected double cost(int k) {
        return currentNodeProfit[k];
    }

    @Override
    protected Integer[] nodeValues(int k) {
        int minVal;
        if (k == -1) minVal = -1;
        else minVal = x[k];
        Integer[] next = new Integer[profits.length - minVal - 1];

        for (int i = minVal + 1; i < profits.length; i++) next[i - minVal - 1] = i;
        return next;
    }

    @Override
    protected double bound(int k) {
        if (k > 0) {
            currentNodeProfit[k] = currentNodeProfit[k - 1] + profits[x[k]];
            currentNodeExecutionTimes[k] = currentNodeExecutionTimes[k - 1] + executionTimes[x[k]];
        } else {
            currentNodeProfit[0] = profits[x[k]];
            currentNodeExecutionTimes[0] = executionTimes[x[k]];
        }
        int currentExecutionTimes = currentNodeExecutionTimes[k];
        return deadlines[x[k]] >= currentExecutionTimes ? 1 : 0;
    }

    @Override
    protected boolean isSolution(int k) {
        return true;
    }

    public void report() {
        for (int i = 0; i < finalX.length; i++) {
            if (finalX[i] != null) {
                System.out.println(executionTimes[finalX[i]] + " -> " + profits[finalX[i]]);
            }
        }
        System.out.println("Total Profit = " + finalProfit);
        System.out.println("Number of nodes generated: " + numberOfNodes);
    }
}
