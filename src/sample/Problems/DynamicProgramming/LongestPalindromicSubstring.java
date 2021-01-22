package sample.Problems.DynamicProgramming;

import java.util.*;
import java.lang.*;
import java.io.*;

public class LongestPalindromicSubstring {
    // DP – Longest Palindromic substring in a string

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        scan.nextLine();

        while (t != 0) {
            String s = scan.nextLine();
            int len = s.length();

            boolean[][] arr = new boolean[len][len];

            // set all the length 1 substr as pallindromic
            for (int i = 0; i < len; i++) {
                arr[i][i] = true;
            }

            // set all the length 2 substr as pallindromic
            for (int i = 0; i < len - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    arr[i][i + 1] = true;
                }
            }

            // check for all the remaining in diagonal array (watch video for reference)
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i + 1; j < len; j++) {
                    if (s.charAt(i) == s.charAt(j) && arr[i + 1][j - 1]) {
                        arr[i][j] = true;
                    }
                }
            }

            int maxI = -12;
            int maxJ = -12576;

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (maxJ - maxI + 1 < j - i + 1 && arr[i][j]) {
                        maxI = i;
                        maxJ = j;
                    }
                }
            }

            System.out.println(s.substring(maxI, maxJ + 1));

            t--;
        }
    }



}
