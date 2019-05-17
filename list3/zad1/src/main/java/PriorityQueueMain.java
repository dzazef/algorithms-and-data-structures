import priority_queue.PriorityQueue;

import java.util.Scanner;

public class PriorityQueueMain {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Number of operations: ");
        int m = s.nextInt();
        PriorityQueue<Integer> Q = new PriorityQueue<>(m, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            String cmd = s.next();
            switch (cmd) {
                case "insert":
                case "i":
                    int v = Integer.parseInt(s.next());
                    int k = Integer.parseInt(s.next());
                    Q.insert(v, k);
                    System.out.println("Inserted " + v + " " + k);
                    break;
                case "empty":
                case "e":
                    System.out.println("Empty: " + Q.empty());
                    break;
                case "top":
                case "t":
                    if (Q.empty()) {
                        System.out.println("Top: null");
                    } else {
                        System.out.println("Top: " + Q.top());
                    }
                    break;
                case "pop":
                    if (Q.empty()) {
                        System.out.println("Pop: null");
                    } else {
                        System.out.println("Pop: " + Q.pop());
                    }
                    break;
                case "priority":
                case "prr":
                    int x = s.nextInt();
                    int p = s.nextInt();
                    Q.priority(x, p);
                    System.out.println("Priority " + x + " " + p);
                    break;
                case "print":
                case "prt":
                    Q.print();
                    break;
            }
        }
    }
}
