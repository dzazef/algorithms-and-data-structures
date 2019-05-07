package prima;

import graph.UndirectedWeightedEdge;

import java.util.HashSet;

public class UndirectedWeightedGraphPrima {
    private HashSet<UndirectedWeightedEdgePrima>[] edgeList;

    public UndirectedWeightedGraphPrima(int vertices) {
        //noinspection unchecked
        edgeList = new HashSet[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeList[i] = new HashSet<>();
        }
    }

    public void addEdge(int begin, int end, int weight) {
        edgeList[begin].add(new UndirectedWeightedEdgePrima(begin, end, weight));
        edgeList[end].add(new UndirectedWeightedEdgePrima(end, begin, weight));
    }

    public HashSet<UndirectedWeightedEdgePrima> getNeighbours(int vertex) {
        return edgeList[vertex];
    }

    public int verticesCount() {
        return edgeList.length;
    }
}
