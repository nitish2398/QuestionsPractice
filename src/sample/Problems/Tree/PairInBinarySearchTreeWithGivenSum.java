package sample.Problems.Tree;

import java.util.Stack;

public class PairInBinarySearchTreeWithGivenSum {

    /**
     * Given a Binary Search Tree and a target sum.
     * Check whether there's a pair of Nodes in the BST with value summing up to the target sum.
     * <p>
     * We can do in O(n) time and O(logn) space
     * travers inorder from start and reverse together
     * believing, two values can never be equal in bst
     */
    public int isPairPresent(Node root, int target) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node temp1 = root;
        Node temp2 = root;
        while (true) {
            while (temp1 != null) {
                stack1.push(temp1);
                temp1 = temp1.left;
            }

            while (temp2 != null) {
                stack2.push(temp2);
                temp2 = temp2.right;
            }

            if (stack1.empty() || stack2.empty()) {
                break;
            }
            temp1 = stack1.peek();
            temp2 = stack2.peek();

            if (temp1.data + temp2.data == target) {
                if (temp1.data == temp2.data) {
                    return 0;
                }
                return 1;
            }

            if (temp1.data + temp2.data < target) {
                stack1.pop();
                temp1 = temp1.right;
                temp2 = null;
            } else {
                stack2.pop();
                temp2 = temp2.left;
                temp1 = null;
            }
        }
        return 0;
    }
}

