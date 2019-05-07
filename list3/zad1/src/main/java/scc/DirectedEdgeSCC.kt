package scc

data class DirectedEdgeSCC(val begin: Int, val end: Int) {
    override fun toString(): String {
        return "($begin -> $end)"
    }
}
