package structures.rbt

import structures.Tree
import java.util.*

private const val BLACK = true
private const val RED = false
class RBT<T : Comparable<T>> : Tree<T>()  {

    private val nil : RBTNode<T> = RBTNode(color = BLACK, key = null)
    private var root : RBTNode<T>

    init {
        nil.parent = nil
        nil.right = nil
        nil.left = nil
        root = nil
    }

    fun getRoot(): RBTNode<T> = root

    private fun leftRotate(x: RBTNode<T>) {
        val y = x.right
        x.right = y!!.left
        if (y.left != nil) {
            y.left!!.parent = x
        }
        y.parent = x.parent
        when {
            x.parent == nil         -> root = y
            x == x.parent!!.left    -> x.parent!!.left = y
            else                    -> x.parent!!.right = y
        }
        y.left = x
        x.parent = y
    }

    private fun rightRotate(x: RBTNode<T>) {
        val y = x.left
        x.left = y!!.right
        if (y.right != nil) {
            y.right!!.parent = x
        }
        y.parent = x.parent
        when {
            x.parent == nil         -> root = y
            x == x.parent!!.right   -> x.parent!!.right = y
            else                    -> x.parent!!.left = y
        }
        y.right = x
        x.parent = y
    }

    override fun insert(key: T) {
        var y : RBTNode<T> = nil
        var x : RBTNode<T> = root
        val z : RBTNode<T> = RBTNode(key = key, color = RED)
        while (x != nil) {
            y = x
            val comparision = compareStatistic(key, x.key!!)
            x = when {
                comparision < 0     -> x.left!!
                comparision > 0     -> x.right!!
                else                -> return
            }
        }
        z.parent = y
        when {
            y == nil                -> root = z
            key < y.key!!           -> y.left = z
            else                    -> y.right = z
        }
        z.left = nil
        z.right = nil
        insertFixUp(z)
    }

    private fun insertFixUp(z: RBTNode<T>) {
        var node = z
        while (node.parent!!.color == RED) {
            if (node.parent == node.parent!!.parent!!.left) {
                val y = node.parent!!.parent!!.right
                when (RED) {
                    y!!.color -> {
                        node.parent!!.color = BLACK
                        y.color = BLACK
                        node.parent!!.parent!!.color = RED
                        node = node.parent!!.parent!!
                    }
                    else -> {
                        if (node == node.parent!!.right) {
                            node = node.parent!!
                            leftRotate(node)
                        }
                        node.parent!!.color = BLACK
                        node.parent!!.parent!!.color = RED
                        rightRotate(node.parent!!.parent!!)
                    }
                }
            } else {
                val y = node.parent!!.parent!!.left
                when (RED) {
                    y!!.color -> {
                        node.parent!!.color = BLACK
                        y.color = BLACK
                        node.parent!!.parent!!.color = RED
                        node = node.parent!!.parent!!
                    }
                    else -> {
                        if (node == node.parent!!.left) {
                            node = node.parent!!
                            rightRotate(node)
                        }
                        node.parent!!.color = BLACK
                        node.parent!!.parent!!.color = RED
                        leftRotate(node.parent!!.parent!!)
                    }
                }
            }
        }
        root.color = true
    }

    private fun transplant(u : RBTNode<T>, v : RBTNode<T>) {
        when {
            u.parent == nil -> root = v
            u == u.parent?.left -> u.parent?.left = v
            else -> u.parent?.right = v
        }
        v.parent = u.parent
    }

    private fun treeMinimum(x : RBTNode<T>): RBTNode<T> {
        var res = x
        while (res.left != nil) {
            res = res.left!!
        }
        return res
    }

    private fun find(key : T): RBTNode<T>? {
        var current = root
        while (current != nil) {
            if (current.key == key) return current
            val comparision = compareStatistic(key, current.key!!)
            when {
                comparision < 0 -> current = current.left!!
                comparision > 0 -> current = current.right!!
            }
        }
        return null
    }

