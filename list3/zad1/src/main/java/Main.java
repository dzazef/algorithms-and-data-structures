import priority_queue.PriorityQueue;
import priority_queue.QueueEmptyException;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(100);
        Random random = new Random();
        for (int i=0; i<100; i++) {
            int v = random.nextInt(2000);
            priorityQueue.insert(v, v);
        }
        try {
            while(!priorityQueue.empty()) {
                System.out.println(priorityQueue.pop());
                System.out.println("-----------------------------");
            }
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
    }
}
