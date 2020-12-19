package sample.Problems.DynamicProgramming;

/*
Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.

Input: n = 4
Output: 7
Explantion:
Below are the four ways
 1 step + 1 step + 1 step + 1 step
 1 step + 2 step + 1 step
 2 step + 1 step + 1 step
 1 step + 1 step + 2 step
 2 step + 2 step
 3 step + 1 step
 1 step + 3 step
*/
public class WaysToCoverDistance {
    private static long[] arr = new long[100001];
    private static int size = 3;

    private static long countWays(int n) {
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        if (size < n) {

            for (int i = size + 1; i <= n; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2] + arr[i - 3]) % (1000000007);
            }

            size = n;
        }
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(countWays(4));
    }
}
