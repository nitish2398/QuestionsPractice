package sample.Problems.Graph;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Rotten Oranges
 * <p>
 * Given a matrix of dimension r*c where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:
 * 0 : Empty cell
 * 1 : Cells have fresh oranges
 * 2 : Cells have rotten oranges
 * <p>
 * So, we have to determine what is the minimum time required to rot all oranges.
 * A rotten orange at index [i,j] can rot other fresh orange
 * at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time.
 * If it is impossible to rot every orange then simply return -1.
 */

class RottenOranges {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();
            String[] input = scan.nextLine().split(" ");

            int[][] arr = new int[n][m];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.valueOf(input[index++]);
                }
            }

            int[] xDist = {1, 0, -1, 0};
            int[] yDist = {0, 1, 0, -1};

            boolean[][] visited = new boolean[n][m];
            Queue<Point> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 2) {
                        for (int k = 0; k < 4; k++) {
                            Point newPoint = new Point(i + xDist[k], j + yDist[k]);
                            if (newPoint.isValid(n, m) &&
                                    arr[newPoint.x][newPoint.y] == 1 &&
                                    !visited[newPoint.x][newPoint.y]) {
                                visited[newPoint.x][newPoint.y] = true;
                                queue.add(newPoint);
                            }
                        }
                    }
                }
            }
            queue.add(null);
            int count = 0;

            while (!queue.isEmpty()) {
                Point current = queue.poll();

                if (current == null) {
                    count++;
                    if (!queue.isEmpty()) {
                        queue.add(null);
                    }
                } else {
                    int i = current.x;
                    int j = current.y;
                    for (int k = 0; k < 4; k++) {
                        Point newPoint = new Point(i + xDist[k], j + yDist[k]);
                        if (newPoint.isValid(n, m) &&
                                arr[newPoint.x][newPoint.y] == 1 &&
                                !visited[newPoint.x][newPoint.y]) {
                            visited[newPoint.x][newPoint.y] = true;
                            queue.add(newPoint);
                        }
                    }

                }
            }

            boolean isMatrixComplete = isMatrixComplete(arr, visited);
            if (isMatrixComplete) {
                System.out.println(count);
            } else {
                System.out.println("-1");
            }
            t--;
        }
    }

    private static boolean isMatrixComplete(int[][] arr, boolean[][] visited) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isValid(int n, int m) {
            return this.x >= 0 && this.x < n && this.y >= 0 && this.y < m;
        }
    }
}
