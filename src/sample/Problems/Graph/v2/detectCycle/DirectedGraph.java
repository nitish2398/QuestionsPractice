package sample.Problems.Graph.v2.detectCycle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions
 * https://www.youtube.com/watch?v=rKQaZuoUR4M
 *
 * create three sets
 * White, grey and black
 *
 * white set contains all the nodes
 * when we start exploring move a node from white to grey set
 * when we are done exploring all the children of that node, move it to black set
 * If we encounter a node again, while the node is already in grey set, that means graph contains a cycle.
 *
 * to print the actual cycle, maintain a child -> parent map
 * having the last node of cycle, print all the nodes from map.
 */
public class DirectedGraph {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        Set<Integer> whiteSet = new HashSet<>();
        Set<Integer> greySet = new HashSet<>();
        Set<Integer> blackSet = new HashSet<>();

        for (int i = 0; i < V; i++) {
            whiteSet.add(i);
        }

        final boolean res[] = {false};

        while (!whiteSet.isEmpty()) {
            if (dfs(adj, whiteSet, greySet, blackSet, whiteSet.stream().findAny().get())) {
                res[0] = true;
                break;
            }
        }

        return res[0];
    }

    boolean dfs(ArrayList<ArrayList<Integer>> adj,
                Set<Integer> whiteSet,
                Set<Integer> greySet,
                Set<Integer> blackSet,
                int v) {
        if (blackSet.contains(v)) {
            return false;
        }
        if (greySet.contains(v)) {
            return true;
        }
        if (whiteSet.contains(v)) {
            whiteSet.remove(v);
            greySet.add(v);
        }
        for (int child : adj.get(v)) {
            if (dfs(adj, whiteSet, greySet, blackSet, child)) {
                return true;
            }
        }
        blackSet.add(v);
        return false;
    }


    public static void main(String[] args) {

    }
}
