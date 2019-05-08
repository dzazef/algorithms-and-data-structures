import kruskal.Kruskal;
import kruskal.UndirectedWeightedGraphKruskal;
import prima.Prima;
import prima.UndirectedWeightedGraphPrima;

import java.util.Scanner;

public class Zad3Main {
    public static void main(String[] args) {
        if (args.length != 1) System.exit(1);
        Scanner s = new Scanner(System.in);
        System.out.print("Number of vertices: ");
        if (args[0].equals("-k")) {
            UndirectedWeightedGraphKruskal graph = new UndirectedWeightedGraphKruskal(s.nextInt());
            System.out.print("Number of edges: ");
            int edges = s.nextInt();
            for (int i = 0; i < edges; i++) {
                System.out.print("Edge "+i+": ");
                graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()), Double.parseDouble(s.next()));
            }
            Kruskal kruskal = new Kruskal(graph);
            long startTime = System.nanoTime();
            kruskal.mst();
            long endTime = System.nanoTime();
            System.out.println();
            System.out.println("Time: "+((endTime - startTime)/1000000)+" ms");
            kruskal.printMST();
        } else if (args[0].equals("-p")) {
            UndirectedWeightedGraphPrima graph = new UndirectedWeightedGraphPrima(s.nextInt());
            System.out.print("Number of edges: ");
            int edges = s.nextInt();
            for (int i = 0; i < edges; i++) {
                System.out.print("Edge "+i+": ");
                graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()), Double.parseDouble(s.next()));
            }
            Prima prima = new Prima(graph);
            long startTime = System.nanoTime();
            prima.mst(0);
            long endTime = System.nanoTime();
            System.out.println();
            System.out.println("Time: "+((endTime - startTime)/1000000)+" ms");
            prima.printMST();
        }
    }
}
