package dijkstra;

import graph.DirectedWeightedEdge;

import java.util.HashSet;

public class DirectedWeightedGraphDijkstra {
    private HashSet<DirectedWeightedEdge>[] edgeList;

    public DirectedWeightedGraphDijkstra(int vertices) {
        //noinspection unchecked
        edgeList = new HashSet[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeList[i] = new HashSet<>();
        }
    }

    public void addEdge(DirectedWeightedEdge edge) {
        edgeList[edge.getBegin()].add(edge);
    }

    public void addEdge(int begin, int end, int weight) {
        edgeList[begin].add(new DirectedWeightedEdge(begin, end, weight));
    }

    public HashSet<DirectedWeightedEdge> getNeighbours(int vertex) {
        return edgeList[vertex];
    }

    public int verticesCount() {
        return edgeList.length;
    }
}
