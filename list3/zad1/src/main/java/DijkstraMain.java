import dijkstra.Dijkstra;
import dijkstra.DirectedWeightedGraph;

import java.util.Scanner;

public class DijkstraMain {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Number of vertices: ");
        int vertices = s.nextInt();
        DirectedWeightedGraph graph = new DirectedWeightedGraph(vertices);
        System.out.print("Number of edges: ");
        int edges = s.nextInt();
        for (int i = 0; i < edges; i++) {
            System.out.print("Edge "+i+": ");
            graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()), Integer.parseInt(s.next()));
        }
        System.out.print("Start vertex: ");
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.shortestPath(s.nextInt());
        s.close();

        dijkstra.printPathWeights();
        System.out.flush();
        dijkstra.printWholePath();
    }
}
