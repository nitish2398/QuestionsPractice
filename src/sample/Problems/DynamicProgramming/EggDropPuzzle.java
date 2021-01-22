package sample.Problems.DynamicProgramming;

/*
Example 1:

Input:
N = 2, K = 10
Output: 4
Example 2:

Input:
N = 3, K = 5
Output: 3
 */
public class EggDropPuzzle {
    static int eggDrop(int n, int k) {
        int dp[][] = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int j = 0; j <= k; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int x = 1; x <= j; x++) {
                    int result = 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]);
                    dp[i][j] = Math.min(dp[i][j], result);
                }
            }
        }

        return dp[n][k];
    }
}
