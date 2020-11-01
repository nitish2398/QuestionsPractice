package sample.Problems.Stack;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * The stock span problem is a financial problem
 * where we have a series of n daily price quotes
 * for a stock and we need to calculate the span of stock’s price for all n days.
 * <p>
 * The span Si of the stock’s price on a given day i is defined
 * as the maximum number of consecutive days just before the given day,
 * for which the price of the stock on the current day is
 * less than or equal to its price on the given day.
 * <p>
 * Example:
 * Input:
 * 2
 * 7
 * 100 80 60 70 60 75 85
 * 6
 * 10 4 5 90 120 80
 * <p>
 * Output:
 * 1 1 1 2 1 4 6
 * 1 1 2 4 5 1
 */

public class StockSpanProblem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String[] s = scan.nextLine().split(" ");

            Stack<Integer> stack = new Stack<>();
            int[] arr = new int[n];
            arr[0] = 1;

            stack.push(0);
            for (int i = 1; i < n; i++) {

                // pop all the smaller elements
                while (!stack.empty() && Integer.valueOf(s[i]) >= Integer.valueOf(s[stack.peek()])) {
                    stack.pop();
                }

                arr[i] = (stack.empty()) ? i + 1 : (i - stack.peek());

                stack.push(i);
            }

            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
            t--;
        }
    }

    /**
     * Time Complexity: O(n).
     * It seems more than O(n) at first look.
     * If we take a closer look, we can observe that every element of the array is
     * added and removed from the stack at most once.
     * So there are total 2n operations at most.
     * Assuming that a stack operation takes O(1) time, we can say that the time complexity is O(n).
     */
}
