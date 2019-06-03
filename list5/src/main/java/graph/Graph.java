package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    int verticesCount;
    List<Edge>[] edges;

    public Graph(int verticesCount) {
        this.verticesCount = verticesCount;
        //noinspection unchecked
        edges = (ArrayList<Edge> []) new ArrayList[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            edges[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(int start, int end, int capacity) {
        Edge edge = new Edge(start, end, capacity, 0);
        edges[start].add(edge);
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public void printGraph() {
        for (int i = 0; i < edges.length; i++) {
            for (Edge edge : edges[i]) {
                System.out.println("[" + i + " -> " + edge.getEndVertex() + ": " + edge.getCapacity() + "]");
            }
        }
    }

    public void resetFlow() {
        for (List<Edge> edgeList : edges) {
            for (Edge edge : edgeList) {
                edge.setFlow(0);
            }
        }
    }

    public List<Edge> getNeighbours(int i) {
        return edges[i];
    }
}
