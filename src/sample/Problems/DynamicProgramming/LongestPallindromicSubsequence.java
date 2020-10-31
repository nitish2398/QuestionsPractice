package sample.Problems.DynamicProgramming;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Given a string of S as input.
 * Your task is to write a program to remove or delete minimum number of characters
 * from the string so that the resultant string is palindrome.
 *
 * We will use DP - find the length of longestPallindromicSubseq.
 * once we have the longest palindrome length, then sizeOfString - length will be our answer
 *
 * refer - https://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome/
 * https://www.youtube.com/watch?v=_nCsPn7_OgI
 */
class GFG
{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();

        while(t != 0) {
            String s = scan.nextLine();
            int n = s.length();

            int arr[][] = new int[n][n];

            for(int i = 0; i < s.length(); i++) {
                arr[i][i] = 1;
            }

            for(int index = 2; index <= n; index++) {
                for(int i = 0; i < n-index+1; i++) {
                    int j = i + index - 1;

                    if(s.charAt(i) == s.charAt(j)) {
                        arr[i][j] = arr[i+1][j-1] + 2;
                    } else {
                        arr[i][j] = Math.max(arr[i+1][j], arr[i][j-1]);
                    }
                }
            }

            System.out.println(n-arr[0][n-1]);

            t--;
        }
    }
}
