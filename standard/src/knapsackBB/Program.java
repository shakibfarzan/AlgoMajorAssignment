package knapsackBB;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        int[] profits = { 6, 10, 18,43,34,32,12 ,6, 10, 18,43,34,32,12,6, 10, 18,43,34,32,12,6, 10, 18,43,34,32,12 ,6, 10, 18,43,34,32,12,6, 10, 18,43,34,32,12};
        int[] weights = { 9, 22, 20,24,5,23,43,9, 22, 20,24,5,23,43,9, 22, 20,24,5,23,43,9, 22, 20,24,5,23,43,9, 22, 20,24,5,23,43,9, 22, 20,24,5,23,43 };
        KnapsackLC lc = new KnapsackLC(weights, profits, 70);
        System.out.println(lc.solve());
        KnapsackFIFO fifo = new KnapsackFIFO(weights, profits, 70);
        System.out.println(fifo.solve());
    }
}
