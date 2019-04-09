import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Incorrect parameters. Usage: --type select|insert|heap|quick --asc|--desc");
        }
        Random r = new Random();
        Integer[] arr = new Integer[10000];
        for (int i = 0; i<10000; i++) {
            arr[i] = r.nextInt(10000);
        }
        Sort.modifiedQuickSort(arr, true);

    }
}