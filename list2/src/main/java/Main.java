import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

@SuppressWarnings("Duplicates")
public class Main {
    static void test(Calculate alg, String fileName, int finalK) {
        try {
            PrintWriter pw = new PrintWriter(fileName);
            for (int n=100; n<=10000; n+=100) {
                System.err.println("("+fileName+")"+"Current n: "+n);
                double s = 0;
                double c = 0;
                long time = 0;
                Random r = new Random();
                for (int j = 0; j< finalK; j++) {
                    Integer[] arr = new Integer[n];
                    for (int i=0; i<n; i++) { arr[i] = r.nextInt(); }
                    alg.reset();
                    alg.sort(arr, true);
                    s += (double)alg.getS()/(double)finalK;
                    c += (double)alg.getC()/(double)finalK;
                    time += alg.getTime()/(long)finalK;
                }
                pw.println(n+";"+(int)s+";"+(int)c+";"+time);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("IO error");
            System.exit(2);
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Incorrect parameters. Usage: \"--type select|insert|heap|quick|mquick --asc|--desc\" or \"--stat file_name k\"");
        }
        if (args[0].equals("--stat")) {
            String fileName = args[1];
            int k = 0;
            try {
                k = Integer.parseInt(args[2]);
            } catch (Exception e) {
                System.err.println("Syntax error");
                System.exit(1);
            }

            final int finalK = k;
//            new Thread(() ->
                    test(new CalculateSelectionSort(), fileName+finalK+"s", finalK);
//            ).start();
//            new Thread(() ->
                    test(new CalculateInsertionSort(), fileName+finalK+"i", finalK);
//            ).start();
//            new Thread(() ->
                    test(new CalculateHeapSort(), fileName+finalK+"h", finalK);
//            ).start();
//            new Thread(() ->
                    test(new CalculateQuickSort(), fileName+finalK+"q", finalK);
//            ).start();
//            new Thread(() ->
                    test(new CalculateModifiedQuickSort(), fileName+finalK+"mq", finalK);
//            ).start();
        } else if (args[0].equals("--type")) {

        } else if (args[0].equals("--asc")) {

        } else if (args[0].equals("--desc")) {

        } else {
            System.err.println("Syntax error");
            System.exit(1);
        }
    }
}