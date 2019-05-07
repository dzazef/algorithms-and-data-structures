package kruskal

import graph.WeightedEdge

data class UndirectedWeightedEdgeKruskal(val vertex1: Int, val vertex2: Int, val weight: Double) : WeightedEdge {
    override fun toString(): String {
        return "($vertex1 -> $vertex2 w: $weight)"
    }
}
