import graph.BipartiteGraph;
import network.MatchingFlowNetwork;

public class Zad2B {
    public static void main(String[] args) {
        int k = 3;
        int m = 1;
        BipartiteGraph bipartiteGraph = new BipartiteGraph(k, m);
        bipartiteGraph.printGraph();
        MatchingFlowNetwork matchingFlowNetwork = new MatchingFlowNetwork(bipartiteGraph, 0, 1 + (1 << (k+1)));
        matchingFlowNetwork.countMaximumFlow();
        matchingFlowNetwork.removeUnnecessaryEdges((1 << k));
        System.out.println(matchingFlowNetwork.getEdgeList());
        System.out.println(matchingFlowNetwork.getMatchingSize());
    }
}
