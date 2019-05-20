package structures.bst

import structures.Statistics
import structures.Tree
import java.io.File
import java.util.*

class BST<T : Comparable<T>>(private var root : BSTNode<T>? = null) : Tree<T>()  {
    fun getRoot(): BSTNode<T>? = root

    override fun insert(key: T) {
        //If root is null insert new value in root
        val node = BSTNode(key)
        if (root == null) {
            root = node
            return
        }
        var parent : BSTNode<T>? = null
        var current = root
        while (current != null) {
            parent = current
            val comparision = compare(key, current.key!!)
            current = when {
                //If key is lesser then current node key search in left tree
                comparision < 0 -> current.left
                //If key is greater then current node key search in left tree
                comparision > 0 -> current.right
                //If key is same do nothing
                else -> return
            }
        }
        //When we found parent of the new node we put it in the right place
        val comparision = compare(key, parent!!.key!!)
        when {
            comparision < 0 -> parent.left = node
            else -> parent.right = node
        }
        notifyInsert()
    }

    override fun delete(key: T) {
        if (root == null) return
        val rootParent = BSTNode<T>(null)
        rootParent.right = root

        var left = false
        var p1: BSTNode<T> = rootParent
        var current : BSTNode<T>? = rootParent.right
        while (current != null) {
            //Find node to delete
            val comparision = compare(key, current.key!!)
            when {
                comparision < 0 -> {
                    p1 = current
                    current = current.left
                    left = true
                }
                comparision > 0 -> {
                    p1 = current
                    current = current.right
                    left = false
                }
                //Node found
                comparision == 0 -> {
                    //If node doesn't have left child, delete node and put his right child in place
                    if (current.left == null) {
                        if (left) p1.left = current.right else p1.right = current.right
                    }
                    //If node doesn't have right child, delete node and put his left child in place
                    else if (current.right == null) {
                        if (left) p1.left = current.left else p1.right = current.left
                    }
                    //If node has both child, swap values with his inorder successor, and delete inorder successor
                    //If inorder successor has right child
                    else {
                        val p2 = findKeyParent(current.right!!)
                        if (p2 == null) {
                            swap(current, current.right!!)
                            current.right = current.right!!.right
                        } else {
                            swap(current, p2.left!!)
                            p2.left = p2.left!!.right
                        }
                    }
                    root = rootParent.right
                    notifyDelete()
                    return
                }
            }
        }
    }

    private fun findKeyParent(node : BSTNode<T>) : BSTNode<T>? {
        var result = node
        var parent : BSTNode<T>? = null
        while (result.left != null) {
            parent = result
            result = result.left!!
        }
        return parent
    }

    private fun swap(v1 : BSTNode<T>, v2 : BSTNode<T>) {
        val temp = v1.key
        v1.key = v2.key
        v2.key = temp
    }

    override fun search(key: T): Boolean {
        var current = root
        while (current != null) {
            if (current.key == key) return true
            val comparision = compare(key, current.key!!)
            when {
                comparision < 0 -> current = current.left
                comparision > 0 -> current = current.right
            }
        }
        return false
    }

    override fun load(f: File) {
        TODO("not implemented")
    }

    override fun inorder() {
        var current = root
        val s = Stack<BSTNode<T>>()
        while (current!=null || s.size>0) {
            while (current != null) {
                s.push(current)
                current = current.left
            }
            current = s.pop()
            println(current.key)
            current = current.right
        }
    }
}