package structures.rbt

data class RBTNode<T : Comparable<T>>(
    var key : T?,
    var left : RBTNode<T>? = null,
    var right: RBTNode<T>? = null,
    var parent: RBTNode<T>? = null,
    var color: Boolean) {
    override fun toString(): String {
        return "[$key l: $left r: $right c: ${if (color) "BLACK" else "RED"}]"
    }
}