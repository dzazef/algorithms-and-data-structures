package structures

open class Statistics<T : Comparable<T>> {
    private val beginTime = System.nanoTime()
    private var maxElements = 0
    private var currentElements = 0

    protected fun notifyInsert() {
        currentElements++
        if (maxElements < currentElements) maxElements = currentElements
    }

    protected fun notifyDelete() = currentElements--

    protected fun lesser(v1 : T, v2 : T) : Boolean {
        return v1 < v2
    }

    protected fun greater(v1 : T, v2 : T) : Boolean {
        return v1 > v2
    }

    protected fun compare(v1 : T, v2 : T) : Int {
        return v1.compareTo(v2)
    }
}