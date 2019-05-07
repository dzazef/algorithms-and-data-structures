package graph

data class UndirectedWeightedEdge(var vertex1 : Int, var vertex2 : Int, val weight : Int) : WeightedEdge{
    override fun toString(): String {
        return "$vertex1 <-> $vertex2 w: $weight"
    }
}
