package sample.Problems.DynamicProgramming.PartitionProblem;

/*
Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is the same.

Examples:

arr[] = {1, 5, 11, 5}
Output: true
The array can be partitioned as {1, 5, 5} and {11}

arr[] = {1, 5, 3}
Output: false
The array cannot be partitioned into equal sum sets.
*/

import sample.CustomException.MyException;

public class EqualSumPartition {
    private static int equalPartition(int n, int[] arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        // if sum is odd, then can not be partitioned
        if (sum % 2 == 1) {
            return 0;
        }

        int targetSum = sum / 2;

        int[][] dp = new int[n + 1][targetSum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= targetSum; j++) {
                if (i == 0 && targetSum != 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j];

                if (j - arr[i - 1] >= 0 && dp[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 5};

        System.out.println(equalPartition(arr.length, arr));

        try {
            MyException exception = new MyException("jgd");
            exception = null;
            System.gc();
        } catch (Throwable e) {
        }
    }
}
