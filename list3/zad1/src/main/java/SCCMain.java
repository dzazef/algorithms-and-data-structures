import scc.DirectedGraphSCC;
import scc.SCC;

import java.util.Scanner;

public class SCCMain {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int vertices = s.nextInt();
        DirectedGraphSCC graph = new DirectedGraphSCC(vertices);
        System.out.print("Number of edges: ");
        int edges = s.nextInt();
        for (int i = 0; i < edges; i++) {
            System.out.print("Edge "+i+": ");
            graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()));
        }
        SCC scc = new SCC(graph);
        long startTime = System.nanoTime();
        scc.sccSearch();
        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("Time: "+((endTime - startTime)/1000000)+" ms");
    }
}
