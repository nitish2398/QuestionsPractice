package sample.Problems.Graph;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Strongly connected component using TARJANs' ALGO
 *
 *https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
 *
 * candidates have same low point belong to same component
 * if B is child of A
 *   1. if not visited yet, then visit B and update A's lowPoint using B's lowPoint
 *   2. if already visited, then update A's lowPoint using B's TimeToReach
 *
 * If it is a root node then print the strongly connected vertex in this component
 */

public class TarjanStronglyConnectedComponent {

    int time;

    public void findStronglyConnectedComponent(ArrayList<ArrayList<Integer>> adj, int N) {
        time = 0;
        Stack<Integer> stack = new Stack<>();
        boolean[] inStack = new boolean[adj.size()];

        int[] timeToReach = new int[adj.size()];
        int[] lowPoint = new int[adj.size()];

        for (int i = 0; i < adj.size(); i++) {
            timeToReach[i] = -1;
            lowPoint[i] = -1;
        }

        for (int i = 0; i < adj.size(); i++) {
            if (timeToReach[i] == -1) {
                findSscUtil(adj, timeToReach, lowPoint, stack, inStack, i);
            }
        }
    }

    private void findSscUtil(ArrayList<ArrayList<Integer>> adj,
                             int[] timeToReach,
                             int[] lowPoint,
                             Stack<Integer> stack,
                             boolean[] inStack, int u) {
        timeToReach[u] = time;
        lowPoint[u] = time;
        stack.push(u);
        inStack[u] = true;

        for (int v : adj.get(u)) {
            if (timeToReach[v] == -1) {
                findSscUtil(adj, timeToReach, lowPoint, stack, inStack, v);

                lowPoint[u] = Math.min(lowPoint[u], lowPoint[v]);
            } else if (inStack[v]) {
                lowPoint[u] = Math.min(lowPoint[u], timeToReach[v]);
            }
        }

        // if we are on Head Node
        if (lowPoint[u] == timeToReach[u]) {
            while (!stack.empty() && stack.peek() != u) {
                int x = stack.pop();
                System.out.print(x + " ");
                inStack[x] = false;
            }

            if (!stack.empty() && stack.peek() == u) {
                int x = stack.pop();
                System.out.print(x);
                inStack[x] = false;
            }
            System.out.print(",");
        }
    }
}

