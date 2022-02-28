package knapsackBB;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KnapsackLC extends KnapsackBB<PriorityQueue<Node>> {
    public KnapsackLC(int[] w, int[] p, int capacity) {
        super(w, p, capacity, new PriorityQueue<>(Comparator.comparingDouble(n -> n.c)));
    }
}
