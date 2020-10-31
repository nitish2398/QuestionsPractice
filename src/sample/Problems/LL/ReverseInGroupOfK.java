package sample.Problems.LL;

/**
 * Reverse a Linked List in groups of given size.
 *
 * Input:
 * LinkedList: 1->2->2->4->5->6->7->8
 * K = 4
 * Output: 4 2 2 1 8 7 6 5
 *
 */

class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}

public class ReverseInGroupOfK {
    public static Node reverse(Node node, int k)
    {
        int count = 0;
        Node prev = null;
        Node next = null;
        Node current = node;

        while(current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        if(next != null) {
            node.next = reverse(next, k);
        }

        return prev;
    }
}
