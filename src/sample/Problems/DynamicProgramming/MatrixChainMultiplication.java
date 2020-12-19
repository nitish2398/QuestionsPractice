package sample.Problems.DynamicProgramming;
import sample.Problems.Hard;

/*
Given a sequence of matrices, find the most efficient way to multiply these matrices together.

Input: p[] = {40, 20, 30, 10, 30}
Output: 26000
There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
Let the input 4 matrices be A, B, C and D.  The minimum number of
multiplications are obtained by putting parenthesis in following way
(A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

Input: p[] = {10, 20, 30, 40, 30}
Output: 30000
There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30.
Let the input 4 matrices be A, B, C and D.  The minimum number of
multiplications are obtained by putting parenthesis in following way
((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

Input: p[] = {10, 20, 30}
Output: 6000
There are only two matrices of dimensions 10x20 and 20x30. So there
is only one way to multiply the matrices, cost of which is 10*20*30


https://www.youtube.com/watch?v=vgLJZMUfnsU
 */

@Hard
public class MatrixChainMultiplication {
    private static int matrixChainMultiplication(int arr[], int n) {
        int[][] dp = new int[n - 1][n - 1];

        // compute for interval 1
        for (int i = 0; i < n - 2; i++) {
            dp[i][i + 1] = arr[i] * arr[i + 1] * arr[i + 2];
        }

        for (int interval = 2; interval < n - 1; interval++) {
            int i = 0;
            int j = i + interval;

            while (i < n - 1 && j < n - 1) {
                dp[i][j] = Math.min(dp[i][j - 1] + (arr[i] * arr[j] * arr[j + 1]),
                        dp[i + 1][j] + arr[i] * arr[j - 1] * arr[j + 1]);

                i++;
                j++;
            }
        }

        return dp[0][n - 2];
    }

    public static void main(String[] args) {
        int[] arr1 = {40, 20, 30, 10, 30};
        int[] arr2 = {10, 20, 30, 40, 30};
        int[] arr3 = {10, 20, 30};
        System.out.println(matrixChainMultiplication(arr1, arr1.length));
        System.out.println(matrixChainMultiplication(arr2, arr2.length));
        System.out.println(matrixChainMultiplication(arr3, arr3.length));
    }
}
