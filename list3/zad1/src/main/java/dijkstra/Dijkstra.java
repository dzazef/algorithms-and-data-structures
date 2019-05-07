package dijkstra;

import priority_queue.PriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    private DirectedWeightedGraph graph;
    private int begin;
    private int[] d;
    private int[] pi;
    private PriorityQueue<Integer> Q;

    public Dijkstra(DirectedWeightedGraph graph) {
        this.graph = graph;
    }

    private void initializeSingleSource() {
        d = new int[graph.verticesCount()];
        pi = new int[graph.verticesCount()];
        for (int i = 0; i < graph.verticesCount(); i++) {
            d[i] = Integer.MAX_VALUE;
            pi[i] = -1;
        }
        d[begin] = 0;
    }

    private void relax(DirectedWeightedEdge edge) {
        if(d[edge.getEnd()] > d[edge.getBegin()] + edge.getWeight()) {
            d[edge.getEnd()] = d[edge.getBegin()] + edge.getWeight();
            pi[edge.getEnd()] = edge.getBegin();
            Q.priority(edge.getEnd(), d[edge.getEnd()]);
        }
    }

    public int[] shortestPath(int begin) {

        this.begin = begin;
        initializeSingleSource();

        Q = new PriorityQueue<>(graph.verticesCount(), Integer.MAX_VALUE);
        for (int i = 0; i < graph.verticesCount(); i++) {
            Q.insert(i, d[i]);
        }

        while (!Q.empty()) {
            int u = Q.pop();
            for (DirectedWeightedEdge edge : graph.getNeighbours(u)) {
                relax(edge);
            }
        }
        return d;
    }

    public void printPathWeights() {
        for (int i = 0; i < d.length; i++) {
            if (d[i] != Integer.MAX_VALUE) System.out.println(i+" "+d[i]);
        }
    }

    public void printWholePath() {
        for (int i = 0; i < pi.length; i++) {
            List<Integer> path = new ArrayList<>();
            path.add(i);
            int prev = pi[i];
            while (prev != -1) {
                path.add(prev);
                prev = pi[prev];
            }
            Collections.reverse(path);
            if (path.size() > 1) System.err.println("--- PATH TO "+i+" ---");
            for (int j = 0; j < path.size()-1; j++) {
                System.err.println(path.get(j)+" -> "+path.get(j+1)+" w: "+(d[path.get(j+1)] - d[path.get(j)]));
            }
        }
    }
}
