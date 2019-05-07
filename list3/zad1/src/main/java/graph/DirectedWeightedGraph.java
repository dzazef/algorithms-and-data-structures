package graph;

import java.util.ArrayList;

public class DirectedWeightedGraph {
    private ArrayList<DirectedWeightedEdge>[] edgeList;

    public DirectedWeightedGraph(int vertices) {
        //noinspection unchecked
        edgeList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeList[i] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedWeightedEdge edge) {
        edgeList[edge.getBegin()].add(edge);
    }

    public void addEdge(int begin, int end, int weight) {
        edgeList[begin].add(new DirectedWeightedEdge(begin, end, weight));
    }

    public ArrayList<DirectedWeightedEdge> getNeigbours(int vertex) {
        return edgeList[vertex];
    }

    public int veritcesCount() {
        return edgeList.length;
    }
}
