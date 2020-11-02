package sample.Problems.Arrays;

import java.util.Scanner;

public class LargestSumSubArrayInCirularArrayUsingKadaneAlgo {
}

/**
 * Given an array arr[] of N integers arranged in a circular fashion.
 * Your task is to find the maximum contiguous subarray sum.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * N = 7
 * arr[] = {8,-8,9,-9,10,-11,12}
 * Output: 22
 * Explanation:  Starting from the last
 * element of the array, i.e, 12, and
 * moving in a circular fashion, we
 * have max subarray as 12, 8, -8, 9,
 * -9, 10, which gives maximum sum
 * as 22.
 * <p>
 * Input:
 * N = 8
 * arr[] = {10,-3,-4,7,6,5,-4,-1}
 * Output: 23
 * Explanation: Sum of the circular
 * subarray with maximum sum is 23
 */
class Kadane {

    static int circularSubarraySum(int a[], int n) {
        int sum = 0;
        boolean isAllNegative = true;
        int max = Integer.MIN_VALUE;
        for (int i : a) {
            if (i >= 0) {
                isAllNegative = false;
            }
            max = Math.max(max, i);
            sum += i;
        }

        return isAllNegative ? max : Math.max(kadaneToFindMax(a),
                sum - kadaneToFindMin(a));
    }

    private static int kadaneToFindMax(int arr[]) {
        int maxSum = arr[0];
        int maxTillNow = arr[0];

        for (int i = 1; i < arr.length; i++) {

            maxTillNow = Math.max(maxTillNow + arr[i], arr[i]);
            maxSum = Math.max(maxSum, maxTillNow);

        }

        return maxSum;
    }

    private static int kadaneToFindMin(int arr[]) {
        int minSum = arr[0];
        int minTillNow = arr[0];

        for (int i = 1; i < arr.length; i++) {

            minTillNow = Math.min(minTillNow + arr[i], arr[i]);
            minSum = Math.min(minSum, minTillNow);

        }

        return minSum;
    }

    public static void main(String[] a) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(circularSubarraySum(arr, n));
    }
}
