import structures.Tree
import structures.bst.BST
import structures.rbt.RBT
import structures.splay.Splay
import java.util.*


fun incorrectUsage() {
    System.err.println("Incorrect parameters")
    System.err.println("Usage ./zad1 --type bst/structures.rbt/splay")
    System.exit(1)
}

fun cleanUpKey(key : String): String {
    var res = key
    while (res.isNotEmpty() && !res[0].isLetter()) {
        res = res.substring(1)
    }
    while (res.isNotEmpty() && !res[res.length-1].isLetter()) {
        res = res.substring(0, res.length-1)
    }
    return res
}

fun main(args : Array<String>) {
    if (args.size != 2 || args[0] != "--type") {
        incorrectUsage()
    }
    val tree: Tree<String>? = when (args[1]) {
        "bst" -> BST()
        "rbt" -> RBT()
        "splay" -> Splay()
        else -> {
            incorrectUsage()
            null
        }
    }
    val s = Scanner(System.`in`)
    print("Number of operations: ")
    val n = s.nextInt()
    for (i in 0 until n) {
        when(s.next()) {
            "inorder", "io" -> tree!!.inorder()
            "insert", "i" -> {
                val v = s.next()
                tree!!.insert(cleanUpKey(v))
                println("Inserted: $v")
            }
            "delete", "d" -> {
                val v = s.next()
                tree!!.delete(v)
                println("Deleted: $v")
            }
            "search", "s" -> {
                val v = s.next()
                println(tree!!.search(v))
            }
            "load", "l" -> {
                TODO("implement")
            }
            else -> println("Incorrect option")
        }
    }
}