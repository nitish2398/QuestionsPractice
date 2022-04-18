package sample.Problems.Graph.v2.detectCycle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Using the DFS
 *
 * use a visited set to keep track of visited node
 * use a constant space to keep track of parent node of the vertex
 * do not consider child to parent node again
 * if a node is visited again then the graph contains a cycle.
 *
 * https://www.youtube.com/watch?v=n_t0a_8H8VY
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions#
 *
 */
public class UndirectedGraph {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < V; i++) {
            if (!visited.contains(i)) {
                if (dfs(adj, visited, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(ArrayList<ArrayList<Integer>> adj, Set<Integer> visited, int v, int parent) {
        visited.add(v);
        for (int child : adj.get(v)) {
            if(child == parent) {
                continue;
            }
            if(visited.contains(child)) {
                return true;
            }
            if(dfs(adj, visited, child, v)) {
                return true;
            }
        }
        return false;
    }
}
