import structures.Tree
import java.io.File

const val SHUFFLE = false
const val PATH = "/home/dzazef/algorithms-and-data-structures/list4/src/main/resources/aspell_wordlist.txt"
const val NUM_OF_TESTS = 100
private var insertTimeMillis : Long = 0
private var deleteTimeMillis : Long = 0
private var searchTimeMillis : Long = 0

fun main(args : Array<String>) {
    if (args.size != 2 || args[0] != "--type") {
        incorrectUsage()
    }
    var tree: Tree<String> = getTree(args[1])
    val wordList = load(File(PATH))
    if (SHUFFLE) {
        wordList.shuffle()
        println("Shuffled...")
    }
    var beginTime : Long
    for (i in 0 until NUM_OF_TESTS) {
        println("Test: $i/$NUM_OF_TESTS")
        tree = getTree(args[1])
        //INSERT
        beginTime = System.nanoTime()
        for (word in wordList) {
            tree.insert(word)
        }
        insertTimeMillis += (System.nanoTime() - beginTime) / 1000000
        println("Inserted...")
        //SEARCH
        beginTime = System.nanoTime()
        for (word in wordList) {
            tree.search(word)
        }
        searchTimeMillis += (System.nanoTime() - beginTime) / 1000000
        println("Searched...")
        //DELETE
        beginTime = System.nanoTime()
        for (word in wordList) {
            tree.delete(word)
        }
        deleteTimeMillis += (System.nanoTime() - beginTime) / 1000000
        println("Deleted...")
    }
    println()
    println("Tree: " + tree::class.java.toString())
    println("Insert avg time: ${millisToTime(insertTimeMillis/NUM_OF_TESTS)}")
    println("Search avg time: ${millisToTime(searchTimeMillis/NUM_OF_TESTS)}")
    println("Delete avg time: ${millisToTime(deleteTimeMillis/NUM_OF_TESTS)}")
    println()
    println("File: $PATH")
    println("Shuffle: $SHUFFLE")
    tree.printStatistics()
}