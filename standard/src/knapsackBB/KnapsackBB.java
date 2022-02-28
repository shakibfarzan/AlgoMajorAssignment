package knapsackBB;

import java.util.*;

public class KnapsackBB<T extends Collection<Node>> {
    protected T nodes;
    protected int upper;
    protected int[] weights;
    protected int[] profits;
    protected int capacity;
    protected int numberOfNodes = 1;

    public KnapsackBB(int[] w, int[] p, int capacity, T nodes) {
        weights = w;
        profits = p;
        sort();
        this.capacity = capacity;
        this.nodes = nodes;
        initRoot();
    }

    protected void sort() {
        double[][] data = new double[weights.length][3];
        for (int i = 0; i < weights.length; i++) {
            data[i][0] = weights[i];
            data[i][1] = profits[i];
            data[i][2] = (double) weights[i] / profits[i];
        }
        Arrays.sort(data, Comparator.comparingDouble(a -> a[2]));
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (int) data[i][0];
            profits[i] = (int) data[i][1];
        }
    }

    protected Node getLastNode() {
        if (nodes instanceof PriorityQueue) { // LC
            return ((PriorityQueue<Node>) nodes).poll();
        } else if (nodes instanceof Deque) { // FIFO
            return ((Deque<Node>) nodes).pollFirst();
        } else if (nodes instanceof Stack) { // LIFO
            return ((Stack<Node>) nodes).pop();
        }
        return null;
    }

    protected void addNodes(Node lastNode) {
        // if item is selected
        Node firstNode = new Node(lastNode.c, lastNode.U, lastNode.index + 1,
                lastNode.itemsIndices, lastNode.currentTotalWeight + weights[lastNode.index + 1]);
        if (!bound(firstNode)) {
            nodes.add(firstNode);
            numberOfNodes++;
        }


        // if item is not selected
        int[] indices = new int[lastNode.itemsIndices.length];
        System.arraycopy(lastNode.itemsIndices, 0, indices, 0, indices.length);
        indices[lastNode.index + 1] = -1;
        double c = 0;
        int u = 0, weight = 0;
        for (int i = 0; i < lastNode.itemsIndices.length; i++) {
            if (indices[i] != -1) {
                weight += weights[i];
                if (weight <= capacity) {
                    c += profits[i];
                    u += profits[i];
                } else {
                    c += (1 - (double) (weight - capacity) / weights[i]) * profits[i];
                    break;
                }
            }
        }

//        for (int i = 0; i < indices.length; i++) {
//            indices[i] = lastNode.itemsIndices[i];
//        }
        Node secondNode = new Node(-c, -u, lastNode.index + 1, indices, lastNode.currentTotalWeight);
        if (secondNode.U < upper) upper = secondNode.U;
        nodes.add(secondNode);
        numberOfNodes++;
    }

    protected boolean bound(Node node) {
        return (node.currentTotalWeight > capacity) || (node.c >= upper);
    }

    protected void initRoot() {
        double c = 0;
        int u = 0, weight = 0;
        for (int i = 0; i < weights.length; i++) {
            weight += weights[i];
            if (weight <= capacity) {
                c += profits[i];
                u += profits[i];
            } else {
                c += (1 - (double) (weight - capacity) / weights[i]) * profits[i];
                break;
            }
        }
        Node root = new Node(-c, -u, -1, new int[weights.length], 0);
        nodes.add(root);
        upper = root.U;
    }

    protected boolean isSolution(Node node) {
        return node.index == weights.length - 1;
    }

    protected int solve() {
        while (!nodes.isEmpty()) {
            Node currentNode = getLastNode();
            if (!bound(currentNode) && !isSolution(currentNode)) {
                addNodes(currentNode);
            }
        }
        return -upper;
    }

}
