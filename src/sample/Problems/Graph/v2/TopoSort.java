package sample.Problems.Graph.v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Quick explain
 */
class TopoSort {
    //Function to return list containing vertices in Topological order.
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < V; i++) {
            dfs(adj, visited, stack, i);
        }

        int[] res = new int[V];
        int i = 0;
        while (i != V) {
            res[i++] = stack.pop();
        }
        return res;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj,
                            Set<Integer> visited, Stack<Integer> stack, int i) {
        if (visited.contains(i)) {
            return;
        }
        visited.add(i);
        for (int child : adj.get(i)) {
            dfs(adj, visited, stack, child);
        }

        stack.push(i);
    }
}
