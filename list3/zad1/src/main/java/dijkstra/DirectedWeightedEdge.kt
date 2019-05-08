package dijkstra

import graph.WeightedEdge

data class DirectedWeightedEdge(val begin: Int, val end: Int, val weight: Double) : WeightedEdge {
    override fun toString(): String {
        return "($begin -> $end w: $weight)"
    }
}
