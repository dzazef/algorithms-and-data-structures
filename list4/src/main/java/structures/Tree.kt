package structures

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception
import java.util.*

abstract class Tree<T : Comparable<T>> : Statistics<T>() {
    abstract fun insert(key : T)
    abstract fun delete(key : T)
    abstract fun search(key : T) : Boolean
    open fun load(f : File, fromString: (String) -> (T)) {
        var counter = 0
        if (f.canRead()) {
            try {
                val br = BufferedReader(FileReader(f))
                var line : String? = br.readLine()
                while (line != null) {
                    val s = Scanner(line)
                    while (s.hasNext()) {
                        insert(fromString(s.next()))
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
        println("Loading finished. Inserted $counter words, including repetitions")
    }
    abstract fun inorder(): Int
    abstract fun size(): Int
}