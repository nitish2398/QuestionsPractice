package sample.Problems.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Given a graph with N nodes and M directed edges. Your task is to complete the function kosaraju()
 * which returns an integer denoting the number of strongly connected components in the graph.
 * <p>
 * https://www.youtube.com/watch?v=RpgcYiky7uw
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/StronglyConnectedComponent.java
 */

class KosarajuStonglyConnectedComponent {
    Stack<Integer> stack = new Stack<>();
    int result = 0;

    public int kosaraju(ArrayList<ArrayList<Integer>> adj, int N) {
        boolean visited[] = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                doDFS(adj, i, visited);
            }
        }
        System.out.println(stack);
        adj = reverseGraph(adj);

        boolean visitedV2[] = new boolean[N];
        while (!stack.empty()) {
            int root = stack.pop();
            if (!visitedV2[root]) {
                visitedV2[root] = true;
                doDFS(adj, root, visitedV2);
                result++;
            }
        }

        return result;
    }

    private void doDFS(ArrayList<ArrayList<Integer>> adj, int i, boolean visited[]) {
        if (adj.get(i) != null) {
            adj.get(i).forEach((child) -> {
                if (!visited[child]) {
                    visited[child] = true;
                    doDFS(adj, child, visited);
                }
            });
        }
        System.out.print(i + " ");
        stack.push(i);
    }

    private ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            reverseGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < adj.size(); i++) {
            final int node = i;
            if (adj.get(i) != null) {
                adj.get(i).forEach(child -> {
                    reverseGraph.get(child).add(node);
                });
            }
        }
        return reverseGraph;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>() {{
            add(new ArrayList<Integer>() {{
                add( 2);
                add(3);
            }});
            add(new ArrayList<Integer>() {{
                add(0);
            }});
            add(new ArrayList<Integer>() {{
                add(1);
            }});
            add(new ArrayList<Integer>() {{
                add(4);
            }});
            add(new ArrayList<Integer>() {{
            }});
        }};
        System.out.println(new KosarajuStonglyConnectedComponent()
                .kosaraju(list, 5));
    }
}

