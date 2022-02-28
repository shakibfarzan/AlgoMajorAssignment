package knapsackBB;

import java.util.ArrayDeque;
import java.util.Deque;

public class KnapsackFIFO extends KnapsackBB<Deque<Node>>{

    public KnapsackFIFO(int[] w, int[] p, int capacity) {
        super(w, p, capacity, new ArrayDeque<>());
    }
}
