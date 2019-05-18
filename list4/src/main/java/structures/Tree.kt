package structures

import structures.bst.BSTNode
import java.io.File

interface Tree<T : Comparable<T>> {
    fun insert(key : T)
    fun delete(key : T)
    fun search(key : T) : Boolean
    fun load(f : File)
    fun inorder()
}