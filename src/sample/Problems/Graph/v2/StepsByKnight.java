package sample.Problems.Graph.v2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a square chessboard, the initial position of Knight and position of a target.
 * Find out the minimum steps a Knight will take to reach the target position.
 *
 * Note:
 * The initial and the target position coordinates of Knight have been given according to 1-base indexing.
 *
 * https://practice.geeksforgeeks.org/problems/steps-by-knight5927/1/?page=1&company[]=Amazon&category[]=Graph&sortBy=submissions#
 *
 */
public class StepsByKnight {
    public int minStepToReachTarget(int start[], int target[], int n)
    {
        boolean visited [][] = new boolean[n+1][n+1];
        Queue<int[]> queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        queue.add(new int [] {start[0], start[1], 0});

        int dirs[][] = new int [][] {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}
        };

        while(!queue.isEmpty()) {
            int [] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];
            System.out.println(Arrays.toString(current));
            if(x == target[0] && y == target[1]) {
                return dist;
            }

            for(int [] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if(isValid(nx, ny, n) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int [] {nx, ny, dist + 1});
                }
            }
        }

        return -1;
    }

    private boolean isValid(int x, int y, int n) {
        return x > 0 && y > 0 && x <= n && y <= n;
    }

    public static void main(String[] args) {
        int [] start = {1, 1};
        int [] target = {7, 5};

        System.out.println(new StepsByKnight().minStepToReachTarget(start, target, 7));
    }
}
