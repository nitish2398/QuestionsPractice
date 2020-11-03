package sample.Problems.Arrays;

// we need to do it on O(n) time and O(1) space

/*
* You are given an array arr[] of N integers including 0.
* The task is to find the smallest positive number missing from the array.
*
* Example 1:

Input:
N = 5
arr[] = {1,2,3,4,5}
Output: 6
Explanation: Smallest positive missing
number is 6.
Example 2:

Input:
N = 5
arr[] = {0,-10,1,3,-20}
Output: 2
Explanation: Smallest positive missing
number is 2.
*
* */

import java.util.Scanner;

public class MissingSmallestPositiveNumberInArray {
    static int missingNumber(int arr[], int size) {

        // replace the positive numbers less than or equal to n, with number-1 index
        // reiterate the array to check missing value
        int i = 0;
        while (i < arr.length) {
            if (arr[i] > 0 &&
                    arr[i] <= arr.length &&
                    arr[i] != arr[arr[i] - 1]) {
                swap(i, arr[i] - 1, arr);
            } else {
                i++;
            }
        }

        for (int p = 0; p < arr.length; p++) {
            if (arr[p] != p + 1) {
                return p + 1;
            }
        }

        return arr.length + 1;
    }

    private static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String arg[]) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(missingNumber(arr, n));
    }
}
