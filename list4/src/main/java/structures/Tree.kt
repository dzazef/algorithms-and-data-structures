package structures

import java.io.File

abstract class Tree<T : Comparable<T>> : Statistics<T>() {
    abstract fun insert(key : T)
    abstract fun delete(key : T)
    abstract fun search(key : T) : Boolean
    abstract fun load(f : File)
    abstract fun inorder()
}