package sample.Problems.DynamicProgramming;

import sample.Problems.Hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Word break problem
 * <p>
 * Given an input string and a dictionary of words,
 * find out if the input string can be segmented into a space-separated
 * sequence of dictionary words. See following examples for more details.
 * <p>
 * Consider the following dictionary
 * { i, like, sam, sung, samsung, mobile, ice,
 * cream, icecream, man, go, mango}
 * <p>
 * Input:  ilike
 * Output: Yes
 * <p>
 * <p>
 * v.v.v Imp and unique
 * https://www.youtube.com/watch?v=WepWFGxiwRs - must watch
 * <p>
 * solve - https://leetcode.com/problems/word-break-ii/
 */

@Hard
public class WordBreakProblem {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String[] buffer = scan.nextLine().split(" ");

            Map<String, Boolean> dictionary = new HashMap<>();
            for (String s : buffer) {
                dictionary.put(s, true);
            }
            String query = scan.nextLine();

            boolean dp[][] = new boolean[query.length()][query.length()];

            for (int i = 0; i < query.length(); i++) {
                int start = 0;
                int end = start + i;

                while (end < query.length()) {
                    if (dictionary.get(query.substring(start, end + 1)) != null) {
                        dp[start][end] = true;
                    } else {
                        for (int index = start; index < end; index++) {
                            if (dp[start][index] && dp[index + 1][end]) {
                                dp[start][end] = true;
                            }
                        }
                    }
                    start++;
                    end++;
                }
            }

            int res = dp[0][query.length() - 1] ? 1 : 0;
            System.out.println(res);

            t--;
        }
    }
}
