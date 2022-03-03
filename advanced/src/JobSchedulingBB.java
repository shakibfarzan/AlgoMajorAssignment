
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class JSNode {
    public int val;
    public int currentNodeProfit;
    public int currentNodeLoss;
    public int currentNodeExecutionTimes;

    public JSNode(int val, int currentNodeProfit, int currentNodeExecutionTimes, int currentNodeLoss) {
        this.val = val;
        this.currentNodeProfit = currentNodeProfit;
        this.currentNodeExecutionTimes = currentNodeExecutionTimes;
        this.currentNodeLoss = currentNodeLoss;
    }

    public String toString() {
        return "val: " + val + " Current Profit: " + currentNodeProfit + " Current ExecutionTime: "
                + currentNodeExecutionTimes + " Current Loss: "+currentNodeLoss;
    }
}

public class JobSchedulingBB extends BranchAndBound<JSNode> {
    public int[] profits;
    public int[] deadlines;
    public int[] executionTimes;

    public JobSchedulingBB(int[] d, int[] p, int[] ex) {
        executionTimes = ex;
        deadlines = d;
        profits = p;
        sortJS();
    }

    private void sortJS() {
        int[][] data = new int[profits.length][4];
        for (int i = 0; i < profits.length; i++) {
            data[i][0] = executionTimes[i];
            data[i][1] = profits[i];
            data[i][2] = deadlines[i];
        }
        Arrays.sort(data, Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < profits.length; i++) {
            executionTimes[i] = data[i][0];
            profits[i] = data[i][1];
            deadlines[i] = data[i][2];
        }
    }

    @Override
    public JSNode[] nodeValues(Node<JSNode> parent) {
        if (parent.depth == profits.length) {
            return new JSNode[0];
        }

        JSNode[] next = new JSNode[2];
        next[0] = new JSNode(0, parent.x.currentNodeProfit + profits[parent.depth], parent.x.currentNodeExecutionTimes, parent.x.currentNodeLoss);
        next[1] = new JSNode(1,parent.x.currentNodeProfit, parent.x.currentNodeExecutionTimes + executionTimes[parent.depth], parent.x.currentNodeLoss - profits[parent.depth]);
        return next;
    }

    @Override
    public boolean isSolution(Node<JSNode> node) {
        return node.depth == profits.length;
    }

    @Override
    public double calcCX(int depth, JSNode t) {
        if(depth == 0) return 0;

        double currentProfit = 0;
        if (t.val == 0) {
            currentProfit += profits[depth - 1];
        }else{
            if (t.currentNodeExecutionTimes > deadlines[depth - 1]) return 1000000000;
            currentProfit = t.currentNodeProfit;
        }

        return currentProfit;
    }

    @Override
    public double calcUX(int depth, JSNode t) {
        return t.currentNodeLoss;
    }

    public double solve(Strategy strategy) {
        int sumProfits = sumProfits();
        JSNode root = new JSNode(0, 0, 0, sumProfits);

        solve(strategy, root);

        return sumProfits - answerVal;
    }

    private int sumProfits(){
        int sum = 0;
        for (int profit: profits){
            sum+=profit;
        }
        return sum;
    }

    @Override
    public double cost(ArrayList<Node<JSNode>> branch) {

        return branch.get(0).x.currentNodeProfit;
    }

    public void report(ArrayList<Node<JSNode>> answer) {
        for (int i = answer.size() - 2; i >= 0; i--) {
            System.out.println(answer.get(i).x.val + " , ");

        }
        System.out.println(" Total Profit = " + answerVal);
        System.out.println("Number of nodes generated: " + numberOfNodes);
    }

}
