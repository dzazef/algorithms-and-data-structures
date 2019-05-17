package kruskal;

import java.util.ArrayList;
import java.util.Comparator;

public class UndirectedWeightedGraphKruskal {

    private int verticesCount;
    private ArrayList<UndirectedWeightedEdgeKruskal> edgeList = new ArrayList<>();

    public UndirectedWeightedGraphKruskal(int verticesCount) {
        this.verticesCount = verticesCount;
    }


    public void addEdge(int u, int v, double w) {
        if (u < v) {
            edgeList.add(new UndirectedWeightedEdgeKruskal(u, v, w));
        } else {
            edgeList.add(new UndirectedWeightedEdgeKruskal(v, u, w));
        }
    }

    public void sort() {
        edgeList.sort(Comparator.comparingDouble(UndirectedWeightedEdgeKruskal::getWeight));
    }

    public ArrayList<UndirectedWeightedEdgeKruskal> getEdgeList() {
        return edgeList;
    }

    public int verticesCount() {
        return verticesCount;
    }

    @Override
    public String toString() {
        return edgeList.toString();
    }
}
