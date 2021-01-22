package sample.Problems.DynamicProgramming;

import sample.Problems.Hard;

import java.util.Scanner;


/*
Given a length n, count the number of strings of length n that can be made using ‘a’, ‘b’ and ‘c’ with at-most one ‘b’ and two ‘c’s allowed.

Input:
2
1
3

Output:
3
19

Explanation:
Test Case 1: N = 1
Possible strings are: "a", "b" and "c"

Test Case 2: N = 3
Number of strings with 3 occurrances of a: 1
2-a and 1-b: 3
2-a and 1-c: 3
1-a, 1-b and 1-c: 6
1-a and 2-c: 3
1-b and 2-c: 3
Hence, total number of strings of length 3 = 1 + 3 + 3 + 6 + 3 + 3 = 19

 */
@Hard
public class NoOfStringsUsingABC {

    private static int noOfStringsUsingABC(int n, int bCount, int cCount, int[][][] dp) {
        //base cases
        if (bCount < 0 || cCount < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (bCount == 0 && cCount == 0) {
            return 1;
        }

        if (dp[n][bCount][cCount] == -1) {
            dp[n][bCount][cCount] = noOfStringsUsingABC(n - 1, bCount, cCount, dp) +
                    noOfStringsUsingABC(n - 1, bCount - 1, cCount, dp) +
                    noOfStringsUsingABC(n - 1, bCount, cCount - 1, dp);
        }

        return dp[n][bCount][cCount];
    }

    private static int noOfStringsUsingABC(int n) {
        int bCount = 1;
        int cCount = 2;

        int[][][] dp = new int[n + 1][bCount + 1][cCount + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= bCount; j++) {
                for (int k = 0; k <= cCount; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return noOfStringsUsingABC(n, bCount, cCount, dp);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();

            System.out.println(noOfStringsUsingABC(n));

            t--;
        }
    }
}
