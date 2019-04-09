import java.util.Arrays;

abstract class Output {
    <T extends Comparable<T>> int compare(T e1, T e2) {
        System.err.println("Comparing "+e1+" and "+e2);
        return e1.compareTo(e2);
    }
    <T extends Comparable<T>> void swap(T[]arr, int p1, int p2) {
        System.err.println("Swapping "+arr[p1]+" and "+arr[p2]);
        T temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
    public abstract <T extends Comparable<T>> void sort(T[] arr, boolean asc);
}

class OutputSelectionSort extends Output {

    public <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int pos;
        int order = (asc?-1:1);
        for (int i=0; i<arr.length-1; i++) {
            pos = i;
            for (int j=i+1; j<arr.length; j++) {
                if (compare(arr[j], arr[pos]) == order) pos = j;
            }
            swap(arr, i, pos);
        }
        System.out.println("Final array: "+ Arrays.toString(arr));
    }
}

class OutputInsertionSort extends Output {
    public <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int j;
        int order = (asc?1:-1);
        for (int i = 1; i < arr.length; i++) {
            T val = arr[i];
            j = i - 1;
            while (j >= 0 && compare(arr[j], val) == order) {
                System.out.println("Shifting "+arr[j]+" right");
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = val;
        }
        System.out.println("Final array: "+ Arrays.toString(arr));
    }
}

class OutputQuickSort extends Output {
    public <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?-1:1);
        quickSortR(arr, 0, arr.length-1, order);
        System.out.println("Final array: "+ Arrays.toString(arr));
    }

    private <T extends Comparable<T>> void quickSortR(T[] arr, int left, int right, int order) {
        if (left<right) {
            int pivotPos = quickSortPartition(arr, left, right, order);
            quickSortR(arr, left, pivotPos-1, order);
            quickSortR(arr, pivotPos+1, right, order);
        }
    }

    private <T extends Comparable<T>> int quickSortPartition(T[] arr, int left, int right, int order) {
        T pivot = arr[right];
        int i = left;
        for (int j=left; j<right; j++) {
            if (compare(arr[j], pivot) == order) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }
}

class OutputHeapSort extends Output {
    public <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?1:-1);
        int len = arr.length;
        for (int i = len/2-1; i>=0; i--) {
            heapify(arr, len, i, order);
        }
        for (int i=len-1; i>0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0, order);
        }
        System.out.println("Final array: "+ Arrays.toString(arr));
    }

    private <T extends Comparable<T>> void heapify(T[] arr, int len, int i, int order) {
        int max = i;
        int left = 2*i+1;
        int right= 2*i+2;


        if (left<len && compare(arr[left], arr[max]) == order) {
            max = left;
        }

        if (right<len && compare(arr[right], arr[max]) == order) {
            max = right;
        }

        if (max != i) {
            swap(arr, i, max);
            heapify(arr, len, max, order);
        }
    }
}

@SuppressWarnings({"Duplicates"})
class OutputModifiedQuickSort extends Output {
    public <T extends Comparable<T>> void sort(T[] arr, boolean asc) {
        int order = (asc?-1:1);
        modifiedQuickSortR(arr, 0, arr.length-1, order, asc);
        System.out.println("Final array: "+ Arrays.toString(arr));
    }

    private <T extends Comparable<T>> void modifiedQuickSortR(T[] arr, int left, int right, int order, boolean asc) {
        if (left<right && (right-left)>16) {
            int pivotPos = modifiedQuickSortPartition(arr, left, right, order);
            modifiedQuickSortR(arr, left, pivotPos-1, order, asc);
            modifiedQuickSortR(arr, pivotPos+1, right, order, asc);
        } else {
            modifiedInsertSort(arr, left, right+1, asc);
        }
    }

    private <T extends Comparable<T>> void modifiedInsertSort(T[] arr, int left, int right, boolean asc) {
        int j;
        int order = (asc?1:-1);
        for (int i = left; i < right; i++) {
            T val = arr[i];
            j = i - 1;
            while (j >= 0 && compare(arr[j], val) == order) {
                arr[j+1] = arr[j];
                System.out.println("Shifting "+arr[j]+" right");
                j--;
            }
            arr[j+1] = val;
        }
    }

    private <T extends Comparable<T>> int median(T[] arr, int e1, int e2, int e3) {
        T v1 = arr[e1];
        T v2 = arr[e2];
        T v3 = arr[e3];
        if (compare(v1, v2) >= 0) {
            if (compare(v3, v1) >=0)
                return e1;
            else if (compare(v3, v2) >= 0)
                return e3;
            else
                return e2;
        } else {
            if (compare(v3, v2)>=0)
                return e2;
            else if (compare(v3, v1)>=0)
                return e3;
            else
                return e1;
        }
    }

    private <T extends Comparable<T>> int modifiedQuickSortPartition(T[] arr, int left, int right, int order) {
        int pivotIndex = median(arr, left, (right-left)/2, right);
        T pivot = arr[pivotIndex];
        if (pivotIndex != right) {
            swap(arr, pivotIndex, right);
        }
        int i = left;
        for (int j=left; j<right; j++) {
            if (compare(arr[j], pivot) == order) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }
}