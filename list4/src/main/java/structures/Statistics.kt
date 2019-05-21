package structures

import ANSI_GREEN
import ANSI_RESET


open class Statistics<T : Comparable<T>> {
    private val beginTime = System.nanoTime()
    private var comparision = 0
    private var modification = 0
    private var insert = 0
    private var insertWithoutRepetition = 0
    private var delete = 0
    private var deleteWithoutRepetition = 0
    private var search = 0
    private var load = 0
    private var inOrder = 0
    private var currentElements = 0
    private var maxElements = 0

    //Only when item was inserted - it wasn't already in the structure
    protected fun notifyElementInserted() {
        insertWithoutRepetition++
        currentElements++
        if (currentElements > maxElements) maxElements = currentElements
    }

    //Only when element was deleted
    protected fun notifyElementDeleted() {
        deleteWithoutRepetition++
        currentElements--
    }

    //Even if element was already in the tree
    protected fun notifyInsert() = ++insert

    //Even if element was not in the tree
    protected fun notifyDelete() = ++delete

    protected fun notifySearch() = ++search

    protected fun notifyLoad() = ++load

    protected fun notifyInOrder() = ++inOrder

    protected fun notifyComparision() = ++comparision

    protected fun notifyComparision(count : Int): Int {
        comparision += count
        return comparision
    }

    protected fun notifyModification() = ++modification

    protected fun notifyModification(count : Int): Int {
        modification += count
        return modification
    }

    protected fun compareStatistic(v1 : T, v2 : T) : Int {
        notifyComparision()
        return v1.compareTo(v2)
    }

    protected fun checkIfNullStatistic(v1 : Any?): Boolean {
        comparision++
        return (v1 == null)
    }

    protected fun checkIfKeyEqualStatistic(v1 : T?, v2: T?): Boolean {
        comparision++
        return (v1 == v2)
    }

    protected fun checkIfNodeEqualStatistic(v1 : Any?, v2: Any?): Boolean {
        comparision++
        return (v1 == v2)
    }

    protected fun checkIfNilStatistic(v1 : Any?, nil: Any?): Boolean {
        comparision++
        return (v1 == nil)
    }

    protected fun checkIfColorEqualStatistic(v1 : Any?, nil: Any?): Boolean {
        comparision++
        return (v1 == nil)
    }

    private fun getCurrentTime(): String {
        var time : Long = (System.nanoTime() - beginTime) / 1000000
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

    fun printStatistics() {
        System.out.print(ANSI_GREEN)
        System.out.println("---STATISTICS---")
        System.out.println("Insert (with repetition): $insert")
        System.out.println("Insert (without repetition): $insertWithoutRepetition")
        System.out.println("Delete (with repetition): $delete")
        System.out.println("Insert (without repetition): $deleteWithoutRepetition")
        System.out.println("Search: $search")
        System.out.println("Load: $load")
        System.out.println("Inorder: $inOrder")
        System.out.println("----------------------------")
        System.out.println("Comparision: $comparision")
        System.out.println("Modification: $modification")
        System.out.println("----------------------------")
        System.out.println("Current elements: $currentElements")
        System.out.println("Max elements: $maxElements")
        System.out.println("Working time: ${getCurrentTime()}")
        System.out.print(ANSI_RESET)
    }
}