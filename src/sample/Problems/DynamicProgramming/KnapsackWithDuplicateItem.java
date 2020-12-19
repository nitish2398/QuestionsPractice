package sample.Problems.DynamicProgramming;

public class KnapsackWithDuplicateItem {
}
/*
* Given a set of N items,
* each with a weight and a value, and a weight limit W.
* Find the maximum value of a collection containing any of the N items
* any number of times so that the total weight is less than or equal to W.

Example 1:
Input: N = 2, W = 3
val[] = {1, 1}
wt[] = {2, 1}
Output: 3
Explaination: Pick the 2nd element thrice.

Example 2:
Input: N = 4, W = 8
val[] = {1, 4, 5, 7}
wt[] = {1, 3, 4, 5}
Output: 11
Explaination: The optimal choice is to
pick the 2nd and 4th element.
*
*/
class Solution1{
    static int knapSack(int n, int w, int val[], int wt[])
    {
        int dp[][] = new int[w + 1][n + 1];

        for (int i = 0; i <= w; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (wt[j - 1] > i) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = Math.max(val[j - 1] + dp[i - wt[j - 1]][j],
                                Math.max(val[j - 1] + dp[i - wt[j - 1]][j-1], dp[i][j - 1]));
                    }
                }
            }
        }

        return dp[w][n];
    }
}
