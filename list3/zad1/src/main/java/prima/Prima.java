package prima;

import priority_queue.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;

public class Prima {

    private UndirectedWeightedGraphPrima graph;
    private HashMap<Integer, Boolean> visited;
    private double[] key;
    private int[] pi;

    public Prima(UndirectedWeightedGraphPrima graph) {
        this.graph = graph;
        visited = new HashMap<>();
    }

    public void mst(int root) {
        key = new double[graph.verticesCount()];
        pi = new int[graph.verticesCount()];

        for (int i = 0; i < graph.verticesCount(); i++) {
            key[i] = Integer.MAX_VALUE;
            pi[i] = -1;
            visited.put(i, false);
        }

        key[root] = 0;

        PriorityQueue<Double> Q = new PriorityQueue<>(graph.verticesCount(), Double.MAX_VALUE);

        for (int i = 0; i < graph.verticesCount(); i++) {
            Q.insert(i, key[i]);
        }

        while (!Q.empty()) {
            int u = Q.pop();
            visited.put(u, true);
            for (UndirectedWeightedEdgePrima edge : graph.getNeighbours(u)) {
                if ((!visited.get(edge.getVertex2())) && edge.getWeight() < key[edge.getVertex2()]) {
                    pi[edge.getVertex2()] = u;
                    key[edge.getVertex2()] = edge.getWeight();
                    Q.priority(edge.getVertex2(), edge.getWeight());
                }
            }
        }

        System.out.println(Arrays.toString(pi));
        System.out.println(Arrays.toString(key));
    }

    public void printMST() {
        if (key == null) mst(0);
    }

}
