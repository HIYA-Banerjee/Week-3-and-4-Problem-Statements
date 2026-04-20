import java.util.*;

public class AccountIdLookup {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1, comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("\nLinear Search:");
        if (first != -1) {
            System.out.println("First occurrence: " + first);
            System.out.println("Last occurrence: " + last);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)");
    }

    // ---------------- BINARY SEARCH (FIRST OCCURRENCE) ----------------
    public static int binaryFirst(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                high = mid - 1; // go left
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ---------------- BINARY SEARCH (LAST OCCURRENCE) ----------------
    public static int binaryLast(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                low = mid + 1; // go right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Counter class for comparisons
    static class Counter {
        int count = 0;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // -------- LINEAR SEARCH (works on unsorted) --------
        linearSearch(logs, "accB");

        // -------- SORT BEFORE BINARY SEARCH --------
        Arrays.sort(logs);
        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        Counter counter = new Counter();

        int first = binaryFirst(logs, "accB", counter);
        int last = binaryLast(logs, "accB", counter);

        System.out.println("\nBinary Search:");
        if (first != -1) {
            System.out.println("First index: " + first);
            System.out.println("Last index: " + last);
            System.out.println("Count: " + (last - first + 1));
        } else {
            System.out.println("Not found");
        }

        System.out.println("Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }
}