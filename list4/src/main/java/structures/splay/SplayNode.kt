package structures.splay

data class SplayNode<T : Comparable<T>>(
    var key : T?,
    var left : SplayNode<T>? = null,
    var right: SplayNode<T>? = null,
    var parent: SplayNode<T>? = null) {
    override fun toString(): String {
        return "[$key l: $left r: $right]"
    }
}