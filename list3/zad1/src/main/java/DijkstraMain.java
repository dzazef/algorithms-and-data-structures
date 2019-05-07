import dijkstra.Dijkstra;
import graph.DirectedWeightedGraph;

import java.util.Arrays;

public class DijkstraMain {
    public static void main(String[] args) {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
        graph.addEdge(0, 1, 15);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 25);
        graph.addEdge(3, 4, 35);
        graph.addEdge(4, 0, 15);

        Dijkstra dijkstra = new Dijkstra(graph);

        dijkstra.shortestPath(0);
        dijkstra.printPathWeights();
        System.out.flush();
        dijkstra.printWholePath();
    }
}
