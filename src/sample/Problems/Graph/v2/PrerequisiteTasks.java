package sample.Problems.Graph.v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/prerequisite-tasks/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions
 */
public class PrerequisiteTasks {
    public boolean isPossible(int N, int[][] prerequisites)
    {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for(int [] req: prerequisites) {
            adj.get(req[1]).add(req[0]);
        }

        return !isCyclic(N, adj);
    }

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
}
