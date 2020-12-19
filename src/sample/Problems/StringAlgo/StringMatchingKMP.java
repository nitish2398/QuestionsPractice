package sample.Problems.StringAlgo;

/*
https://www.youtube.com/watch?v=GTJr8OvyEVQ
 */
public class StringMatchingKMP {
    public static void main(String[] arg) {
        String text = "abcxabcdabcdabcy";
        String pattern = "abcdabcy";

        System.out.println("abc".compareTo("0"));

        isPatternExistInText(text.toCharArray(), pattern.toCharArray());
    }

    private static void isPatternExistInText(char[] text, char[] pattern) {
        int[] lps = toTemporaryArray(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        if (j == pattern.length) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    private static int[] toTemporaryArray(char[] pattern) {
        int[] lps = new int[pattern.length];

        int j = 0;
        int i = 1;

        while (i < pattern.length) {
            if (pattern[j] == pattern[i]) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
