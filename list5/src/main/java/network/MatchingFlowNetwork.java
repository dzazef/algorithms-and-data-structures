package network;

import graph.Graph;

public class MatchingFlowNetwork extends FlowNetwork{

    public MatchingFlowNetwork(Graph graph, int s, int t) {
        super(graph, s, t);
    }

    public int getMatchingSize(int k2) {
        edgeList.removeIf(edge -> edge.getStartVertex() == 0 || edge.getEndVertex() == (1 + 2 * k2));
        return edgeList.size();
    }

}
