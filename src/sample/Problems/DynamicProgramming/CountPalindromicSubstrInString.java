package sample.Problems.DynamicProgramming;

import java.util.Scanner;

/**
 * Given a string, the task is to count all palindromic sub-strings present in it.
 *
 * Example:
 *
 * Input
 *
 * 2
 * 5
 * abaab
 * 7
 * abbaeae
 *
 * Output
 *
 * 3
 * 4
 */

public class CountPalindromicSubstrInString {
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        while(t != 0) {
            int len = scan.nextInt();
            scan.nextLine();
            String s = scan.nextLine();

            boolean [][] arr = new boolean [len][len];

            // set all the length 1 substr as pallindromic
            for(int i = 0; i < len; i++) {
                arr[i][i] = true;
            }

            // set all the length 2 substr as pallindromic
            for(int i = 0; i < len-1; i++) {
                if(s.charAt(i) == s.charAt(i+1)) {
                    arr[i][i+1] = true;
                }
            }

            // check for all the remaining in diagonal array (watch video for reference)
            for(int i = len-1; i >= 0; i--) {
                for(int j = i+1; j<len; j++) {
                    if(s.charAt(i) == s.charAt(j) && arr[i+1][j-1]) {
                        arr[i][j] = true;
                    }
                }
            }

            int PallindromicSubStr = 0;

            for(int i = 0; i < len; i++) {
                for(int j = 0; j < len; j++) {
                    if(arr[i][j]) {
                        PallindromicSubStr++;
                    }
                }
            }

            System.out.println(PallindromicSubStr - len);

            t--;
        }
    }

}
