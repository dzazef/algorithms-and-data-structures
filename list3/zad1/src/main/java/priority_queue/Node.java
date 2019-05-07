package priority_queue;

public class Node {

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) throws IncorrectPriorityException {
        if (priority < 0) throw new IncorrectPriorityException();
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int priority;
    private int value;

    public Node(int priority, int value) throws IncorrectPriorityException {
        if (priority < 0) throw new IncorrectPriorityException();
        this.priority = priority;
        this.value = value;
    }

    @Override
    public String toString() {
        return "("+getValue()+", "+getPriority()+")";
    }
}
