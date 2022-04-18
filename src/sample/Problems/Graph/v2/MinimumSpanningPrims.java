package sample.Problems.Graph.v2;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions#
 */
public class MinimumSpanningPrims {
    //Function to find sum of weights of edges of the Minimum Spanning Tree.

    /**
     * Adj input format ->>
     * index of list is vertex, containing a list having next vertex and weight
     * <p>
     * for a vertex 1 --> 2 with weight 4
     * adj[1] ---> [[2, 4]]
     */
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(new Node(0, 0));
        int ans = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek().vertex;
            int weight = queue.peek().weight;
            queue.poll();
            if (!visited.contains(node)) {
                ans += weight;
                visited.add(node);
                ArrayList<ArrayList<Integer>> vertices = adj.get(node);
                for (ArrayList<Integer> vertex : vertices) {
                    if (!visited.contains(vertex.get(0))) {
                        queue.add(new Node(vertex.get(0), vertex.get(1)));
                    }
                }
            }
        }
        return ans;
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }
}
