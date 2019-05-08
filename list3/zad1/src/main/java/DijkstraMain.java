import dijkstra.Dijkstra;
import dijkstra.DirectedWeightedGraph;

import java.util.Scanner;

// e log v
// e log v prim
// e log v krusk
// e + v kosr
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
            graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()), Double.parseDouble(s.next()));
        }
        System.out.print("Start vertex: ");
        Dijkstra dijkstra = new Dijkstra(graph);
        long startTime = System.nanoTime();
        dijkstra.shortestPath(s.nextInt());
        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("Time: "+((endTime - startTime)/1000000)+" ms");
        s.close();

        dijkstra.printPathWeights();
        System.out.flush();
        dijkstra.printWholePath();
    }
}
