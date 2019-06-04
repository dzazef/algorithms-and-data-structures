package glpk;

import graph.Edge;
import graph.Graph;

import java.io.*;

public class MaxFlowModel {

    private Graph graph;

    public MaxFlowModel(Graph graph) {
        this.graph = graph;
    }

    public void write(String name) throws IOException {
        FileWriter fw = new FileWriter(new File(name));
        fw.write("data;\n");
        fw.write("param n := " + graph.getVerticesCount() + ";\n");
        fw.write("param : E :   a :=\n");
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            for (Edge edge : graph.getNeighbours(i)) {
                fw.write((i+1) + " " + (edge.getEndVertex() + 1) + " " + (edge.getCapacity()) + "\n");
            }
        }
        fw.write(";");
        fw.close();
    }
}
