package structures.splay

import structures.Statistics
import structures.Tree
import structures.bst.BSTNode
import java.io.File

class Splay<T : Comparable<T>>(private var root : BSTNode<T>? = null) : Tree<T>, Statistics<T>() {
    override fun getRoot(): BSTNode<T>? {
        TODO("not implemented")
    }

    override fun insert(key: T) {
        TODO("not implemented")
    }

    override fun delete(key: T) {
        TODO("not implemented")
    }

    override fun search(key: T): Boolean {
        TODO("not implemented")
    }

    override fun load(f: File) {
        TODO("not implemented")
    }

    override fun inorder() {
        TODO("not implemented")
    }
}