@file:Suppress("unused")

import structures.Tree
import structures.bst.BST
import structures.rbt.RBT
import structures.splay.Splay
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception
import java.util.*

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

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

fun load(f : File): ArrayList<String> {
    var counter = 0
    val list = ArrayList<String>()
    if (f.canRead()) {
        try {
            val br = BufferedReader(FileReader(f))
            var line : String? = br.readLine()
            while (line != null) {
                val s = Scanner(line)
                while (s.hasNext()) {
                    list.add(cleanUpKey(s.next()))
                    counter++
                }
                line = br.readLine()
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    } else {
        println("ERROR: Can't read from file")
    }
    println("Loading finished. Read $counter words, including repetitions")
    return list
}

fun millisToTime(millis : Long): String {
    var time = millis
    var res = ""
    res += (time / 3600000).toString() + "h "
    time %= 3600000
    res += (time / 60000).toString() + "m "
    time %= 60000
    res += (time / 1000).toString() + "s "
    time %= 1000
    res += time.toString() + "ms "
    return res
}

fun getTree(type : String): Tree<String> {
    return when (type) {
        "bst" -> BST()
        "rbt" -> RBT()
        "splay" -> Splay()
        else -> {
            incorrectUsage()
            BST()
        }
    }
}

