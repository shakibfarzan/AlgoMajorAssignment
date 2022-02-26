package JobScheduling;

public class JSProgram {
    public static void main(String[] args) {
        int[] deadlines = {1, 12, 4, 3, 6};
        int[] profits = {4, 8, 3, 1, 5};
        int[] exec = {1, 9, 2, 1, 3};
        JobScheduling1 js1 = new JobScheduling1(profits, deadlines, exec);
        js1.solve();
        js1.report();
    }
}
