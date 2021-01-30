package sample.Problems.DynamicProgramming;

/*
Given a value N, find the number of ways to make change for N cents,
if we have infinite supply of each of S = { S1, S2, .. , SM } valued coins.

Input:
n = 4 , m = 3
S[] = {1,2,3}
Output: 4
Explanation: Four Possible ways are:
{1,1,1,1},{1,1,2},{2,2},{1,3}.

Input:
n = 10 , m = 4
S[] ={2,5,3,6}
Output: 5
Explanation: Five Possible ways are:
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5}
and {5,5}.
 */
public class CoinChangeProblem {

    public long count(int sum[], int len, int val)
    {
        long [][] dp = new long[val+1][len+1];


        for(int i = 0; i <= val; i++) {
            for(int j = 0; j <= len; j++) {
                if(i == 0) {
                    dp[i][j] = 1;
                } else if (i > 0 && j == 0) {
                    dp[i][j] = 0;
                } else {
                    if(i - sum[j-1] < 0) {
                        dp[i][j] = dp[i][j-1];
                    } else {
                        dp[i][j] = dp[i][j-1] + dp[i-sum[j-1]][j];
                    }
                }
            }
        }

        return dp[val][len];
    }

    public long countRec(int sum[], int len, int val) {
        if(val == 0) {
            return 1;
        }

        if(val > 0 && len <= 0) {
            return 0;
        }

        if(val < 0) {
            return 0;
        }

        return countRec(sum, len-1, val) + countRec(sum, len, val-sum[len-1]);
    }

    public static void main(String[] args) {
        int val = 10;
        int len = 4;
        int [] sum = {2,5,3,6};

        System.out.println(new CoinChangeProblem().count(sum, len, val));
        System.out.println(new CoinChangeProblem().countRec(sum, len, val));
    }
}
