import java.util.Arrays;

public class PrimMST {
    private static final int V = 5;

    private int findMinKey(int[] key, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    private void primMST(int[][] graph) {
        int[] key = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = findMinKey(key, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        PrimMST mst = new PrimMST();
        mst.primMST(graph);
    }
}
