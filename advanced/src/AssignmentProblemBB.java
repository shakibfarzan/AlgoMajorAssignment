import java.util.ArrayList;

class APNode {
    public int val;
    public double currentNodeProfit;

    public APNode(int val, double currentNodeProfit) {
        this.val = val;
        this.currentNodeProfit = currentNodeProfit;
    }

    public String toString() {
        return "val: " + val + " Current Cost: " + currentNodeProfit;
    }
}

public class AssignmentProblemBB extends BranchAndBound<APNode> {
    public double[][] G;

    public AssignmentProblemBB(double[][] G) {
        this.G = G;
    }

    @Override
    public APNode[] nodeValues(Node<APNode> parent) {
        ArrayList<APNode> nextValues = new ArrayList<>();
        ArrayList<Node<APNode>> currentPath = branchPath(parent);
        if (parent.depth == 0) {
            for (int i = 0; i < G.length; i++) {
                nextValues.add(new APNode(i, G[i][parent.depth]));
            }
        } else if (parent.depth < G[0].length){
            for (int val = 0; val < G.length; val++) {
                boolean found = false;
                for (Node<APNode> node : currentPath) {
                    if (node.x.val == val) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    nextValues.add(new APNode(val, parent.x.currentNodeProfit + G[val][parent.depth]));
                }

            }
        }
        return nextValues.toArray(new APNode[0]);
    }

    @Override
    public boolean isSolution(Node<APNode> node) {
        return node.depth == G[0].length;
    }

    @Override
    public double calcCX(int depth, APNode t) {
        return t.currentNodeProfit;
    }

    @Override
    public double calcUX(int depth, APNode t) {
        if (depth == 0)
            return 1000000000;
        else
            return answerVal;
    }

    public double cost(ArrayList<Node<APNode>> branch) {
        return branch.get(0).x.currentNodeProfit;
    }

    public void report(ArrayList<Node<APNode>> answer, Strategy strategy) {
        System.out.println("Cost: "+answerVal);
        System.out.println("Strategy: "+strategy);
        int j = 0;
        for (int i = answer.size() - 2; i >= 0; i--) {
            System.out.println("Job "+j+" -> "+"Worker "+answer.get(i).x.val);
            j++;
        }
        System.out.println("Number of nodes generated: " + numberOfNodes);
        System.out.println();
    }

    public void solve(Strategy strategy) {
        APNode root = new APNode(-1, 0);
        ArrayList<Node<APNode>> answer =solve(strategy,root);
        report(answer, strategy);
    }
}