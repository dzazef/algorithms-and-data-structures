package structures.splay

import structures.Statistics
import structures.Tree
import java.io.File
import java.util.*

class Splay<T : Comparable<T>>(private var root : SplayNode<T>? = null) : Tree<T>() {

    private fun splay(node : SplayNode<T>?) {
        while (node != root) {
            if(node?.parent == root){
                zig(node)
            }else if (node?.parent?.parent?.left == node?.parent){
                if(node?.parent?.left == node){
                    zigZig(node)
                }else{
                    zigZag(node)
                }
            }else{
                if(node?.parent?.right == node){
                    zigZig(node)
                }else{
                    zigZag(node)
                }
            }
        }
    }

    private fun zig(node : SplayNode<T>?) {
        if(node?.parent?.left == node){
            rotateRight(node)
        }else{
            rotateLeft(node)
        }
    }

    private fun zigZig(node : SplayNode<T>?) {
        zig(node?.parent)
        zig(node)
    }

    private fun zigZag(node : SplayNode<T>?) {
        zig(node)
        zig(node)
    }

    private fun rotateRight(node: SplayNode<T>?) {
        val temp = node?.right
        val gp = node?.parent?.parent
        val p = node?.parent
        if (gp != null) {
            if(gp.left == p){
                gp.left = node
                node.parent = gp
            }else{
                gp.right = node
                node.parent = gp
            }
        } else {
            root = node
            node?.parent = null
        }
        node?.right = p
        p?.parent = node
        if (temp != null) {
            temp.parent = p
            p?.left = temp
        } else {
            p?.left = null
        }
    }

    private fun rotateLeft(node: SplayNode<T>?) {
        val gp = node?.parent?.parent
        val temp = node?.left
        val p = node?.parent
        if (gp != null) {
            if (gp.left == p) {
                gp.left = node
                node.parent = gp
            } else {
                gp.right = node
                node.parent = gp
            }
        } else {
            root = node
            node?.parent = null
        }
        node?.left = p
        p?.parent = node
        if (temp != null) {
            p?.right = temp
            temp.parent = p
        } else {
            p?.right = null
        }
    }

    override fun insert(key: T) {
        if (root == null) {
            root = SplayNode(key = key)
            return
        }
        search(key)
        val comparision = compare(key, root!!.key!!)
        when {
            comparision == 0 -> return
            comparision > 0 -> {
                val temp = SplayNode(key = key)
                temp.left = root
                root?.parent = temp
                temp.right = root?.right
                root?.right?.parent = temp
                root?.right = null
                root = temp
                return
            }
            comparision < 0 -> {
                val temp = SplayNode(key = key)
                temp.right = root
                root?.parent = temp
                temp.left = root?.left
                root?.left?.parent = temp
                root?.left = null
                root = temp
                return
            }
        }
    }

    override fun delete(key: T) {
        if (root == null) return
        if (!search(key)) return
        getRoot()
        val leftTree = root?.left
        leftTree?.parent = null
        val rightTree = root?.right
        rightTree?.parent = null
        if (leftTree == null) {
            root = rightTree
        } else {
            var parent : SplayNode<T>? = null
            var current = leftTree
            while(current != null) {
                parent = current
                current = current.right
            }
            root = leftTree
            if (parent != null) splay(parent)
            root?.right = rightTree
            rightTree?.parent = root
        }
    }

    private fun search(key : T, node : SplayNode<T>?): Boolean {
        var current = node
        var parent : SplayNode<T>? = null
        while (current != null) {
            val comparision = compare(key, current.key!!)
            when {
                comparision < 0 -> {
                    parent = current
                    current = current.left
                }
                comparision > 0 -> {
                    parent = current
                    current = current.right
                }
                comparision == 0 -> {
                    splay(current)
                    return true
                }
            }
        }
        if (parent != null) splay(parent)
        return false
    }

    override fun search(key: T): Boolean = search(key, root)

    private fun inorder(output : Boolean): Int {
        var counter = 0
        println("---SPLAY TREE---")
        var current = root
        val s = Stack<SplayNode<T>>()
        while (current!=null || s.size>0) {
            while (current != null) {
                s.push(current)
                current = current.left
            }
            current = s.pop()
            counter++
            if (output) println(current.key)
            current = current.right
        }
        return counter
    }

    override fun inorder(): Int = inorder(true)

    override fun size(): Int = inorder(false)

    fun getRoot(): SplayNode<T>? = root
}
