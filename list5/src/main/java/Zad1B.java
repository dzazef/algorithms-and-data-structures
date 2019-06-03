import graph.HyperCube;
import network.FlowNetwork;

public class Zad1B {
    public static void main(String[] args) {
        if (args.length != 2 || !args[0].equals("--size")) {
            System.err.println("Usage: ./zad1 --size k");
        }
        int k = Integer.parseInt(args[1]);
        HyperCube hyperCube = new HyperCube(k);
        FlowNetwork flowNetwork = new FlowNetwork(hyperCube, 0, hyperCube.getVerticesCount()-1);
        var begin = System.nanoTime();
        flowNetwork.countMaximumFlow();
        var end = System.nanoTime();
        System.err.println("Hypercube size: " + k);
        System.err.println("Augmenting paths: " + flowNetwork.getAugmentingPaths());
        System.err.println("Time[ms]: " + (end - begin) / 1000000);
        System.out.println("Result: " + flowNetwork.getMaxFlow());
    }
}
