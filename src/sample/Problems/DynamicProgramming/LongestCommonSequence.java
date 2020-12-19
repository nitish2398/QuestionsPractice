package sample.Problems.DynamicProgramming;
/*
Given two sequences, find the length of longest subsequence present in both of them. Both the strings are of uppercase.

Example 1:

Input:
A = 6, B = 6
str1 = ABCDGH
str2 = AEDFHR
Output: 3
Explanation: LCS for input Sequences
“ABCDGH” and “AEDFHR” is “ADH” of
length 3.
Example 1:

Input:
A = 3, B = 2
str1 = ABC
str2 = AC
Output: 2
Explanation: LCS of "ABC" and "AC" is
"AC" of length 2.

https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1#
 */
public class LongestCommonSequence {
    static int lcs(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? (1 + dp[i - 1][j - 1]) : 0;

                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], dp[i - 1][j]));
            }
        }

        return dp[s1.length()][s2.length()];
    }

    private static int lcsRecursive(String s1, String s2, int max1, int max2) {
        if(max1 == 0 || max2 == 0) {
            return 0;
        }

        if(s1.charAt(max1-1) == s2.charAt(max2-1)) {
            return Math.max(1 + lcsRecursive(s1, s2, max1-1, max2-1),
                    Math.max(lcsRecursive(s1, s2, max1-1, max2),
                    lcsRecursive(s1, s2, max1, max2-1)));
        }

        return Math.max(lcsRecursive(s1, s2, max1-1, max2),
                lcsRecursive(s1, s2, max1, max2-1));
    }

    public static void main(String[] args) {
        String s1 = "ABC";
        String s2 = "AC";

        System.out.println(lcs(s1, s2));
    }
}
