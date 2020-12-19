package sample.Problems.DynamicProgramming;
/*
Given two strings str1 and str2 and below operations that can performed on str1.
Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.

Insert
Remove
Replace

All of the above operations are of equal cost.
 */
public class EditDistance {
    private static int editDistance(String s, String t)
    {
        int [][] dp = new int[s.length()+1][t.length()+1];

        for(int i = 0; i <= s.length(); i++) {
            for(int j = 0; j <= t.length(); j++) {
                if(i == 0) {
                    dp[i][j] = j;
                    continue;
                }

                if(j == 0) {
                    dp[i][j] = i;
                    continue;
                }

                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        String s1 = "geek";
        String s2 = "gesek";

        System.out.println(editDistance(s1, s2));
    }
}
