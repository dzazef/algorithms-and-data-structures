import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

class SortTest {
    private final int ARRAY_SIZE = 300000;
    private final int NUM_OF_TESTS = 10;

    private Integer[] randomArray(@SuppressWarnings("SameParameterValue") int n) {
        Integer[] arr = new Integer[n];
        Random r = new Random();
        for (int i=0; i<arr.length; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }


    private static <T extends Comparable<T>> boolean validateSort(T[]arr, boolean asc) {
        int order = (asc?1:-1);
        for (int i = 0; i<arr.length-1; i++) {
            if (arr[i].compareTo(arr[i+1])==order) {
                return false;
            }
        }
        return true;
    }

    @Test
    void selectionSort() {
        for (int i=0; i<NUM_OF_TESTS; i++) {
            Integer[] arr = randomArray(ARRAY_SIZE);
            Sort.selectionSort(arr, true);
            Assert.assertTrue(validateSort(arr, true));

            arr = randomArray(ARRAY_SIZE);
            Sort.selectionSort(arr, false);
            Assert.assertTrue(validateSort(arr, false));
        }
    }

    @Test
    void insertSort() {
        for (int i=0; i<NUM_OF_TESTS; i++) {
            Integer[] arr = randomArray(ARRAY_SIZE);
            Sort.insertSort(arr, true);
            Assert.assertTrue(validateSort(arr, true));

            arr = randomArray(ARRAY_SIZE);
            Sort.insertSort(arr, false);
            Assert.assertTrue(validateSort(arr, false));
        }
    }

    @Test
    void quickSort() {
        for (int i=0; i<NUM_OF_TESTS; i++) {
            Integer[] arr = randomArray(ARRAY_SIZE);
            Sort.quickSort(arr, true);
            Assert.assertTrue(validateSort(arr, true));

            arr = randomArray(ARRAY_SIZE);
            Sort.quickSort(arr, false);
            Assert.assertTrue(validateSort(arr, false));
        }
    }

    @Test
    void heapSort() {
        for (int i=0; i<NUM_OF_TESTS; i++) {
            Integer[] arr = randomArray(ARRAY_SIZE);
            Sort.heapSort(arr, true);
            Assert.assertTrue(validateSort(arr, true));

            arr = randomArray(ARRAY_SIZE);
            Sort.heapSort(arr, false);
            Assert.assertTrue(validateSort(arr, false));
        }
    }

    @Test
    void modifiedQuickSort() {
        for (int i=0; i<NUM_OF_TESTS; i++) {
            Integer[] arr = randomArray(ARRAY_SIZE);
            Sort.modifiedQuickSort(arr, true);
            Assert.assertTrue(validateSort(arr, true));

            arr = randomArray(ARRAY_SIZE);
            Sort.modifiedQuickSort(arr, false);
            Assert.assertTrue(validateSort(arr, false));
        }
    }
}
