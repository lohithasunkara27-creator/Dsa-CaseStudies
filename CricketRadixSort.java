import java.util.*;

public class CricketRadixSort {

    static class Delivery {
        int over, ball;

        Delivery(int over, int ball) {
            this.over = over;
            this.ball = ball;
        }
    }

    // Counting Sort by Ball
    static void countingSortBall(Delivery[] arr) {
        int n = arr.length;
        Delivery[] output = new Delivery[n];
        int[] count = new int[7]; // balls 1-6

        for (Delivery d : arr)
            count[d.ball]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].ball] - 1] = arr[i];
            count[arr[i].ball]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Counting Sort by Over
    static void countingSortOver(Delivery[] arr) {
        int n = arr.length;
        Delivery[] output = new Delivery[n];
        int[] count = new int[50]; // overs 0-49

        for (Delivery d : arr)
            count[d.over]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].over] - 1] = arr[i];
            count[arr[i].over]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    static void radixSort(Delivery[] arr) {
        countingSortBall(arr);
        countingSortOver(arr);
    }

    public static void main(String[] args) {

        Delivery[] deliveries = {
            new Delivery(2,4),
            new Delivery(1,1),
            new Delivery(3,6),
            new Delivery(1,5),
            new Delivery(2,2),
            new Delivery(3,1),
            new Delivery(1,3),
            new Delivery(2,6),
            new Delivery(3,4),
            new Delivery(1,2)
        };

        radixSort(deliveries);

        System.out.println("Sorted Delivery Records:");

        for (Delivery d : deliveries) {
            System.out.println("(" + d.over + "," + d.ball + ")");
        }

        System.out.println("\nRecords Sorted Successfully Using Radix Sort.");
    }
}