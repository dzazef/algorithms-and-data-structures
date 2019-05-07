import scc.DirectedGraphSCC;
import scc.SCC;

public class SCCMain {
    public static void main(String[] args) {
        DirectedGraphSCC graph = new DirectedGraphSCC(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 6);
        SCC scc = new SCC(graph);
        scc.sccSearch();
    }
}