    override fun delete(key: T) {
        val z = find(key)
        var y = z
        if (y == null)
            return
        else {
            val x : RBTNode<T>
            var yBeginColor = y.color
            when (nil) {
                z!!.left -> {
                    x = z.right!!
                    transplant(z, z.right!!)
                }
                z.right -> {
                    x = z.left!!
                    transplant(z, z.left!!)
                }
                else -> {
                    y = treeMinimum(z.right!!)
                    yBeginColor = y.color
                    x = y.right!!
                    if (y.parent == z) {
                        x.parent = y
                    } else {
                        transplant(y, y.right!!)
                        y.right = z.right
                        y.right!!.parent = y
                    }
                    transplant(z, y)
                    y.left = z.left
                    y.left!!.parent = y
                    y.color = z.color
                }
            }
            if (yBeginColor == BLACK) {
                deleteFixUp(x)
            }
        }
    }

    private fun deleteFixUp(node: RBTNode<T>) {
        var x = node
        while (x != root && x.color == BLACK) {
            if (x == x.parent!!.left) {
                var w = x.parent!!.right!!
                if (w.color == RED) {
                    w.color = BLACK
                    x.parent!!.color = RED
                    leftRotate(x.parent!!)
                    w = x.parent!!.right!!
                }
                if (w.left!!.color == BLACK && w.right!!.color == BLACK) {
                    w.color = RED
                    x = x.parent!!
                } else {
                    if (w.right!!.color == BLACK) {
                        w.left!!.color = BLACK
                        w.color = RED
                        rightRotate(w)
                        w = x.parent!!.right!!
                    }
                    w.color = x.parent!!.color
                    x.parent!!.color = BLACK
                    w.right!!.color = BLACK
                    leftRotate(x.parent!!)
                    x = root
                }
            } else {
                var w = x.parent!!.left!!
                if (w.color == RED) {
                    w.color = BLACK
                    x.parent!!.color = RED
                    rightRotate(x.parent!!)
                    w = x.parent!!.left!!
                }
                if (w.right!!.color == BLACK && w.left!!.color == BLACK) {
                    w.color = RED
                    x = x.parent!!
                } else {
                    if (w.left!!.color == BLACK) {
                        w.right!!.color = BLACK
                        w.color = RED
                        leftRotate(w)
                        w = x.parent!!.left!!
                    }
                    w.color = x.parent!!.color
                    x.parent!!.color = BLACK
                    w.left!!.color = BLACK
                    rightRotate(x.parent!!)
                    x = root
                }
            }
        }
        x.color = BLACK
    }

    override fun search(key: T): Boolean {
        return find(key) != null
    }

    private fun inorder(output : Boolean): Int {
        var counter = 0
        if (output) println("---RBT TREE---")
        var current = root
        val s = Stack<RBTNode<T>>()
        while (current!=nil || s.size>0) {
            while (current != nil) {
                s.push(current)
                current = current.left!!
            }
            current = s.pop()
            if (output) println(current.key)
            counter++
            current = current.right!!
        }
        return counter
    }

    override fun inorder() : Int = inorder(true)

    override fun size(): Int = inorder(false)
}

//        //If root is null insert new value in root
//        val node = RBTNode(key = key, color = RED, left = nil, right = nil, parent = nil)
//        if (root == nil) {
//            node.color = BLACK
//            root = node
//            return
//        }
//        var current = root
//        while (current != nil) {
//            val comparision = compareStatistic(key, current?.key!!)
//            current = when {
//                //If key is lesser then current node key search in left tree
//                comparision < 0 -> current.left
//                //If key is greater then current node key search in left tree
//                comparision > 0 -> current.right
//                //If key is same do nothing
//                else -> return
//            }
//        }
//        node.parent = current.parent
//        val comparision = compareStatistic(key, current.parent!!.key!!)
//        when {
//            comparision < 0 -> current.parent?.left = node
//            comparision > 0 -> current.parent?.right = node
//        }
//        insertFixUp(node)
//        if (root == nil) {
//            root = RBTNode(key = key, color = BLACK, right = nil, left = nil, parent = nil)
//            return
//        }