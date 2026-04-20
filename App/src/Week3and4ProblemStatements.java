import java.util.*;

// Transaction class
class Transaction {
    String id;
    double fee;
    String timestamp; // HH:MM format

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorting {

    // ---------------- BUBBLE SORT (by fee) ----------------
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination (best case O(n))
            if (!swapped) break;
        }

        System.out.println("\nBubble Sort Result (by fee): " + list);
        System.out.println("Total passes: " + n + ", swaps: " + swaps);
    }

    // ---------------- INSERTION SORT (fee + timestamp) ----------------
    public static void insertionSort(List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare by fee first, then timestamp (stable)
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result (fee + timestamp): " + list);
    }

    // Comparator: fee → timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // ---------------- OUTLIER DETECTION ----------------
    public static void findHighFeeOutliers(List<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (>50):");

        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Clone list for separate sorts
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        // Apply sorting
        bubbleSort(bubbleList);
        insertionSort(insertionList);

        // Outlier detection
        findHighFeeOutliers(transactions);
    }
}