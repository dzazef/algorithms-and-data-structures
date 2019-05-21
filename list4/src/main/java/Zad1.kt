import structures.Tree
import java.io.File
import java.io.IOException
import java.util.*


fun incorrectUsage() {
    System.err.println("Incorrect parameters")
    System.err.println("Usage ./zad1 --type bst/structures.rbt/splay")
    System.exit(1)
}

fun main(args : Array<String>) {
    if (args.size != 2 || args[0] != "--type") {
        incorrectUsage()
    }
    val tree: Tree<String> = getTree(args[1])
    val s = Scanner(System.`in`)
    print("Number of operations: ")
    val n = s.nextInt()
    for (i in 0 until n) {
        when(s.next()) {
            "inorder", "io" -> {
                val number = tree.inorder()
                println("$number elements in tree")
                tree.printStatistics()
            }
            "insert", "i" -> {
                val v = s.next()
                tree.insert(cleanUpKey(v))
                println("Inserted: $v")
                tree.printStatistics()
            }
            "delete", "d" -> {
                val v = s.next()
                tree.delete(v)
                println("Deleted: $v")
                tree.printStatistics()
            }
            "search", "s" -> {
                val v = s.next()
                println(tree.search(v))
                tree.printStatistics()
            }
            "load", "l" -> {
                val path = s.next()
                try {
                    tree.load(File(path)) {cleanUpKey(it)}
                    tree.printStatistics()
                } catch (e : IOException) {
                    println("Incorrect file")
                }
            }
            else -> println("Incorrect option")
        }
    }
}