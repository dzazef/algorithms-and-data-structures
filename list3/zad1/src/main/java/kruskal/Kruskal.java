package kruskal;

import graph.UndirectedWeightedEdge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Kruskal {

    private UndirectedWeightedGraphKruskal graph;
    private ArrayList<HashSet<Integer>> vertexSetList;
    private ArrayList<UndirectedWeightedEdgeKruskal> mst;

    public Kruskal(UndirectedWeightedGraphKruskal graph) {
        this.graph = graph;
        vertexSetList = new ArrayList<>();
    }

    public ArrayList<UndirectedWeightedEdgeKruskal> mst() {
        graph.sort();
        ArrayList<UndirectedWeightedEdgeKruskal> edgeSet = new ArrayList<>();

        for (int i = 0; i < graph.verticesCount(); i++) {
            makeSet(i);
        }

        for (UndirectedWeightedEdgeKruskal edge : graph.getEdgeList()) {
            int find1 = find(edge.getVertex1());
            int find2 = find(edge.getVertex2());
            if (find1 != find2) {
                edgeSet.add(edge);
                union(find1, find2);
            }
        }
        mst = edgeSet;
        return edgeSet;
    }

    public void printMST() {
        if (mst == null) mst();
        mst.sort(Comparator.comparingInt(UndirectedWeightedEdgeKruskal::getVertex1));
        int weightSum = 0;
        for (UndirectedWeightedEdgeKruskal edge : mst) {
            weightSum += edge.getWeight();
            System.out.println(edge);
        }
        System.out.println("Weight sum: "+weightSum);
    }

    private void makeSet(int v) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(v);
        vertexSetList.add(hashSet);
    }

    private int find(int v) {
        for (int i = 0; i < vertexSetList.size(); i++) {
            for (Integer j : vertexSetList.get(i)) {
                if (j==v) return i;
            }
        }
        return -1;
    }

    private void union(int u, int v) {
        HashSet<Integer> setU = vertexSetList.get(u);
        HashSet<Integer> setV = vertexSetList.get(v);
        setU.addAll(setV);
        vertexSetList.remove(v);
    }
}
