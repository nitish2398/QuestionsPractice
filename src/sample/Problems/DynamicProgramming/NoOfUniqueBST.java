package sample.Problems.DynamicProgramming;

import java.util.Scanner;

public class NoOfUniqueBST {
    /*
     * No of unique BST are equal to catalina number = (2n!)/((n+1)! * n!)
     *
     * for 1 result be -> 1
     * for 2 result be -> 2 (1->2) (2->1)
     *
     * for n = 3, result would be 5
     * for n = 4, result would be res[0]*res[3] + res[1]*res[2] + res[2]*res[1] + res[3]*res[0] = 14
     *
     * and so on....
     *
     * https://www.youtube.com/watch?v=YDf982Lb84o
     *
     *
     * */


    /*
    *  Testcase1:
       for N = 2, there are 2 unique BSTs
         1               2
          \            /
           2         1

       Testcase2:
       for N = 3, there are 5 possible BSTs
       1              3        3         2      1
        \           /         /          / \      \
           3        2         1        1    3      2
        /       /              \                   \
        2      1               2                    3
    * */

    private static int[] arr = new int[1001];

    // Return the total number of BSTs possible with keys [1....N] inclusive.
    private static int numTrees(int n) {
        if (arr[n] != 0) {
            return arr[n];
        }

        // way to calculate catalina number
        int i = n - 1, j = 0;
        while (j != n && i != -1) {
            arr[n] = arr[n] + (arr[i] * arr[j]);
            i--;
            j++;
        }

        return arr[n];
    }


    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        arr[0] = 1;
        arr[1] = 1;

        while (t != 0) {
            int n = scan.nextInt();

            for (int i = 2; i <= n; i++) {
                if (arr[i] == 0) {
                    arr[i] = numTrees(i);
                }
            }

            System.out.println(arr[n]);
            t--;
        }
    }
}
