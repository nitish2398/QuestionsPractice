package sample.Problems.Arrays;

import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Given a positive integer N, print its corresponding column title as it would appear in an Excel sheet.
 * For N =1 we have column A, for 27 we have AA and so on.
 *
 * Note: The alphabets are all in uppercase.
 *
 * Input:
 * 1
 * 51
 * Output:
 * AY
 *
 */

class GFG {
    private static String result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            result = "";
            long n = scan.nextLong();
            getResultExcelValue(n);
            System.out.println(result);
            t--;
        }
    }

    private static void getResultExcelValue(long n) {
        while (n > 0) {
            if (n <= 26) {
                result = getCharacterFromInteger(n) + result;
                break;
            } else {
                long division = n / 26;
                long multipication = division * 26;
                if (multipication == n) {
                    result = 'Z' + result;
                    n--;
                } else {
                    result = getCharacterFromInteger(n - multipication) + result;
                }
            }
            n = n / 26;
        }
    }

    private static char getCharacterFromInteger(long x) {
        x--;
        return (char) ('A' + x);
    }
}
