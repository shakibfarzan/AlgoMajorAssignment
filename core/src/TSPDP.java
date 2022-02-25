import java.util.*;

public class TSPDP {
    public static void main(String[] args) {
        int[][] graph = {
                {1000000000,10,15,20},
                {5,1000000000,9,10},
                {6,13,1000000000,12},
                {8,8,9,1000000000}
        };
        test(graph);
        graph = new int[][]{
                {1000000000, 8, 5, 2},
                {1, 1000000000, 19, 5},
                {15, 4, 1000000000, 6},
                {7, 2, 3, 1000000000}
        };
        test(graph);
        graph = new int[][]{
                {1000000000, 17, 8, 3, 9, 1, 9, 23},
                {9, 1000000000, 12, 3, 8, 1, 1000000000, 1000000000},
                {1000000000, 11, 1000000000, 4, 78, 2, 8, 2},
                {15, 9, 4, 1000000000, 22, 1000000000, 1, 9},
                {9, 10, 9, 3, 1000000000, 1000000000, 11, 25},
                {5, 1000000000, 2, 34, 1000000000, 1000000000, 1, 9},
                {90, 1, 15, 8, 9, 3, 1000000000, 2},
                {1, 3, 1000000000, 1000000000, 9, 8, 19, 1000000000}
        };
        test(graph);
        graph = new int[][]{
                {1000000000, 10, 1000000000, 2, 9},
                {9, 1000000000, 1000000000, 8, 7},
                {8, 14, 1000000000, 1, 18},
                {1000000000, 8, 13, 1000000000, 19},
                {10, 9, 1000000000, 13, 1000000000}
        };
        test(graph);
    }

    static HashMap<HashSet<Integer>, Integer>[] data;
    static HashMap<Integer, HashMap<HashSet<Integer>, Integer>> solution = new HashMap<>();

    private static int solve(HashSet<Integer> set, int[][] graph, int node) {
        if (data == null) {
            data = new HashMap[graph.length + 1];
        }
        if (set.size() == 0) {
            return graph[node][0];
        }
        if (data[node] != null && data[node].get(set) != null) {
            return data[node].get(set);
        }
        int min = 1000000000;
        HashSet<Integer> solSet = new HashSet<>();
        for (Integer currentNode : set) {
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.remove(currentNode);
            if (data[currentNode] == null) data[currentNode] = new HashMap<>();
            int cost = solve(newSet, graph, currentNode) + graph[node][currentNode];
            if (cost < min) {
                solSet = newSet;
                min = cost;
            }
        }
        if (data[node] == null) data[node] = new HashMap<>();
        data[node].put(set, min);
        solution.computeIfAbsent(solSet.size(), k -> new HashMap<>());
        solution.get(solSet.size()).put(solSet, min);
        return min;
    }

    private static int solve(int[][] graph) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < graph.length; i++) {
            set.add(i);
        }
        return solve(set, graph, 0);
    }

    private static ArrayList<Integer> getAnswer(int[][] graph) {
        HashSet<Integer> completeSet = new HashSet<>();
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i < graph.length; i++) {
            completeSet.add(i);
        }
        int firstNode = getAbsentNodeFromPrevSet(solution.get(graph.length - 2).keySet().stream().findFirst().get(), completeSet);
        answer.add(firstNode);
        int key = graph.length - 3;
        HashSet<Integer> prevSet = solution.get(graph.length - 2).keySet().stream().findFirst().get();
        while (answer.size() < graph.length - 1) {
            int min = 1000000000;
            int solNode = 0;
            HashSet<Integer> solSet = new HashSet<>();
            for (HashSet<Integer> set : solution.get(key).keySet()) {
                if(!set.containsAll(answer)){
                    if (set.size() == 0) {
                        answer.add(getLast(completeSet, answer));
                        return answer;
                    }
                    int currentNode = getAbsentNodeFromPrevSet(set, prevSet);
                    if (currentNode == -1) continue;
                    if (solution.get(key).get(set) + graph[firstNode][currentNode] < min) {
                        min = solution.get(key).get(set) + graph[firstNode][currentNode];
                        solNode = currentNode;
                        solSet = set;
                    }
                }
            }
            key--;
            prevSet = solSet;
            firstNode = solNode;
            answer.add(firstNode);
        }
        return answer;
    }

    private static void printAnswer(int[][] graph){
        ArrayList<Integer> answer = getAnswer(graph);
        System.out.print("0 -> ");
        for (Integer node: answer){
            System.out.print(node+" -> ");
        }
        System.out.println("0");
    }

    private static int getAbsentNodeFromPrevSet(HashSet<Integer> currentSet, HashSet<Integer> prevSet) {
        for (Integer node : prevSet) {
            if (!currentSet.contains(node)) return node;
        }
        return -1;
    }

    private static int getLast(HashSet<Integer> completeSet, ArrayList<Integer> answer){
        for (Integer e: answer){
            completeSet.remove(e);
        }
        return completeSet.stream().findFirst().get();
    }

    private static void test(int[][] graph){
        data = null;
        solution = new HashMap<>();
        System.out.println("\nGraph: ");
        for (int[] integers : graph) {
            for (int j = 0; j < graph.length; j++) {
                if (integers[j] == 1000000000) System.out.print("∞\t");
                else System.out.print(integers[j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\nCost: "+solve(graph));
        System.out.print("Path: ");
        printAnswer(graph);
        System.out.println();
    }
}
