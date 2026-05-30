import java.util.*;

public class QuickRouteDijkstra {

    static final int V = 5;

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void dijkstra(int graph[][], int source) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("=== QuickRoute Delivery Optimization System ===\n");

        System.out.println("Warehouse = Node 0");
        System.out.println("\nShortest Distance from Warehouse:\n");

        for (int i = 1; i < V; i++) {
            System.out.println("Location " + i + " = " + dist[i] + " km");
        }

        System.out.println("\nRoute Optimization Completed Successfully");
        System.out.println("Time Complexity = O(V²)");
    }

    public static void main(String[] args) {

        int graph[][] = {
                {0, 4, 2, 0, 0},
                {4, 0, 1, 5, 0},
                {2, 1, 0, 8, 3},
                {0, 5, 8, 0, 2},
                {0, 0, 3, 2, 0}
        };

        QuickRouteDijkstra obj = new QuickRouteDijkstra();
        obj.dijkstra(graph, 0);
    }
}
