package scc;

import java.util.HashSet;
import java.util.Stack;

public class SCC {

    private DirectedGraphSCC graph;
    private boolean[] visited;
    private Stack<Integer> verticesStack;

    public SCC(DirectedGraphSCC graph) {
        this.graph = graph;
        this.visited = new boolean[graph.verticesCount()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        verticesStack = new Stack<>();
    }

    private void DFSstack(int v, DirectedGraphSCC graph) {
        visited[v] = true;
        for (DirectedEdgeSCC edge : graph.getNeighbours(v)) {
            if (!visited[edge.getEnd()]) DFSstack(edge.getEnd(), graph);
        }
        verticesStack.push(v);
    }

    private void DFSprint(int v, DirectedGraphSCC graphT) {
        visited[v] = true;
        System.out.print(v+" ");
        for (DirectedEdgeSCC edge : graphT.getNeighbours(v)) {
            if (!visited[edge.getEnd()]) DFSprint(edge.getEnd(), graphT);
        }
    }

    private DirectedGraphSCC transpose() {
        DirectedGraphSCC res = new DirectedGraphSCC(graph.verticesCount());
        for (HashSet<DirectedEdgeSCC> edgeSet : graph.getEdgeSet()) {
            for (DirectedEdgeSCC edge : edgeSet) {
                res.addEdge(edge.getEnd(), edge.getBegin());
            }
        }
        return res;
    }

    public void sccSearch() {
        for (int i = 0; i < graph.verticesCount(); i++) {
            if (!visited[i]) DFSstack(i, graph);
        }
        DirectedGraphSCC graphT = transpose();
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int cn = 0;
        while(!verticesStack.empty()) {
            int v = verticesStack.pop();
            if (visited[v]) continue;
            cn++;
            System.out.println();
            System.out.print("SCC "+cn+": ");
            DFSprint(v, graphT);
        }

    }



}
