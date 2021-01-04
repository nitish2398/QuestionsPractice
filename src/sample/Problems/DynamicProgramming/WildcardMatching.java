package sample.Problems.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

https://leetcode.com/problems/wildcard-matching/
https://www.youtube.com/watch?v=3ZDZ-N0EPV0
 */
public class WildcardMatching {

    private static Map<Integer, Map<Integer, Boolean>> isMatch;

    private static Boolean wildcardMatching(String input, String pattern, int inputIndex, int patternIndex) {

        if (isMatch.containsKey(inputIndex) && isMatch.get(inputIndex).containsKey(patternIndex)) {
            return isMatch.get(inputIndex).get(patternIndex);
        } else {
            // if both string matched exactly and we crossed the 0 index
            if (inputIndex == -1 && patternIndex == -1) {
                addResult(inputIndex, patternIndex, true);
            } else if (inputIndex == -1) {
                // if we have crossed zero index of input, then all the remaining characters in pattern should be * only
                addResult(inputIndex, patternIndex, pattern.charAt(patternIndex) == '*' &&
                        wildcardMatching(input, pattern, inputIndex, patternIndex - 1));
            } else if (patternIndex == -1) {
                // if patter is all covered and input is not then no solution exist
                addResult(inputIndex, patternIndex, false);
            } else if (input.charAt(inputIndex) == pattern.charAt(patternIndex) ||
                    pattern.charAt(patternIndex) == '?') {
                // for ? and exact match, check the next character of both
                addResult(inputIndex, patternIndex, wildcardMatching(input, pattern, inputIndex - 1, patternIndex - 1));
            } else if (pattern.charAt(patternIndex) == '*') {
                // for * we need to check both [i][j-1] and [i-1][j]
                addResult(inputIndex, patternIndex, patternIndex == 0 || (wildcardMatching(input, pattern, inputIndex, patternIndex - 1) ||
                        wildcardMatching(input, pattern, inputIndex - 1, patternIndex)));
            } else {
                // result is false otherwise
                addResult(inputIndex, patternIndex, false);
            }
        }
        return isMatch.get(inputIndex).get(patternIndex);
    }

    private static void addResult(int i, int j, boolean result) {
        if (isMatch.containsKey(i)) {
            if (!isMatch.get(i).containsKey(j) || !isMatch.get(i).get(j)) {
                isMatch.get(i).put(j, result);
            }
        } else {
            isMatch.put(i, new HashMap<Integer, Boolean>() {{
                put(j, result);
            }});
        }
    }

    private static String refactorInput(String x) {
        char[] input = x.toCharArray();

        String result = "";
        int i = -1;

        for (char c : input) {
            if (i > -1 && result.charAt(i) == '*' && c == '*') {
                continue;
            } else {
                result = result + c;
                i++;
            }
        }

        return result;
    }

    // Best solution to follow
    private static boolean isWildcardMatchBottomUp(String input, String pattern) {
        pattern = refactorInput(pattern);

        boolean[][] isWildcard = new boolean[input.length() + 1][pattern.length() + 1];

        for(int i = 0; i < pattern.length(); i++) {
            isWildcard[0][i] = false;
        }

        for(int i = 0; i < input.length(); i++) {
            isWildcard[i][0] = false;
        }

        isWildcard[0][0] = true;
        if(pattern.length() > 0 && pattern.charAt(0) == '*') {
            isWildcard[0][1] = true;
        }

        for(int i = 1; i <= input.length(); i++) {
            for(int j = 1; j <= pattern.length(); j++) {
                if(input.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
                    isWildcard[i][j] = isWildcard[i-1][j-1];
                } else if(pattern.charAt(j-1) == '*') {
                    isWildcard[i][j] = isWildcard[i-1][j] || isWildcard[i][j-1];
                } else {
                    isWildcard[i][j] = false;
                }
            }
        }

        return isWildcard[input.length()][pattern.length()];
    }

    public static void main(String[] args) {
        String input = "aaaababbbaaabaabbbbabaababaabbabbaabababbaaaaaaabba";
        String pattern = "*baaaba***********";

        isMatch = new HashMap<>();
        System.out.println(isWildcardMatchBottomUp(input, pattern));
    }
}


