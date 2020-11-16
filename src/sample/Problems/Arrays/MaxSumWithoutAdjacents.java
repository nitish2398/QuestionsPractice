package sample.Problems.Arrays;

/**
 * Given an array Arr of size N containing positive integers.
 * Find the maximum sum of a subsequence such that no two numbers in the sequence should be adjacent in the array.
 * <p>
 * Input:
 * N = 4
 * Arr[] = {3, 2, 7, 10}
 * Output: 13
 * Explanation: 3 and 10 forms a non
 * continuous  subsequence with maximum
 * sum.
 */
public class MaxSumWithoutAdjacents {
    public static void main(String arg[]) {
        int[] arr = {5, 5, 10, 100, 10, 5};
        System.out.println(new Solution().findMaxSum(arr, arr.length));

        int[] arr2 = {3, 2, 7, 10};
        System.out.println(new Solution().findMaxSum(arr2, arr2.length));
    }
}

class Solution {
    int findMaxSum(int arr[], int n) {
        if (n <= 0) {
            return -1;
        }

        int include = arr[0];
        int exclude = 0;
        int currentMax = 0;

        for (int i = 1; i < n; i++) {
            currentMax = Math.max(include, exclude);

            include = exclude + arr[i];
            exclude = currentMax;
        }

        return Math.max(include, exclude);
    }
}
