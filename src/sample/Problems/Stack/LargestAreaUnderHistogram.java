package sample.Problems.Stack;

import java.util.*;
import java.lang.*;

/**
 * Find the largest rectangular area possible in a given histogram where the largest rectangle
 * can be made of a number of contiguous bars.
 * For simplicity, assume that all bars have same width and the width is 1 unit.
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * https://www.youtube.com/watch?v=ZmnqCZp9bBs
 * <p>
 * 1.	If the element is greater or equal to top of stack then push into stack.
 * 2.	If Smaller then keep popping out from the stack until input[stackTop] is less or equal to currentElemment
 * 3.	Meanwhile perform -> {
 * 4.	     Index = pop from stack
 * 5.	     CurrentArea = input[Index] * (isStackEmpty()) ? i : (i – stack.currentop() – 1);
 * 6.	     Compare currentArea with maxArea
 * }
 */

public class LargestAreaUnderHistogram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            Stack<Integer> stack = new Stack<>();

            int n = scan.nextInt();
            scan.nextLine();
            String[] arr = scan.nextLine().split(" ");

            int current;
            int max = -1;
            int i = 1;
            stack.push(0);

            while (!stack.empty()) {
                if (i < n) {
                    while (!stack.empty() &&
                            Integer.parseInt(arr[i]) < Integer.parseInt(arr[stack.peek()])) {
                        int index = stack.pop();
                        current = Integer.parseInt(arr[index]) * (stack.empty() ? i : (i - stack.peek() - 1));

                        if (current > max) {
                            max = current;
                        }
                    }
                    stack.push(i);
                    i++;
                } else {
                    int index = stack.pop();
                    current = Integer.parseInt(arr[index]) * (stack.empty() ? i : (i - stack.peek() - 1));

                    if (current > max) {
                        max = current;
                    }
                }
            }

            System.out.println(max);
            t--;
        }
    }
}

