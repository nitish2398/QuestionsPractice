package sample.Problems.Tree;

import java.util.*;
import java.lang.*;
import java.io.*;

public class NodesAtGivenDistanceInBinaryTreeHard {
}

/*
* Given a binary tree, a target node in the binary tree,
* and an integer value k, find all the nodes that are at
* distance k from the given target node. No parent pointers are available.
* Example 1:
* Input :
          20
        /    \
      8       22
    /   \
   4    12
       /   \
      10    14

Target Node = 8
K = 2

Output: 10 14 22

Explanation: The three nodes at distance 2
from node 8 are 10, 14, 22.
*
* Example 2:
* Input :
         20
       /    \
      7      24
    /   \
   4     3
        /
       1

Target Node = 7
K = 2

Output: 1 24
* */

class Solution {
    private static ArrayList<Integer> list;

    public static ArrayList<Integer> KDistanceNodes(Node root, int target, int k) {
        list = new ArrayList<>();
        KDistanceNodesUtil(root, target, k);
        Collections.sort(list);
        return list;
    }

    private static int KDistanceNodesUtil(Node root, int target, int k) {
        if(root == null) {
            return -1;
        }

        if(root.data == target) {
            printKNodesDown(root, k);
            return 0;
        }

        int leftDistance = KDistanceNodesUtil(root.left, target, k);
        if(leftDistance != -1) {
            if(leftDistance + 1 == k) {
                list.add(root.data);
            } else {
                printKNodesDown(root.right, k - 2 - leftDistance);
            }
            return leftDistance+1;
        }

        int rightDistance = KDistanceNodesUtil(root.right, target, k);
        if(rightDistance != -1) {
            if(rightDistance + 1 == k) {
                list.add(root.data);
            } else {
                printKNodesDown(root.left, k - 2 - rightDistance);
            }
            return rightDistance+1;
        }

        return -1;
    }

    private static void printKNodesDown(Node node, int k) {
        if(node != null) {
            if(k == 0) {
                list.add(node.data);
            }
            printKNodesDown(node.left, k-1);
            printKNodesDown(node.right, k-1);
        }
    }
}



class GFG
{
    static Node buildTree(String str)
    {
        // Corner Case
        if(str.length() == 0 || str.equals('N'))
            return null;
        String[] s = str.split(" ");

        Node root = new Node(Integer.parseInt(s[0]));
        Queue <Node> q = new LinkedList<Node>();
        q.add(root);

        // Starting from the second element
        int i = 1;
        while(!q.isEmpty() && i < s.length)
        {
            // Get and remove the front of the queue
            Node currNode = q.remove();

            // Get the current node's value from the string
            String currVal = s[i];

            // If the left child is not null
            if(!currVal.equals("N"))
            {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.left);
            }

            // For the right child
            i++;
            if(i >= s.length)
                break;
            currVal = s[i];

            // If the right child is not null
            if(!currVal.equals("N"))
            {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.right);
            }

            i++;
        }

        return root;
    }

    public static void main(String args[]) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t>0)
        {
            String s = br.readLine().trim();
            Node root = buildTree(s);
            Solution T = new Solution();
            int target = Integer.parseInt(br.readLine().trim());
            int k = Integer.parseInt(br.readLine().trim());
            ArrayList<Integer> res = new ArrayList<Integer>();
            res = T.KDistanceNodes(root,target,k);
            for(int i = 0;i<res.size();i++)
                System.out.print(res.get(i) + " ");
            System.out.println();
            t--;
        }
    }
}
