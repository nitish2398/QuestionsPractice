package sample.Problems.DynamicProgramming;

/*
Given two strings str1 and str2,
the task is to find the length of the shortest string that has both str1 and str2 as subsequences.

Examples :

Input:   str1 = "geek",  str2 = "eke"
Output: 5
Explanation:
String "geeke" has both string "geek"
and "eke" as subsequences.

Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
Output:  9
Explanation:
String "AGXGTXAYB" has both string
"AGGTAB" and "GXTXAYB" as subsequences
*/


// hook crux -
// shortestCommonSequence = (length of string1 + length of string2) - longestCommonSubSequence

public class ShortestCommonSequence {
    private static int shortestCommonSequence(String s1, String s2) {
        return (s1.length() + s2.length()) - LongestCommonSequence.lcs(s1, s2);
    }
}
