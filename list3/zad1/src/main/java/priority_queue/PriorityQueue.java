package priority_queue;

public class PriorityQueue {

    private Node[] A;
    private int heapSize = 0;

    public PriorityQueue(int size) {
        A = new Node[size];
    }

    public boolean empty() {
        return (heapSize==0);
    }

    public int top() {
        return A[0].getValue();
    }

    public int pop() {
        Node max = A[0];
        A[0] = A[heapSize-1];
        heapSize--;
        heapify(A, 0);
        return max.getValue();

    }

    public void priority(int value, int priority) {
        for (int i = 0; i < heapSize; i++) {
            if (A[i].getValue() == value) {
                increaseKey(i, priority);
            }
        }
    }

    private void increaseKey(int x, int k) {
        if (k < A[x].getPriority()) {
            A[x].setPriority(k);
            while( x > 0 && A[parent(x)].getPriority() > A[x].getPriority()) {
                swap(A, x, parent(x));
                x = parent(x);
            }
        }
    }

    public void insert(int value, int key) {
        heapSize++;
        A[heapSize-1] = new Node(Integer.MAX_VALUE, value);
        increaseKey(heapSize-1, key);
    }

    private void heapify(Node[] arr, int i) {
        int min = i;
        int left = left(i);
        int right= right(i);

        if (left<=heapSize && arr[left].getPriority() < arr[min].getPriority()) {
            min = left;
        }
        if (right<=heapSize && arr[right].getPriority() < arr[min].getPriority()) {
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

    private void swap(Node[] arr, int p1, int p2) {
        Node temp = arr[p1];
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

