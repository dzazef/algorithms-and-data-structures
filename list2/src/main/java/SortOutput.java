import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "Duplicates"})
class SortOutput {
    private static <T extends Comparable<T>> void swap(T[] arr, int p1, int p2) {
        System.out.println("Swapping "+arr[p1]+" and "+arr[p2]);
        T temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    public static <T extends Comparable<T>> void selectionSort(T[] arr, boolean asc) {
        int pos;
        int order = (asc?-1:1);
        System.err.println("Starting selection sort by "+(asc?"ascending":"descending")+".");
        System.err.println("Array: "+ Arrays.toString(arr));
        for (int i=0; i<arr.length-1; i++) {
            System.err.println("Possible "+(asc?"min":"max")+" element: "+arr[i].toString());
            pos = i;
            for (int j=i+1; j<arr.length; j++) {
                System.err.print("Comparing "+arr[j].toString()+" to "+arr[pos].toString());
                if (arr[j].compareTo(arr[pos]) == order) pos = j;
                System.err.println(", current "+(asc?"min":"max")+" element: "+arr[pos].toString());
            }
            System.err.println("Switching "+arr[i].toString()+" and "+arr[pos].toString());
            swap(arr, i, pos);
            System.err.println("Current array: "+Arrays.toString(arr));
        }
        System.err.println("Final array: "+Arrays.toString(arr));
    }


    public static <T extends Comparable<T>> void insertSort(T[] arr, boolean asc) {
        int j;
        int order = (asc?1:-1);
        System.err.println("Starting insertion sort by "+(asc?"ascending":"descending")+".");
        System.err.println("Array: "+Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            T val = arr[i];
            System.err.println("Examined element: "+val.toString());
            j = i - 1;
//            if (!(j >= 0 && arr[j].compareTo(val) == order))
            System.err.println("Checking if "+j+" is more or equal than 0 and comparing "+arr[j].toString()+" and "+val.toString());
            while (j >= 0 && arr[j].compareTo(val) == order) {
                System.err.println("Checking if "+j+" is more or equal than 0 and comparing "+arr[j].toString()+" and "+val.toString());
                arr[j+1] = arr[j];
                System.err.println("Shifting "+arr[j].toString()+" right");
                j--;
            }
            System.err.println("Inserting "+val.toString()+" on place "+(j+1));
            arr[j+1] = val;
            System.err.println("Current array: "+Arrays.toString(arr));
        }
        System.err.println("Final array: "+Arrays.toString(arr));
    }


    public static <T extends Comparable<T>> void quickSort(T[] arr, boolean asc) {
        int order = (asc?-1:1);
        System.err.println("Array: "+Arrays.toString(arr));
        quickSortR(arr, 0, arr.length-1, order);
        System.err.println("Final array: "+Arrays.toString(arr));
    }

    private static <T extends Comparable<T>> void quickSortR(T[] arr, int left, int right, int order) {
        if (left<right) {
            int pivotPos = partition(arr, left, right, order);
            System.err.println("Element "+arr[pivotPos].toString()+" is on position "+pivotPos);
            System.err.println("Current array: "+Arrays.toString(arr));
            quickSortR(arr, left, pivotPos-1, order);
            quickSortR(arr, pivotPos+1, right, order);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right, int order) {
        T pivot = arr[right];
        int i = left;
        for (int j=left; j<right; j++) {
            System.err.println("Comparing "+arr[j].toString()+" and "+pivot.toString());
            if (arr[j].compareTo(pivot) == order) {
                System.err.println("Swapping "+arr[i]+" and "+arr[j]);
                swap(arr, i, j);
                i++;
            }
        }
        System.err.println("Swapping "+arr[i]+" and "+arr[right]);
        swap(arr, i, right);
        return i;
    }
}
