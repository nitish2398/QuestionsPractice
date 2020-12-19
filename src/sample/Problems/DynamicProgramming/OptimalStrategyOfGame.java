package sample.Problems.DynamicProgramming;

/*
Consider a row of n coins of values v1 . . . vn, where n is even.
We play a game against an opponent by alternating turns.
In each turn, a player selects either the first or last coin from the row,
removes it from the row permanently, and receives the value of the coin.
Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.

Let us understand the problem with few examples:

8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
…….User chooses 7.
…….Opponent chooses 8.
…….User chooses 15.
…….Opponent chooses 3.
Total value collected by user is 22(7 + 15)
*/

/*
https://www.youtube.com/watch?v=WxpIHvsu1RI
*/

public class OptimalStrategyOfGame {
    static long countMaximum(int arr[], int n) {
        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        for (int interval = 1; interval < n; interval++) {
            int i = 0;
            int j = i + interval;

            while (i < n && j < n) {
                long x = (i + 2 <= j) ? dp[i + 2][j] : 0;
                long y = (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0;
                long z = (i <= j - 2) ? dp[i][j - 2] : 0;

                dp[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));

                i++;
                j++;
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {8, 15, 3, 7};

        System.out.println(countMaximum(arr, arr.length));
    }
}
