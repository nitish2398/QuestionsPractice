package sample.Problems.LL;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Given a singly linked list of size N, and an integer K.
 * You need to swap the Kth node from beginning and Kth node from the end in the linked list.
 * Note: You need to swap the nodes through the links and not changing the content of the nodes.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * N = 4,  K = 1
 * value[] = {1,2,3,4}
 * Output: 1
 * Explanation: Here K = 1, hence after
 * swapping the 1st node from the beginning
 * and end thenew list will be 4 2 3 1.
 */

class SwapKthNodeFromStartAndEnd {
    Node swapkthnode(Node head, int num, int k) {
        // Count nodes in linked list
        int n = countNodes(head);

        if (n < k)
            return head;

        // If x (kth node from start) and
        // y(kth node from end) are same
        if (2 * k - 1 == n)
            return head;

        // Find the kth node from beginning of linked list.
        // We also find previous of kth node because we need
        // to update next pointer of the previous.
        Node x = head;
        Node x_prev = null;
        for (int i = 1; i < k; i++) {
            x_prev = x;
            x = x.next;
        }

        // Similarly, find the kth node from end and its
        // previous. kth node from end is (n-k+1)th node
        // from beginning
        Node y = head;
        Node y_prev = null;
        for (int i = 1; i < n - k + 1; i++) {
            y_prev = y;
            y = y.next;
        }

        // If x_prev exists, then new next of it will be y.
        // Consider the case when y->next is x, in this case,
        // x_prev and y are same. So the statement
        // "x_prev->next = y" creates a self loop. This self
        // loop will be broken when we change y->next.
        if (x_prev != null)
            x_prev.next = y;

        // Same thing applies to y_prev
        if (y_prev != null)
            y_prev.next = x;

        // Swap next pointers of x and y. These statements
        // also break self loop if x->next is y or y->next
        // is x
        Node temp = x.next;
        x.next = y.next;
        y.next = temp;

        // Change head pointers when k is 1 or n
        if (k == 1)
            head = y;

        if (k == n)
            head = x;

        return head;
    }


    private int countNodes(Node head) {
        Node temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }
}

