package scc;

import java.util.HashSet;

public class DirectedGraphSCC {
    private HashSet<DirectedEdgeSCC>[] edgeList;

    public DirectedGraphSCC(int vertices) {
        //noinspection unchecked
        edgeList = new HashSet[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeList[i] = new HashSet<>();
        }
    }

    public void addEdge(DirectedEdgeSCC edge) {
        edgeList[edge.getBegin()].add(edge);
    }

    public void addEdge(int begin, int end) {
        edgeList[begin].add(new DirectedEdgeSCC(begin, end));
    }

    public HashSet<DirectedEdgeSCC> getNeighbours(int vertex) {
        return edgeList[vertex];
    }

    public int verticesCount() {
        return edgeList.length;
    }

    public HashSet<DirectedEdgeSCC>[] getEdgeSet() {
        return edgeList;
    }
}
