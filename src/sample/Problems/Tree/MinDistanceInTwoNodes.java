package sample.Problems.Tree;

/*
Given a binary tree and two node values your task is to find the minimum distance between them.

Example 1:

Input:
        1
      /  \
     2    3
a = 2, b = 3
Output: 2
Explanation: The tree formed is:
       1
     /   \
    2     3
We need the distance between 2 and 3.
Being at node 2, we need to take two
steps ahead in order to reach node 3.
The path followed will be:
2 -> 1 -> 3. Hence, the result is 2.

*/
public class MinDistanceInTwoNodes {
    Node LCA(Node root, int a, int b) {
        if (root == null) {
            return root;
        }

        if (root.data == a || root.data == b) {
            return root;
        }

        Node ls = LCA(root.left, a, b);
        Node rs = LCA(root.right, a, b);

        if (ls != null && rs != null) {
            return root;
        }
        if (ls != null) {
            return ls;
        } else {
            return rs;
        }
    }

    int height(Node root, int target, int level) {
        if (root == null) {
            return -1;
        }
        if (root.data == target) {
            return level;
        }
        int left = height(root.left, target, level + 1);
        if (left == -1) {
            return height(root.right, target, level + 1);
        }
        return left;
    }

    int findDist(Node root, int a, int b) {
        if (root == null) {
            return -1;
        }

        Node lca = LCA(root, a, b);

        int aHeight = height(root, a, 0);
        int bHeight = height(root, b, 0);
        int lcaHeight = height(root, lca.data, 0);

        return aHeight + bHeight + (2 * lcaHeight);
    }
}
