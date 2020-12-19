package sample.Problems.DynamicProgramming;

public class LongestIncreasingSubsequence {
    private static int longestIncreasingSubsequence(int size, int [] arr){
        int [] dp = new int[size];

        dp[0] = 1;
        int result = 1;

        for(int i = 1; i < size; i++) {
            dp[i] = 1;

            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int [] arr = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};

        int [] arr2 = {59,93,22,84,42,61,92,7,38};

        System.out.println(longestIncreasingSubsequence(arr2.length, arr2));
    }
}
