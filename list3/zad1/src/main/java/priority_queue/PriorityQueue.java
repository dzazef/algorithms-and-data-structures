package priority_queue;

public class PriorityQueue<T extends Comparable<T>> {

    private final T MAX_VALUE;
    private Node<T>[] A;
    private int heapSize = 0;

    public PriorityQueue(int size, T MAX_VALUE) {
        //noinspection unchecked
        A = new Node[size];
        this.MAX_VALUE = MAX_VALUE;
    }

    public boolean empty() {
        return (heapSize==0);
    }

    public int top() {
        return A[0].getValue();
    }

    public int pop() {
        Node<T> max = A[0];
        A[0] = A[heapSize-1];
        heapSize--;
        heapify(A, 0);
        return max.getValue();

    }

    public void priority(int value, T priority) {
        for (int i = 0; i < heapSize; i++) {
            if (A[i].getValue() == value) {
                increaseKey(i, priority);
            }
        }
    }

    private void increaseKey(int x, T k) {
        if (k.compareTo(A[x].getPriority()) < 0) {
            A[x].setPriority(k);
            while( x > 0 && (A[parent(x)].getPriority()).compareTo(A[x].getPriority()) > 0)  {
                swap(A, x, parent(x));
                x = parent(x);
            }
        }
    }

    public void insert(int value, T key) {
        heapSize++;
        A[heapSize-1] = new Node<>(MAX_VALUE, value);
        increaseKey(heapSize-1, key);
    }

    private void heapify(Node<T>[] arr, int i) {
        int min = i;
        int left = left(i);
        int right= right(i);

        if (left<=heapSize && (arr[left].getPriority()).compareTo((arr[min].getPriority())) < 0) {
            min = left;
        }
        if (right<=heapSize && (arr[right].getPriority()).compareTo(arr[min].getPriority()) < 0) {
            min = right;
        }
        if (min != i) {
            swap(arr, i, min);
            heapify(arr, min);
        }
    }

    public void print() {
        for (int i=0; i<heapSize; i++) {
            System.out.println(A[i].toString());
        }
    }

    private void swap(Node<T>[] arr, int p1, int p2) {
        Node<T> temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    private int parent(int i) {
        return i/2;
    }

    private int left(int i) {
        return 2*i + 1;
    }

    private int right(int i) {
        return 2*i + 2;
    }
}

