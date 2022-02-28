package assignmentProblemBT;

import java.util.ArrayList;

public class AssignmentProblem extends BackTrackingOptimization<Integer> {
	private double[][] G;
	private double[] currentNodeProfit;

	public AssignmentProblem(double[][] G) {

		super(new Integer[G.length], new Integer[G.length]);
		this.G = G;
		currentNodeProfit = new double[G.length];
	}

	@Override
	protected double cost(int k) {
		currentNodeProfit[k] = currentNodeProfit[k - 1] + G[x[k - 1]][x[k]];
		return -currentNodeProfit[k];

	}

	@Override
	protected Integer[] nodeValues(int k) {
		if (k == G[0].length)
			return null;
		k++;
		ArrayList<Integer> nextValues = new ArrayList<Integer>();

		for (int val = 1; val < x.length; val++) {
			if (G[x[k - 1]][val] != 0) {
				int i;
				for (i = 0; i <= k - 1; i++) {
					if (x[i] == val)
						break;
				}
				if (i == k) {
					if ((k < x.length - 1) || ((k == x.length - 1) && (G[val][x[0]] != 0))) {
						nextValues.add(val);

					}
				}
			}
		}

		return (Integer[]) nextValues.toArray(new Integer[nextValues.size()]);
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
		x[0] = 0;
		solve(1);
	}

	public void print() {
		System.out.print("min cost: ");
		for (int i = 0; i < finalX.length; i++) {
			System.out.print(finalX[i] + " , ");
		}
		System.out.println(" cost = " + -finalProfit);

	}

}