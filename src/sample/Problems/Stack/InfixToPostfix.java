package sample.Problems.Stack;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Given an infix expression in the form of a string str. Convert this infix expression to postfix expression.
 *
 * Input:
 * 2
 * a+b*(c^d-e)^(f+g*h)-i
 * A*(B+C)/D
 *
 * Output:
 * abcd^e-fgh*+^*+i-
 * ABC+*D/
 *
 * ALGORITHM
 * 1. if alphabet - add to result
 * 2. if ( - add to stack
 * 3. if ) - pop stack till corrosponding ( is popped
 * 4. else if +,-,*,/ -
 *     4.1 while top of stack is of higher or equal precedence, keep popping and adding to result
 *     4.2 at end add current character to stack
 * 5. when loop break add all the remaining characters of stack to results.
 */

class GFG
{
    private static Map<Character, Integer> precedence = new HashMap<Character, Integer>() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
        put('^', 3);
        put('(', Integer.MIN_VALUE);
        put(')', Integer.MIN_VALUE);
    }};

    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();

        while(t != 0) {
            String infix = scan.nextLine();
            String postfix = "";
            Stack<Character> stack = new Stack<>();
            for(Character c : infix.toCharArray()) {
                if(isAlphabet(c)) {
                    postfix += c;
                } else {
                    if(c == '(') {
                        stack.push(c);
                    } else {
                        if(c == ')') {
                            while (!stack.empty()) {
                                if(stack.peek() == '(') {
                                    stack.pop();
                                    break;
                                } else {
                                    postfix += stack.pop();
                                }
                            }
                        } else {
                            while (!stack.empty() &&
                                    precedence.get(stack.peek()) >= precedence.get(c)) {
                                postfix += stack.pop();
                            }
                            stack.push(c);
                        }
                    }
                }
            }
            while (!stack.empty()) {
                postfix += stack.pop();
            }

            System.out.println(postfix);
            t--;
        }
    }

    private static boolean isAlphabet(Character c) {
        return precedence.get(c) == null;
    }
}

