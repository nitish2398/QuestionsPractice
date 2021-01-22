package sample.Problems.Stack;

import java.util.Scanner;
import java.util.Stack;

/*Complete the function given below*/

/**
 * Max rectangle
 * Given a binary matrix. Find the maximum area of a rectangle formed only of 1s in the given matrix.
 *
 * matrix will look like
 * 0 1 1 0
 * 1 1 1 1
 * 1 1 1 1
 * 1 1 0 0
 * the max size rectangle is
 * 1 1 1 1
 * 1 1 1 1
 * and area is 4 *2 = 8.
 *
 * 1. we will use the concept of max area in a histogram
 * 2. consider first row as histogram and calculate max area
 * 3. add second row and calculate/compare the area
 * 4. repeat for all of the rows to get the final solution
 * */

// Extension of largest Area under Histogram

class GfG {
    public int maxArea(int M[][], int m, int n) {
        int[] arr = new int[m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[j] = M[i][j] == 1 ? arr[j] + M[i][j] : 0;
            }

            res = Math.max(res, findMaxAreaUnderHistogram(arr));
        }
        return res;
    }

    private int findMaxAreaUnderHistogram(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        stack.push(i);
        i++;

        int maxArea = 0;
        while (!stack.empty()) {
            while (!stack.isEmpty() && (i == n || arr[i] < arr[stack.peek()])) {
                int index = stack.pop();
                int current = arr[index] * (stack.empty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(maxArea, current);
            }
            if (i < n) {
                stack.push(i);
            }
            i++;
        }

        return maxArea;
    }
}

class Solution {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int arr[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        System.out.println(new GfG().maxArea(arr, m, n));
    }
}


