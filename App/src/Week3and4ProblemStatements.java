import java.util.*;

// Client class
class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRiskRanking {

    // ---------------- BUBBLE SORT (Ascending riskScore) ----------------
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualization (important for demo 🎯)
                    System.out.println("Swap: " + Arrays.toString(arr));
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("\nBubble Sorted (Ascending): " + Arrays.toString(arr));
        System.out.println("Total Swaps: " + swaps);
    }

    // ---------------- INSERTION SORT (Descending riskScore + balance) ----------------
    public static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Descending riskScore, then ascending balance
            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sorted (Descending riskScore): " + Arrays.toString(arr));
    }

    // Comparator logic
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c2.accountBalance, c1.accountBalance);
    }

    // ---------------- TOP N HIGH RISK ----------------
    public static void topHighRisk(Client[] arr, int topN) {
        System.out.println("\nTop " + topN + " High Risk Clients:");

        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Clone arrays for separate sorting
        Client[] bubbleArr = clients.clone();
        Client[] insertionArr = clients.clone();

        // Bubble Sort
        bubbleSort(bubbleArr);

        // Insertion Sort
        insertionSort(insertionArr);

        // Top Risk Clients
        topHighRisk(insertionArr, 3);
    }
}