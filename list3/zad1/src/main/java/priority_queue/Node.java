package priority_queue;

public class Node<T> {

    public T getPriority() {
        return priority;
    }

    public void setPriority(T priority) {
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private T priority;
    private int value;

    public Node(T priority, int value) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public String toString() {
        return "("+getValue()+", "+getPriority()+")";
    }
}
