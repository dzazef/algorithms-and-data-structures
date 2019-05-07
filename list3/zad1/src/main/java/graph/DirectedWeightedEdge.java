package graph;

public class DirectedWeightedEdge {
    private int begin;
    private int end;
    private int weight;

    public DirectedWeightedEdge(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "("+ begin +" -> "+ end +" w: "+weight+")";
    }
}
