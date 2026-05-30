import java.util.*;

public class ShopTrackSegmentTree {

    static int[] tree;
    static int[] sales;

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = sales[start];
        } else {
            int mid = (start + end) / 2;

            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    static int query(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node, start, mid, l, r)
                + query(2 * node + 1, mid + 1, end, l, r);
    }

    static void update(int node, int start, int end, int idx, int value) {

        if (start == end) {
            sales[idx] = value;
            tree[node] = value;
        } else {

            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node, start, mid, idx, value);
            else
                update(2 * node + 1, mid + 1, end, idx, value);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {

        sales = new int[]{1000, 1500, 2000, 2500, 3000};

        int n = sales.length;
        tree = new int[4 * n];

        build(1, 0, n - 1);

        System.out.println("=== ShopTrack Sales Analytics System ===\n");

        System.out.println("Daily Sales:");
        for (int s : sales) {
            System.out.print(s + " ");
        }

        System.out.println("\n\nSegment Tree Created Successfully");

        int totalSales = query(1, 0, n - 1, 1, 3);

        System.out.println("\nQuery:");
        System.out.println("Total Sales from Day 2 to Day 4 = " + totalSales);

        System.out.println("\nUpdating Day 3 Sales to 2200");

        update(1, 0, n - 1, 2, 2200);

        int updatedSales = query(1, 0, n - 1, 1, 3);

        System.out.println("\nUpdated Query:");
        System.out.println("Total Sales from Day 2 to Day 4 = " + updatedSales);

        System.out.println("\nTime Complexity:");
        System.out.println("Range Query = O(log n)");
        System.out.println("Update = O(log n)");
    }
}
