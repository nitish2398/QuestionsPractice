package sample.Problems.DynamicProgramming;

/*
Given a n*n matrix where all numbers are distinct, find the maximum length path
(starting from any cell) such that all cells along the path are in increasing order with a difference of 1.

We can move in 4 directions from a given cell (i, j), i.e.,
we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that the adjacent cells have a difference of 1.

Example:

Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9.
*/

public class LongestPathInMatrix {
    static int[][] dp;

    private static int maximumPath(int N, int arr[][]) {
        dp = new int[N][N];
        int longestPath = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int currentPathLength = explorePath(arr, i, j);
                longestPath = Math.max(longestPath, currentPathLength);
            }
        }

        return longestPath;
    }

    private static int explorePath(int[][] arr, int i, int j) {
        if (isValidIndex(arr, i, j) && dp[i][j] == 0) {

            int result = 1;

            if (isValidIndex(arr, i + 1, j) && arr[i][j] + 1 == arr[i + 1][j]) {
                result = Math.max(result, 1 + explorePath(arr, i + 1, j));
            }

            if (isValidIndex(arr, i, j + 1) && arr[i][j] + 1 == arr[i][j + 1]) {
                result = Math.max(result, 1 + explorePath(arr, i, j + 1));
            }

            if (isValidIndex(arr, i - 1, j) && arr[i][j] + 1 == arr[i - 1][j]) {
                result = Math.max(result, 1 + explorePath(arr, i - 1, j));
            }

            if (isValidIndex(arr, i, j - 1) && arr[i][j] + 1 == arr[i][j - 1]) {
                result = Math.max(result, 1 + explorePath(arr, i, j - 1));
            }

            dp[i][j] = result;
        }

        return dp[i][j];
    }

    private static boolean isValidIndex(int[][] arr, int i, int j) {
        return i >= 0 && i < arr.length && j >= 0 && j < arr[i].length;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 9},
                {5, 3, 8},
                {4, 6, 7}
        };

        System.out.println(maximumPath(arr.length, arr));
    }
}
