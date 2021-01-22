package sample.Problems.Arrays;

import sample.Problems.Hard;

import java.util.*;
import java.lang.*;

/**
 * Given a 2D array, find the maximum sum subarray in it.
 * For example, in the following 2D array, the maximum sum subarray
 * https://practice.geeksforgeeks.org/problems/maximum-sum-rectangle/0/
 * https://www.youtube.com/watch?v=yCQN096CwWM
 */

/**
 * Algorithm:
 * 1. Use kadane Algo to find max sum in a array
 * 2. pick the xth column
 * 3. add the sum to the temp array
 * 4. mind the max sum in temp array using kadane algo
 * 5. repeat the process for all the columns
 */

class MaximumSumRectangleInGrid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            int arr[][] = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }

            int result = Integer.MIN_VALUE;

            for (int x = 0; x < m; x++) {
                int temp[] = new int[n];
                for (int i = x; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[j] = temp[j] + arr[j][i];
                    }
                    result = Math.max(result, kadane(temp));
                }
            }

            System.out.println(result);

            t--;
        }
    }

    private static int kadane(int[] arr) {
        int max_sum = 0;
        int max_sum_till_now = 0;

        for (int i = 0; i < arr.length; i++) {
            max_sum_till_now = max_sum_till_now + arr[i];

            if (max_sum_till_now < 0) {
                max_sum_till_now = 0;
            }

            max_sum = Math.max(max_sum, max_sum_till_now);
        }

        return max_sum;
    }
}
