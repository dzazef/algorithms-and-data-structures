import kruskal.Kruskal;
import kruskal.UndirectedWeightedGraphKruskal;
import prima.Prima;
import prima.UndirectedWeightedGraphPrima;

import java.util.Scanner;

public class Zad3Main {
//    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        System.out.print("Number of vertices: ");
//        UndirectedWeightedGraphKruskal graph = new UndirectedWeightedGraphKruskal(s.nextInt());
//        System.out.print("Number of edges: ");
//        int edges = s.nextInt();
//        for (int i = 0; i < edges; i++) {
//            System.out.print("Edge "+i+": ");
//            graph.addEdge(Integer.parseInt(s.next()), Integer.parseInt(s.next()), Double.parseDouble(s.next()));
//        }
//
//        Kruskal kruskal = new Kruskal(graph);
//        kruskal.printMST();
//    }
    public static void main(String[] args) {
        UndirectedWeightedGraphPrima graph = new UndirectedWeightedGraphPrima(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(3, 5, 14);
        graph.addEdge(2, 5, 4);
        graph.addEdge(8, 6, 6);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 2, 2);
        Prima prima = new Prima(graph);
        prima.mst(0);
    }
//    public static void main(String[] args) {
//        UndirectedWeightedGraphKruskal graph = new UndirectedWeightedGraphKruskal(9);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(1, 2, 8);
//        graph.addEdge(2, 3, 7);
//        graph.addEdge(3, 4, 9);
//        graph.addEdge(4, 5, 10);
//        graph.addEdge(5, 6, 2);
//        graph.addEdge(6, 7, 1);
//        graph.addEdge(7, 8, 8);
//        graph.addEdge(1, 7, 11);
//        graph.addEdge(3, 5, 14);
//        graph.addEdge(2, 5, 4);
//        graph.addEdge(8, 6, 6);
//        graph.addEdge(7, 8, 7);
//        graph.addEdge(8, 2, 2);
//        Kruskal kruskal = new Kruskal(graph);
//        kruskal.mst();
//        kruskal.printMST();
//    }
}
