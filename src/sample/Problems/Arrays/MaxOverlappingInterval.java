package sample.Problems.Arrays;

import java.security.KeyPair;
import java.util.*;
import java.lang.*;
import java.io.*;


/**
 * https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
 * <p>
 * Consider a big party where a log register for guest’s entry and exit times is maintained.
 * Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.
 */

public class MaxOverlappingInterval {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            int entry[] = new int[n];
            int leave[] = new int[n];

            for (int i = 0; i < n; i++) {
                entry[i] = scan.nextInt();
            }
            for (int i = 0; i < n; i++) {
                leave[i] = scan.nextInt();
            }

            Arrays.sort(entry);
            Arrays.sort(leave);

            int maxNum = 0;
            int maxTime = 0;
            int result = 0;

            int i = 0;
            int j = 0;
            while (i < n && j < n) {
                if (entry[i] <= leave[j]) {
                    maxNum++;
                    if (maxNum >= result) {
                        result = maxNum;
                        maxTime = entry[i];
                    }
                    i++;
                } else {
                    maxNum--;
                    j++;
                }
            }

            while (i < n) {
                maxNum++;
                if (maxNum >= result) {
                    result = maxNum;
                    maxTime = entry[i];
                }
                i++;
            }

            System.out.println(result + " " + maxTime);
            t--;
        }
    }
}
