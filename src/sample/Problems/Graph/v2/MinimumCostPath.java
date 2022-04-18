package sample.Problems.Graph.v2;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-cost-path3833/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions
 */
public class MinimumCostPath {
    private static final int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    public int minimumCostPath(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<Node> queue = new PriorityQueue<>();

        visited[0][0] = true;
        queue.add(new Node(0,0, grid[0][0]));

        while (!queue.isEmpty()) {
            int x = queue.peek().x;
            int y = queue.peek().y;
            int cost = queue.peek().cost;

            queue.poll();

            if(x == grid.length - 1 && y == grid[0].length - 1) {
                return cost;
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (isValid(nx, ny, grid) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, cost + grid[nx][ny]));
                }
            }
        }

        return -1;
    }

    private boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
           return this.cost - o.cost;
        }
    }
}
