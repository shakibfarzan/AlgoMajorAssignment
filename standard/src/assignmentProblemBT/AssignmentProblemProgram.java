package assignmentProblemBT;

public class AssignmentProblemProgram {
    public static void main(String[] args) {
        double[][] C = new double[][]{
                {82, 83, 69, 92},
                {77, 37, 49, 92},
                {11, 69, 5, 86},
                {8, 9, 98, 23}
        };

        double[][] D = new double[][]{
                {9, 3, 4, 7, 6},
                {3, 2, 3, 1, 2},
                {5, 3, 3, 1, 6},
                {7, 4, 1, 0, 5},
                {9, 2, 6, 2, 0}
        };

        double[][] G = new double[][]{
                {0, 6, 0, 3},
                {6, 0, 3, 4},
                {0, 3, 1, 1},
                {3, 4, 1, 4},
                {6, 2, 6, 1}
        };

        double[][] T = new double[][]{
                {2, 9, 10, 30, 16},
                {6, 12, 3, 14, 2},
                {0, 13, 8, 1, 16},
                {13, 14, 1, 0, 13},
                {6, 2, 6, 3, 7}
        };

        AssignmentProblem assignment = new AssignmentProblem(C);
        assignment.solve();
        assignment.print();
        AssignmentProblem assignment2 = new AssignmentProblem(D);
        assignment2.solve();
        assignment2.print();
        AssignmentProblem assignment3 = new AssignmentProblem(G);
        assignment3.solve();
        assignment3.print();
        AssignmentProblem assignment4 = new AssignmentProblem(T);
        assignment4.solve();
        assignment4.print();
    }
}
