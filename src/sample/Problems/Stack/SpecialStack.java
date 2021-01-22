package sample.Problems.Stack;

import java.util.Stack;

/**
 Design a data-structure SpecialStack that supports all the stack operations
 like push(), pop(), isEmpty(), isFull() and an additional operation getMin()
 which should return minimum element from the SpecialStack.
 Your task is to complete all the functions, using stack data-Structure.
 */

class SpecialStack {
    public static Integer min = Integer.MAX_VALUE;

    public void push(int a, Stack<Integer> s) {
        if (isEmpty(s)) {
            min = a;
            s.push(a);
            return;
        }
        if (min > a) {
            s.push(2 * a - min);
            min = a;
        } else {
            s.push(a);
        }
    }

    public int pop(Stack<Integer> s) {
        if (isEmpty(s)) {
            return -1;
        } else {
            int val = s.pop();
            if (val < min) {
                int a = min;
                min = 2 * min - val;
                return a;
            }
            return val;
        }
    }

    public int min(Stack<Integer> s) {
        if (isEmpty(s)) {
            return -1;
        }
        return min;
    }

    public boolean isFull(Stack<Integer> s, int n) {
        if (isEmpty(s) && n > 0) {
            return false;
        }
        return s.size() == n;
    }

    public boolean isEmpty(Stack<Integer> s) {
        return s == null || s.size() == 0;
    }
}

