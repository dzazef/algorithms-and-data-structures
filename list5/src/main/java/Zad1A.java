import graph.HyperCube;
import network.FlowNetwork;

public class Zad1A {
    private static int NUM_OF_TESTS = 100;
    public static void main(String[] args) {
        System.out.println(NUM_OF_TESTS + " tests");
        System.out.println("k;time;paths;flow");
        for (int i = 1; i <= 16; i++) {
            if (i == 12) NUM_OF_TESTS = 20;
            if (i == 14) NUM_OF_TESTS = 10;
            double millis = 0;
            double paths = 0;
            double flow = 0;
            for (int j = 0; j < NUM_OF_TESTS; j++) {
                var hyperCube = new HyperCube(i);
                var flowNetwork = new FlowNetwork(hyperCube, 0, hyperCube.getVerticesCount()-1);
                var begin = System.nanoTime();
                flowNetwork.countMaximumFlow();
                var end = System.nanoTime();
                millis += (end - begin) / 1000000;
                paths += flowNetwork.getAugmentingPaths();
                flow += flowNetwork.getMaxFlow();
            }
            System.out.println(i + ";" + (millis / NUM_OF_TESTS) + ";" + (paths / NUM_OF_TESTS) + ";" + (flow / NUM_OF_TESTS));
        }
    }
}
