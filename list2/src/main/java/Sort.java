import java.util.Random;

class RandomArray {
    static Integer[] get(int n) {
        Random r = new Random();
        Integer[] arr = new Integer[n];
        for (int i=0; i<n; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }
}

class Swap {
    static <T extends Comparable<T>> void swap(T[] arr, int p1, int p2) {
        T temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }}

class SelectionSort {
    public static <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int pos;
        int order = (asc?-1:1);
        for (int i=0; i<arr.length-1; i++) {
            pos = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j].compareTo(arr[pos]) == order) pos = j;
            }
            Swap.swap(arr, i, pos);
        }
    }
}

class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int j;
        int order = (asc?1:-1);
        for (int i = 1; i < arr.length; i++) {
            T val = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j].compareTo(val) == order) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = val;
        }
    }
}

class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?-1:1);
        quickSortR(arr, 0, arr.length-1, order);
    }

    private static <T extends Comparable<T>> void quickSortR(T[] arr, int left, int right, int order) {
        if (left<right) {
            int pivotPos = quickSortPartition(arr, left, right, order);
            quickSortR(arr, left, pivotPos-1, order);
            quickSortR(arr, pivotPos+1, right, order);
        }
    }

    private static <T extends Comparable<T>> int quickSortPartition(T[] arr, int left, int right, int order) {
        T pivot = arr[right];
        int i = left;
        for (int j=left; j<right; j++) {
            int comparision = arr[j].compareTo(pivot);
            if (comparision == order) {
                Swap.swap(arr, i, j);
                i++;
            }
        }
        Swap.swap(arr, i, right);
        return i;
    }
}

@SuppressWarnings({"Duplicates"})
class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?1:-1);
        int len = arr.length;
        for (int i = len/2-1; i>=0; i--) {
            heapify(arr, len, i, order);
        }
        for (int i=len-1; i>0; i--) {
            Swap.swap(arr, 0, i);
            heapify(arr, i, 0, order);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] arr, int len, int i, int order) {
        int max = i;
        int left = 2*i+1;
        int right= 2*i+2;

        if (left<len && arr[left].compareTo(arr[max]) == order) {
            max = left;
        }
        if (right<len && arr[right].compareTo(arr[max]) == order) {
            max = right;
        }
        if (max != i) {
            Swap.swap(arr, i, max);
            heapify(arr, len, max, order);
        }
    }
}

class ModifiedQuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?-1:1);
        modifiedQuickSortR(arr, 0, arr.length-1, order, asc);
    }

    private static <T extends Comparable<T>> void modifiedQuickSortR(T[] arr, int left, int right, int order, boolean asc) {
        if (left<right && (right-left)>16) {
            int pivotPos = modifiedQuickSortPartition(arr, left, right, order, asc);
            modifiedQuickSortR(arr, left, pivotPos-1, order, asc);
            modifiedQuickSortR(arr, pivotPos+1, right, order, asc);
        } else {
            modifiedInsertSort(arr, left, right+1, asc);
        }
    }

    private static <T extends Comparable<T>> void modifiedInsertSort(T[] arr, int left, int right, boolean asc) {
        int j;
        int order = (asc?1:-1);
        for (int i = left; i < right; i++) {
            T val = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j].compareTo(val) == order) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = val;
        }
    }

    private static <T extends Comparable<T>> int median(T[] arr, int e1, int e2, int e3) {
        T v1 = arr[e1];
        T v2 = arr[e2];
        T v3 = arr[e3];
        if (v1.compareTo(v2) >= 0) {
            if (v3.compareTo(v1) >=0)
                return e1;
            else if (v3.compareTo(v2) >= 0)
                return e3;
            else
                return e2;
        } else {
            if (v3.compareTo(v2) >=0)
                return e2;
            else if (v3.compareTo(v1) >=0)
                return e3;
            else
                return e1;
        }
    }

    private static <T extends Comparable<T>> int modifiedQuickSortPartition(T[] arr, int left, int right, int order, boolean asc) {
        int pivotIndex = median(arr, left, (right-left)/2, right);
        T pivot = arr[pivotIndex];
        if (pivotIndex != right) {
            Swap.swap(arr, pivotIndex, right);
        }
        int i = left;
        for (int j=left; j<right; j++) {
            int comparision = arr[j].compareTo(pivot);
            if (comparision == order) {
                Swap.swap(arr, i, j);
                i++;
            }
        }
        Swap.swap(arr, i, right);
        return i;
    }
}