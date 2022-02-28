package assignmentProblemBT;

public class AssignmentProblemProgram {
    public static void main(String[] args) {
//        0 = infinity
        double[][] C = new double[][]{
                {0, 6, 1, 3, 6},
                {6, 0, 3, 4, 2},
                {0, 3, 0, 1, 6},
                {3, 4, 1, 0, 3},
                {6, 2, 6, 3, 0}};

        double[][] D = new double[][]{
                {0, 3, 4, 7, 6},
                {3, 0, 3, 1, 2},
                {5, 3, 0, 1, 6},
                {7, 4, 1, 0, 5},
                {9, 2, 6, 2, 0}};

        double[][] G = new double[][]{
                {0, 6, 0, 3, 6},
                {6, 0, 3, 4, 2},
                {0, 3, 0, 1, 6},
                {3, 4, 1, 0, 3},
                {6, 2, 6, 3, 0}};

        double[][] T = new double[][]{
                {0, 9, 10, 30, 16},
                {6, 0, 3, 14, 2},
                {0, 13, 0, 1, 16},
                {13, 14, 1, 0, 13},
                {6, 2, 6, 3, 0}};

        AssignmentProblem assignment = new AssignmentProblem(G);
        assignment.solve();
        assignment.print();
        AssignmentProblem assignment2 = new AssignmentProblem(T);
        assignment2.solve();
        assignment2.print();
        AssignmentProblem assignment3 = new AssignmentProblem(D);
        assignment3.solve();
        assignment3.print();
        AssignmentProblem assignment4 = new AssignmentProblem(C);
        assignment4.solve();
        assignment4.print();
    }
}
