package sample.Problems.DynamicProgramming;

/*
Given two strings S and T of length n and m respectively.
find count of distinct occurrences of T in S as a sub-sequence.

Input:
S = "banana" , T = "ban"
Output: 3
Explanation: There are 3 sub-sequences:
[ban], [ba n], [b an].

Input:
S = "geeksforgeeks" , T = "ge"
Output: 6
Explanation: There are 6 sub-sequences:
[ge], [ ge], [g e], [g e] [g e] and [ g e].

* */
public class DistinctOccurences {

    // dp
    int  subsequenceCount(String s, String t)
    {
        int m = s.length();
        int n = t.length();

        int dp[][] = new int[m+1][n+1];

        // order specific loops dp[0][0] must be consider as 1
        for(int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for(int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[m][n];
    }

    // recursive
    int getDistinctOccurrences(String s, String t, int i, int j) {
        if(j < 0) {
            return 1;
        }

        if(i < 0) {
            return 0;
        }

        if(s.charAt(i) == t.charAt(j)) {
            return getDistinctOccurrences(s, t, i-1, j-1) + getDistinctOccurrences(s, t, i-1, j);

        }

        return getDistinctOccurrences(s, t, i-1, j);
    }
}

class Solution {
    public  static void main(String [] arg) {
        System.out.println(new DistinctOccurences().subsequenceCount("geeksforgeeks", "ge"));
    }
}
