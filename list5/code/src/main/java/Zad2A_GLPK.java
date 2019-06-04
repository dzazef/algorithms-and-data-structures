import glpk.MaxFlowModel;
import graph.BipartiteGraph;

import java.io.IOException;

public class Zad2A_GLPK {
    public static void main(String[] args) throws IOException {
        for (int k = 1; k <= 10; k++) {
            for (int i = 1; i <= k; i++) {
                BipartiteGraph bipartiteGraph = new BipartiteGraph(k, i);
                MaxFlowModel maxFlowModel = new MaxFlowModel(bipartiteGraph);
                maxFlowModel.write("matching_data_" + k + "_" + i);
            }
        }
    }
}
