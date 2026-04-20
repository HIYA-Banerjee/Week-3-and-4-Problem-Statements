import java.util.*;

// Asset class
class Asset {
    String name;
    double returnRate;   // %
    double volatility;   // %

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting {

    // ---------------- MERGE SORT (ASC, STABLE by returnRate) ----------------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Stable merge (<= preserves order for ties)
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ---------------- QUICK SORT (DESC returnRate + ASC volatility) ----------------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Median-of-3 pivot selection (better than random in practice)
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low], b = arr[mid], c = arr[high];

        if ((a.returnRate > b.returnRate && a.returnRate < c.returnRate) ||
                (a.returnRate < b.returnRate && a.returnRate > c.returnRate)) return low;

        if ((b.returnRate > a.returnRate && b.returnRate < c.returnRate) ||
                (b.returnRate < a.returnRate && b.returnRate > c.returnRate)) return mid;

        return high;
    }

    private static int partition(Asset[] arr, int low, int high) {

        int pivotIndex = medianOfThree(arr, low, high);
        swap(arr, pivotIndex, high);

        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (compare(arr[j], pivot) < 0) { // DESC logic
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Comparator: returnRate DESC, volatility ASC
    private static int compare(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            return Double.compare(a2.returnRate, a1.returnRate);
        }
        return Double.compare(a1.volatility, a2.volatility);
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        // Clone arrays
        Asset[] mergeArr = assets.clone();
        Asset[] quickArr = assets.clone();

        // Merge Sort
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (ASC): " + Arrays.toString(mergeArr));

        // Quick Sort
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (DESC): " + Arrays.toString(quickArr));
    }
}