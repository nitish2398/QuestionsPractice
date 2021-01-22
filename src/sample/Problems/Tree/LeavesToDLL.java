package sample.Problems.Tree;

import sample.Problems.Hard;

/**
 * Leaves to DLL
 * Given a Binary Tree of size N, extract all its leaf nodes
 * to form a Doubly Link List strating from the left most leaf.
 * Modify the original tree to make the DLL thus removing the leaf
 * nodes from the tree. Consider the left and right pointers of
 * the tree to be the previous and next pointer of the DLL respectively.
 */

@Hard
class BinaryTreeToDLL {
    Node head = null;
    Node prev = null;
    Node current = null;
    public Node convertToDLL(Node root)
    {
        getDLL(root);
        return head;
    }
    public Node getDLL(Node root) {
        if(root == null || (root.left == null && root.right == null))  {
            return root;
        }

        if(root.left != null && root.left.left == null && root.left.right == null) {
            if(head == null) {
                head = root.left;
                prev = root.left;
            } else {
                prev.right = root.left;
                root.left.left = prev;
                prev = root.left;
            }
            root.left = null;
        }

        root.left = getDLL(root.left);

        if(root.right != null && root.right.left == null && root.right.right == null) {
            if(head == null) {
                head = root.right;
                prev = root.right;
            } else {
                prev.right = root.right;
                root.right.left = prev;
                prev = root.right;
            }
            root.right = null;
        }

        root.right = getDLL(root.right);

        return root;
    }
}

