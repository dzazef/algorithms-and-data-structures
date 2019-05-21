package structures.splay

import structures.Tree
import java.util.*

class Splay<T : Comparable<T>>(private var root : SplayNode<T>? = null) : Tree<T>() {

    private fun splay(node : SplayNode<T>?) {
        while (!checkIfEqualStatistic(node, root)) {
            if(checkIfEqualStatistic(node?.parent, root)){
                zig(node)
            }else if (checkIfEqualStatistic(node?.parent?.parent?.left, node?.parent)){
                if(checkIfEqualStatistic(node?.parent?.left, node)){
                    zigZig(node)
                }else{
                    zigZag(node)
                }
            }else{
                if(checkIfEqualStatistic(node?.parent?.right, node)){
                    zigZig(node)
                }else{
                    zigZag(node)
                }
            }
        }
    }

    private fun zig(node : SplayNode<T>?) {
        if(checkIfEqualStatistic(node?.parent?.left, node)){
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
        if (!checkIfNullStatistic(gp)) {
            if(checkIfEqualStatistic(gp!!.left, p)){
                notifyModification(2)
                gp.left = node
                node.parent = gp
            }else{
                notifyModification(2)
                gp.right = node
                node.parent = gp
            }
        } else {
            notifyModification(2)
            root = node
            node?.parent = null
        }
        notifyModification(2)
        node?.right = p
        p?.parent = node
        if (!checkIfNullStatistic(temp)) {
            notifyModification(2)
            temp!!.parent = p
            p?.left = temp
        } else {
            p?.left = null; notifyModification()
        }
    }

    private fun rotateLeft(node: SplayNode<T>?) {
        val gp = node?.parent?.parent
        val temp = node?.left
        val p = node?.parent
        if (!checkIfNullStatistic(gp)) {
            if (checkIfEqualStatistic(gp!!.left, p)) {
                notifyModification(2)
                gp.left = node
                node.parent = gp
            } else {
                notifyModification(2)
                gp.right = node
                node.parent = gp
            }
        } else {
            notifyModification(2)
            root = node
            node?.parent = null
        }
        notifyModification(2)
        node?.left = p
        p?.parent = node
        if (!checkIfNullStatistic(temp)) {
            notifyModification(2)
            p?.right = temp!!
            temp.parent = p
        } else {
            p?.right = null; notifyModification()
        }
    }

    override fun insert(key: T) {
        notifyInsert()
        if (checkIfNullStatistic(root)) {
            root = SplayNode(key = key)
            notifyElementInserted()
            return
        }
        search(key)
        val comparision = compareStatistic(key, root!!.key!!)
        when {
            comparision == 0 -> return
            comparision > 0 -> {
                val temp = SplayNode(key = key)
                notifyModification(6)
                temp.left = root
                root?.parent = temp
                temp.right = root?.right
                root?.right?.parent = temp
                root?.right = null
                root = temp
                notifyElementInserted()
                return
            }
            comparision < 0 -> {
                val temp = SplayNode(key = key)
                notifyModification(6)
                temp.right = root
                root?.parent = temp
                temp.left = root?.left
                root?.left?.parent = temp
                root?.left = null
                root = temp
                notifyElementInserted()
                return
            }
        }
    }

    override fun delete(key: T) {
        notifyDelete()
        if (checkIfNullStatistic(root)) return
        if (checkIfEqualStatistic(search(key), false)) return
        val leftTree = root?.left
        leftTree?.parent = null; notifyModification()
        val rightTree = root?.right
        rightTree?.parent = null; notifyModification()
        if (checkIfNullStatistic(leftTree)) {
            root = rightTree; notifyModification()
        } else {
            var parent : SplayNode<T>? = null
            var current = leftTree
            while(!checkIfNullStatistic(current)) {
                parent = current!!
                current = current.right
            }
            root = leftTree; notifyModification()
            if (!checkIfNullStatistic(parent)) splay(parent!!)
            root?.right = rightTree; notifyModification()
            rightTree?.parent = root; notifyModification()
        }
        notifyElementDeleted()
    }

    private fun search(key : T, node : SplayNode<T>?): Boolean {
        var current = node
        var parent : SplayNode<T>? = null
        while (!checkIfNullStatistic(current)) {
            val comparision = compareStatistic(key, current!!.key!!)
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
        if (!checkIfNullStatistic(parent)) splay(parent)
        return false
    }

    override fun search(key: T): Boolean = search(key, root)

    private fun inorder(output : Boolean): Int {
        var counter = 0
        if (output) println("---SPLAY TREE---")
        var current = root
        val s = Stack<SplayNode<T>>()
        while (!checkIfNullStatistic(current) || s.size>0) {
            while (!checkIfNullStatistic(current)) {
                s.push(current!!)
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
