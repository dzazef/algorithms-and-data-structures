import glpk.MaxFlowModel;
import graph.HyperCube;
import network.FlowNetwork;

import java.io.IOException;

public class Zad1B {
    public static void main(String[] args) throws IOException {
        if ((args.length != 2 && args.length != 3) || !args[0].equals("--size")) {
            System.err.println("Usage: ./zad1 --size k --glpk");
        }
        int k = Integer.parseInt(args[1]);
        HyperCube hyperCube = new HyperCube(k);
        if (args.length == 3) {
            MaxFlowModel maxFlowModel = new MaxFlowModel(hyperCube);
            maxFlowModel.write("test2.mod");
        } else {
            hyperCube.printGraph(false);
            FlowNetwork flowNetwork = new FlowNetwork(hyperCube, 0, hyperCube.getVerticesCount() - 1);
            var begin = System.nanoTime();
            flowNetwork.countMaximumFlow();
            var end = System.nanoTime();
            System.err.println("Hypercube size: " + k);
            System.err.println("Augmenting paths: " + flowNetwork.getAugmentingPaths());
            System.err.println("Time[ms]: " + (end - begin) / 1000000);
            System.out.println("Result: " + flowNetwork.getMaxFlow());
        }
    }
}
