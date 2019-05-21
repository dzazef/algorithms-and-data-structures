package structures.bst

import structures.Tree
import java.util.*

class BST<T : Comparable<T>>(private var root : BSTNode<T>? = null) : Tree<T>()  {

    fun getRoot(): BSTNode<T>? = root

    override fun insert(key: T) {
        notifyInsert()
        //If root is null insert new value in root
        val node = BSTNode(key)
        if (checkIfNullStatistic(root)) {
            notifyElementInserted()
            root = node; notifyModification()
            return
        }
        var parent : BSTNode<T>? = null
        var current = root
        while (!checkIfNullStatistic(current)) {
            parent = current
            val comparision = compareStatistic(key, current!!.key!!)
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
        val comparision = compareStatistic(key, parent!!.key!!)
        when {
            comparision < 0 -> parent.left = node
            else -> parent.right = node
        }
        notifyModification()
        notifyElementInserted()
    }

    override fun delete(key: T) {
        notifyDelete()
        if (checkIfNullStatistic(root)) return
        val rootParent = BSTNode(null, right = root)
        var left = false
        var p1: BSTNode<T> = rootParent
        var current : BSTNode<T>? = rootParent.right

        while (!checkIfNullStatistic(current)) {
            //Find node to delete
            val comparision = compareStatistic(key, current!!.key!!)
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
                    if (checkIfNullStatistic(current.left)) {
                        if (left) p1.left = current.right else p1.right = current.right
                        notifyComparision()
                        notifyModification()
                    }
                    //If node doesn't have right child, delete node and put his left child in place
                    else if (checkIfNullStatistic(current.right)) {
                        if (left) p1.left = current.left else p1.right = current.left
                        notifyComparision()
                        notifyModification()
                    }
                    //If node has both child, swap values with his inorder successor, and delete inorder successor
                    //If inorder successor has right child
                    else {
                        val p2 = findKeyParent(current.right!!)
                        if (checkIfNullStatistic(p2)) {
                            swap(current, current.right!!)
                            current.right = current.right!!.right
                            notifyModification()
                        } else {
                            swap(current, p2!!.left!!)
                            p2.left = p2.left!!.right
                            notifyModification()
                        }
                    }
                    root = rootParent.right
                    notifyModification()
                    notifyElementDeleted()
                    return
                }
            }
        }
    }

    private fun findKeyParent(node : BSTNode<T>) : BSTNode<T>? {
        var result = node
        var parent : BSTNode<T>? = null
        while (!checkIfNullStatistic(result.left)) {
            parent = result
            result = result.left!!
        }
        return parent
    }

    private fun swap(v1 : BSTNode<T>, v2 : BSTNode<T>) {
        val temp = v1.key
        v1.key = v2.key
        v2.key = temp
        notifyModification(2)
    }

    override fun search(key: T): Boolean {
        notifySearch()
        var current = root
        while (!checkIfNullStatistic(current)) {
            if (checkIfKeyEqualStatistic(current!!.key, key)) return true
            val comparision = compareStatistic(key, current.key!!)
            when {
                comparision < 0 -> current = current.left
                comparision > 0 -> current = current.right
            }
        }
        return false
    }

    override fun inorder(): Int {
        notifyInOrder()
        return inorder(true)
    }

    private fun inorder(output : Boolean): Int {
        var counter = 0
        if (output) println("---BST TREE---")
        var current = root
        val s = Stack<BSTNode<T>>()
        while (!checkIfNullStatistic(current) || s.size > 0) {
            while (!checkIfNullStatistic(current)) {
                s.push(current)
                current = current!!.left
            }
            current = s.pop()
            if (output) println(current.key)
            counter++
            current = current.right
        }
        return counter
    }

    override fun size(): Int = inorder(false)
}