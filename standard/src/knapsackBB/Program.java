package knapsackBB;

import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
       Coordinator coordinator = new Coordinator();
        try {
            coordinator.experiment(3000, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
