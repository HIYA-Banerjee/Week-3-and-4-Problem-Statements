import java.util.*;

public class RiskThresholdLookup {

    // ---------------- LINEAR SEARCH (UNSORTED) ----------------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)");
    }

    // ---------------- BINARY SEARCH INSERTION POINT ----------------
    public static int insertionPoint(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // insertion index
    }

    // ---------------- FLOOR (largest ≤ target) ----------------
    public static Integer floor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ---------------- CEILING (smallest ≥ target) ----------------
    public static Integer ceiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // Counter class
    static class Counter {
        int count = 0;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // Linear search (unsorted)
        linearSearch(unsorted, target);

        // Sort before binary operations
        int[] sorted = {10, 25, 50, 100};
        System.out.println("\nSorted Risks: " + Arrays.toString(sorted));

        Counter counter = new Counter();

        int insertIndex = insertionPoint(sorted, target, counter);
        Integer floorVal = floor(sorted, target, counter);
        Integer ceilVal = ceiling(sorted, target, counter);

        System.out.println("\nBinary Results:");
        System.out.println("Insertion index: " + insertIndex);
        System.out.println("Floor: " + floorVal);
        System.out.println("Ceiling: " + ceilVal);
        System.out.println("Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }
}