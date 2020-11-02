package sample.Problems.Tree;

public class HeightOfSpiralTree {
}

/*
* Given a special Binary Tree whose leaf nodes are connected
* to form a circular doubly linked list.
* Find the height of this special Binary Tree.
*
* Input:
        1
      /   \
     2     3
    /
   4
Output: 3
Explanation: There are 3 edges and 4
nodes in spiral tree where leaf nodes
4 and 3 are connected in circular
doubly linked list form. Thus the
height of spiral tree is 3.
*
* */
class Tree {
    // The logic to find the height of a tree will remain same
    // only we need to check the weather the node is a left or not;

    public static int findTreeHeight(Node root) {
        if (root == null) {
            return 0;
        }

        if (isLeafNode(root)) {
            return 1;
        }

        return 1 + Math.max(findTreeHeight(root.left), findTreeHeight(root.right));
    }

    private static boolean isLeafNode(Node root) {
        return root.left != null && root.left.right == root &&
                root.right != null && root.right.left == root;
    }
}
