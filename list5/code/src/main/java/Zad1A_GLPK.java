import glpk.MaxFlowModel;
import graph.HyperCube;

import java.io.IOException;

public class Zad1A_GLPK {
    public static void main(String[] args) throws IOException {
        for (int k = 1; k <= 16; k++) {
            HyperCube hyperCube = new HyperCube(k);
            MaxFlowModel maxFlowModel = new MaxFlowModel(hyperCube);
            maxFlowModel.write("maxflow_data_" + k + ".dat");
        }
    }
}
