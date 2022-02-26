package JobScheduling;

import java.util.Arrays;

public class JSProgram {
    public static void main(String[] args) {
        int[] deadlines = {1, 12, 4, 3, 6};
        int[] profits = {4, 8, 3, 1, 5};
        int[] exec = {1, 9, 2, 1, 3};
        JobScheduling1 js1 = new JobScheduling1(profits, deadlines, exec);
        js1.solve();
        js1.report();
        System.out.println();
        JobScheduling2 js2 = new JobScheduling2(profits, deadlines, exec);
        js2.solve();
        js2.report();
        System.out.println();
        JobScheduling3 js3 = new JobScheduling3(profits, deadlines, exec);
        js3.solve();
        js3.report();
    }
}
