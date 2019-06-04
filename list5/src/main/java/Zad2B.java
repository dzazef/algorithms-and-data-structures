import graph.BipartiteGraph;
import network.MatchingFlowNetwork;

public class Zad2B {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: ./zad2 --size k --degree m");
            System.exit(1);
        }
        int k = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[3]);

        BipartiteGraph bipartiteGraph = new BipartiteGraph(k, m);
        bipartiteGraph.printGraph(true);
        MatchingFlowNetwork matchingFlowNetwork = new MatchingFlowNetwork(bipartiteGraph, 0, 1 + (1 << (k+1)));
        var begin = System.nanoTime();
        matchingFlowNetwork.countMaximumFlow();
        var end = System.nanoTime();
        System.err.println("K: " + k + ", M: " + m);
        System.err.println("Time: " + (end - begin) / 1000000);
        System.out.println(matchingFlowNetwork.getMatchingSize((1 << k)));
    }
}
