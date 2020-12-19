package sample.Problems.DynamicProgramming.PartitionProblem;
/*
Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
If there is a set S with n elements,
then if we assume Subset1 has m elements,
Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.

Example:
Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11
*/

public class MinimumSumPartition {

    // find out all the sums which are possible till max sum
    // the choose the minimum difference

    private static int minDiffernce(int[] arr, int n) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }

        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else {
                    if (i == 0) {
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[i - 1][j];

                        if (j - arr[i - 1] >= 0) {
                            dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                        }
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[n][j]) {
                result = Math.min(result, (sum - j) - j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {11, 18, 24, 18, 18};

        System.out.println(minDiffernce(arr, arr.length));
    }
}
