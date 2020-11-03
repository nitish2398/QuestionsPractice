package sample.Problems.DynamicProgramming;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Knapsack01 {
    static int knapSack(int w, int wt[], int val[], int n) {
        int dp[][] = new int[w + 1][n + 1];

        for (int i = 0; i <= w; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (wt[j - 1] > i) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = Math.max(val[j - 1] + dp[i - wt[j - 1]][j - 1], dp[i][j - 1]);
                    }
                }
            }
        }

        return dp[w][n];
    }
}
class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        //reading total testcases
        int t = Integer.parseInt(read.readLine());

        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());

            int val[] = new int[n];
            int wt[] = new int[n];

            String st[] = read.readLine().trim().split("\\s+");

            //inserting the values
            for(int i = 0; i < n; i++)
                val[i] = Integer.parseInt(st[i]);

            String s[] = read.readLine().trim().split("\\s+");

            //inserting the weigths
            for(int i = 0; i < n; i++)
                wt[i] = Integer.parseInt(s[i]);

            //calling method knapSack() of class Knapsack
            System.out.println(new Knapsack01().knapSack(w, wt, val, n));
        }
    }
}
