package assignmentProblemBT;

import java.util.ArrayList;

public class AssignmentProblem extends BackTrackingOptimization<Integer> {
    private double[][] G;
    private double[] currentNodeProfit;

    public AssignmentProblem(double[][] G) {

        super(new Integer[G[0].length], new Integer[G[0].length]);
        this.G = G;
        currentNodeProfit = new double[G[0].length];
    }

    @Override
    protected double cost(int k) {
        if (k == 0) {
            currentNodeProfit[k] = G[x[k]][k];
        } else {
            currentNodeProfit[k] = currentNodeProfit[k - 1] + G[x[k]][k];
        }
        return -currentNodeProfit[k];
    }

    @Override
    protected Integer[] nodeValues(int k) {
        if (k == x.length)
            return null;
        k++;
        ArrayList<Integer> nextValues = new ArrayList<>();
        if (k == 0) {
            for (int i = 0; i < G.length; i++) {
                nextValues.add(i);
            }
        } else {
            for (int val = 0; val < G.length; val++) {
                int i;
                for (i = 0; i <= k - 1; i++) {
                    if (x[i] == val)
                        break;
                }
                if (i == k) {
                    if ((k < G.length - 1) || ((k == G.length - 1))) {
                        nextValues.add(val);
                    }
                }
            }
        }

        return nextValues.toArray(new Integer[0]);
    }

    @Override
    protected double bound(int k) {
        return (cost(k) > finalProfit) ? 1 : 0;
    }

    @Override
    protected boolean isSolution(int k) {
        return k == x.length - 1;
    }

    @Override
    public void solve() {
        solve(0);
    }

    public void print() {
        System.out.println("Cost: "+-finalProfit);
        for (int i = 0; i < x.length; i++) {
            System.out.println("Job "+i+" -> Worker "+finalX[i]);
        }
        System.out.println();

    }

}