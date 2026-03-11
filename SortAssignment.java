import java.util.Arrays;
import java.util.Random;

public class SortAssignment {

    public static void main(String[] args) {
        // 設定測試資料量 (例如 10000 筆)
        int n = 10000;
        int[] data = generateRandomArray(n);

        // 1. Bubble Sort 測試
        testSort("Bubble Sort", Arrays.copyOf(data, data.length));

        // 2. Insertion Sort 測試
        testSort("Insertion Sort", Arrays.copyOf(data, data.length));

        // 3. Merge Sort 測試
        testSort("Merge Sort", Arrays.copyOf(data, data.length));

        // 4. Quick Sort 測試
        testSort("Quick Sort", Arrays.copyOf(data, data.length));
    }

    // --- 演算法實作區 ---

    // Bubble Sort: O(n^2)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort: O(n^2)
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort: O(n log n)
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Quick Sort: O(n log n)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // --- 輔助工具 ---

    public static int[] generateRandomArray(int n) {
        Random rd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rd.nextInt(100000);
        return arr;
    }

    public static void testSort(String name, int[] arr) {
        long startTime = System.currentTimeMillis();
        
        // 改用傳統 switch 寫法，相容所有 Java 版本
        switch (name) {
            case "Bubble Sort":
                bubbleSort(arr);
                break;
            case "Insertion Sort":
                insertionSort(arr);
                break;
            case "Merge Sort":
                mergeSort(arr, 0, arr.length - 1);
                break;
            case "Quick Sort":
                quickSort(arr, 0, arr.length - 1);
                break;
            default:
                System.out.println("未知的排序類型");
                return;
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println(name + " 耗時: " + (endTime - startTime) + " 毫秒");
    }
}