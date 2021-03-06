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
        x.right = y!!.left; notifyModification()
        if (!checkIfNilStatistic(y.left, nil)) {
            y.left!!.parent = x; notifyModification()
        }
        y.parent = x.parent; notifyModification()
        when {
            checkIfNilStatistic(x.parent, nil) -> {
                root = y; notifyModification()
            }
            checkIfNodeEqualStatistic(x, x.parent!!.left) -> {
                x.parent!!.left = y; notifyModification()
            }
            else -> {
                x.parent!!.right = y; notifyModification()
            }
        }
        y.left = x; notifyModification()
        x.parent = y; notifyModification()
    }

    private fun rightRotate(x: RBTNode<T>) {
        val y = x.left
        x.left = y!!.right; notifyModification()
        if (!checkIfNilStatistic(y.right, nil)) {
            y.right!!.parent = x; notifyModification()
        }
        y.parent = x.parent; notifyModification()
        when {
            checkIfNilStatistic(x.parent, nil) -> {
                root = y; notifyModification()
            }
            checkIfNodeEqualStatistic(x, x.parent!!.right) -> {
                x.parent!!.right = y; notifyModification()
            }
            else                    -> {
                x.parent!!.left = y; notifyModification()
            }
        }
        y.right = x; notifyModification()
        x.parent = y; notifyModification()
    }

    override fun insert(key: T) {
        notifyInsert()
        var y : RBTNode<T> = nil
        var x : RBTNode<T> = root
        val z : RBTNode<T> = RBTNode(key = key, color = RED)
        while (!checkIfNilStatistic(x, nil)) {
            y = x
            val comparision = compareStatistic(key, x.key!!)
            x = when {
                comparision < 0     -> x.left!!
                comparision > 0     -> x.right!!
                else                -> return
            }
        }
        z.parent = y; notifyModification()
        when {
            checkIfNilStatistic(y, nil)         -> root = z
            compareStatistic(key, y.key!!) < 0  -> y.left = z
            else                                -> y.right = z
        }; notifyModification()
        z.left = nil; notifyModification()
        z.right = nil; notifyModification()
        insertFixUp(z)
        notifyElementInserted()
    }

    /**
     * Case 1: uncle is red
     * Case 2: uncle is black and z is right son
     * Case 3: uncle is black and z is left son
     */
    private fun insertFixUp(z: RBTNode<T>) {
        var node = z
        while (checkIfColorEqualStatistic(node.parent!!.color, RED)) {
            if (checkIfNodeEqualStatistic(node.parent, node.parent!!.parent!!.left)) {
                val y = node.parent!!.parent!!.right
                when {
                    checkIfColorEqualStatistic(y!!.color, RED) -> {
                        notifyModification(3)                                              //CASE 1
                        node.parent!!.color = BLACK                                              //CASE 1
                        y.color = BLACK                                                          //CASE 1
                        node.parent!!.parent!!.color = RED                                       //CASE 1
                        node = node.parent!!.parent!!                                            //CASE 1
                    }
                    else -> {
                        if (checkIfNodeEqualStatistic(node, node.parent!!.right)) {              //CASE2
                            node = node.parent!!                                                 //CASE2
                            leftRotate(node)                                                     //CASE2
                        }
                        notifyModification(2)                                              //CASE3
                        node.parent!!.color = BLACK                                              //CASE3
                        node.parent!!.parent!!.color = RED                                       //CASE3
                        rightRotate(node.parent!!.parent!!)                                      //CASE3
                    }
                }
            } else {                                                                             //ANALOGICAL
                val y = node.parent!!.parent!!.left
                when {
                    checkIfColorEqualStatistic(y!!.color, RED) -> {
                        notifyModification(3)
                        node.parent!!.color = BLACK
                        y.color = BLACK
                        node.parent!!.parent!!.color = RED
                        node = node.parent!!.parent!!
                    }
                    else -> {
                        if (checkIfNodeEqualStatistic(node, node.parent!!.left)) {
                            node = node.parent!!
                            rightRotate(node)
                        }
                        notifyModification(2)
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
            checkIfNilStatistic(u.parent, nil)           -> {
                root = v; notifyModification()
            }
            checkIfNodeEqualStatistic(u, u.parent?.left) -> {
                u.parent?.left = v; notifyModification()
            }
            else -> {
                u.parent?.right = v; notifyModification()
            }
        }
        v.parent = u.parent; notifyModification()
    }

    private fun treeMinimum(x : RBTNode<T>): RBTNode<T> {
        var res = x
        while (!checkIfNilStatistic(res.left, nil)) {
            res = res.left!!
        }
        return res
    }

    private fun find(key : T): RBTNode<T>? {
        var current = root
        while (!checkIfNilStatistic(current, nil)) {
            if (checkIfKeyEqualStatistic(current.key, key)) return current
            val comparision = compareStatistic(key, current.key!!)
            when {
                comparision < 0 -> current = current.left!!
                comparision > 0 -> current = current.right!!
            }
        }
        return null
    }

    override fun delete(key: T) {
        notifyDelete()
        val z = find(key)
        var y = z
        if (checkIfNullStatistic(y))
            return
        else {
            val x : RBTNode<T>
            var yBeginColor = y!!.color
            when {
                checkIfNilStatistic(z!!.left, nil) -> {
                    x = z.right!!
                    transplant(z, z.right!!)
                }
                checkIfNilStatistic(z.right, nil) -> {
                    x = z.left!!
                    transplant(z, z.left!!)
                }
                else -> {
                    y = treeMinimum(z.right!!)
                    yBeginColor = y.color
                    x = y.right!!
                    if (checkIfNodeEqualStatistic(y.parent, z)) {
                        x.parent = y; notifyModification()
                    } else {
                        transplant(y, y.right!!)
                        y.right = z.right; notifyModification()
                        y.right!!.parent = y; notifyModification()
                    }
                    transplant(z, y)
                    notifyModification(3)
                    y.left = z.left
                    y.left!!.parent = y
                    y.color = z.color
                }
            }
            if (checkIfColorEqualStatistic(yBeginColor, BLACK)) {
                deleteFixUp(x)
            }
        }
        notifyElementDeleted()
    }

    /**
     * CASE 1: brother w of x is red
     * CASE 2: brother w of x is black and both w's son are black
     * CASE 3: brother w of x is black, left w's son is red, right w's son is black
     * CASE 4: brother w of x is black, left w's son is black, right w's son is red
     */
    private fun deleteFixUp(node: RBTNode<T>) {
        var x = node
        while (!checkIfNodeEqualStatistic(x, root) && checkIfColorEqualStatistic(x.color, BLACK)) {
            if (checkIfNodeEqualStatistic(x, x.parent!!.left)) {
                var w = x.parent!!.right!!
                if (checkIfColorEqualStatistic(w.color, RED)) {
                    notifyModification(2)                                                                                     //CASE1
                    w.color = BLACK                                                                                                 //CASE1
                    x.parent!!.color = RED                                                                                          //CASE1
                    leftRotate(x.parent!!)                                                                                          //CASE1
                    w = x.parent!!.right!!                                                                                          //CASE1
                }
                if (checkIfColorEqualStatistic(w.left!!.color, BLACK) && checkIfColorEqualStatistic(w.right!!.color, BLACK)) {
                    w.color = RED; notifyModification()                                                                             //CASE2
                    x = x.parent!!                                                                                                  //CASE2
                } else {
                    if (checkIfColorEqualStatistic(w.right!!.color, BLACK)) {
                        notifyModification(2)                                                                                //CASE3
                        w.left!!.color = BLACK                                                                                      //CASE3
                        w.color = RED                                                                                               //CASE3
                        rightRotate(w)                                                                                              //CASE3
                        w = x.parent!!.right!!                                                                                      //CASE3
                    }
                    notifyModification(3)                                                                                     //CASE4
                    w.color = x.parent!!.color                                                                                      //CASE4
                    x.parent!!.color = BLACK                                                                                        //CASE4
                    w.right!!.color = BLACK                                                                                         //CASE4
                    leftRotate(x.parent!!)                                                                                          //CASE4
                    x = root
                }
            } else {                                                                                                                //ANALOGICAL
                var w = x.parent!!.left!!
                if (checkIfColorEqualStatistic(w.color, RED)) {
                    notifyModification(2)
                    w.color = BLACK
                    x.parent!!.color = RED
                    rightRotate(x.parent!!)
                    w = x.parent!!.left!!
                }
                if (checkIfColorEqualStatistic(w.right!!.color,  BLACK) && checkIfColorEqualStatistic(w.left!!.color, BLACK)) {
                    w.color = RED; notifyModification()
                    x = x.parent!!
                } else {
                    if (checkIfColorEqualStatistic(w.left!!.color, BLACK)) {
                        notifyModification(2)
                        w.right!!.color = BLACK
                        w.color = RED
                        leftRotate(w)
                        w = x.parent!!.left!!
                    }
                    notifyModification(3)
                    w.color = x.parent!!.color
                    x.parent!!.color = BLACK
                    w.left!!.color = BLACK
                    rightRotate(x.parent!!)
                    x = root
                }
            }
        }
        x.color = BLACK; notifyModification()
    }

    override fun search(key: T): Boolean {
        notifySearch()
        return !checkIfNullStatistic(find(key))
    }

    private fun inorder(output : Boolean): Int {
        var counter = 0
        if (output) println("---RBT TREE---")
        var current = root
        val s = Stack<RBTNode<T>>()
        while (!checkIfNilStatistic(current, nil) || s.size>0) {
            while (!checkIfNilStatistic(current, nil)) {
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

    override fun inorder() : Int {
        notifyInOrder()
        return inorder(true)
    }

    override fun size(): Int = inorder(false)
}
