package knapsackBB;

public class Node {
    int c;
    int U;
    int index;
    int[] itemsIndices;
    int currentTotalWeight;
    public Node(int c, int U, int index, int[] itemsIndices, int currentTotalWeight){
        this.c = c;
        this.U = U;
        this.index = index;
        this.itemsIndices = itemsIndices;
        this.currentTotalWeight = currentTotalWeight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "c=" + c +
                ", U=" + U +
                ", index=" + index +
                '}';
    }
}
