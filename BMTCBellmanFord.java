// BMTC Shortest Path using Bellman-Ford Algorithm
public class BMTCBellmanFord {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static final int V = 7;
    static final String[] hubs = {
        "MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"
    };

    public static void bellmanFord(Edge[] edges, int source) {
        int[] dist = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE &&
                dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Negative Weight Cycle Detected!");
                return;
            }
        }

        System.out.println("Shortest Distance from MJC:");
        for (int i = 0; i < V; i++) {
            System.out.println(hubs[i] + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {

        Edge[] edges = {
            new Edge(0, 1, 8),   // MJC -> KEM
            new Edge(0, 2, 5),   // MJC -> JAY
            new Edge(0, 3, 12),  // MJC -> KOR
            new Edge(2, 3, 4),   // JAY -> KOR
            new Edge(1, 5, 7),   // KEM -> HBR
            new Edge(1, 4, 10),  // KEM -> WHF
            new Edge(3, 4, 6),   // KOR -> WHF
            new Edge(3, 6, 9),   // KOR -> MRT
            new Edge(4, 5, 3),   // WHF -> HBR
            new Edge(5, 6, 11),  // HBR -> MRT
            new Edge(4, 6, -3)   // WHF -> MRT (Negative Edge)
        };

        bellmanFord(edges, 0); // Source = MJC
    }
}