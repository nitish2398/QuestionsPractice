package sample.Problems.Tree;

import java.util.*;
import java.lang.*;

/**
 * Given inorder and postorder traversals of a Binary Tree in the arrays in[] and post[] respectively.
 * The task is to construct the binary tree from these traversals.
 * For example, if given inorder and postorder traversals are
 * In = {4, 8, 2, 5, 1, 6, 3, 7}
 * Post = {8, 4, 5, 2, 6, 7, 3, 1}
 * then your function should construct below tree.
 * pre = {1,2,4,8,5,3,6,7}
 */

class GfG {
    int size;

    Node buildTree(int in[], int post[], int n) {
        if (n == 0) {
            return null;
        }
        size = n - 1;

        return getTree(in, post);
    }

    Node getTree(int in[], int post[]) {
        if (size < 0 || in == null || in.length == 0) {
            size++; // restore the size, as current element is not used yet
            return null;
        }
        if (in.length == 1) {
            return new Node(in[0]);
        }

        int lastEle = post[size];
        int[] left = getLeftArray(in, lastEle);
        int[] right = getRightArray(in, lastEle);

        size--;

        Node root = new Node(lastEle);
        root.right = getTree(right, post);

        size--;

        root.left = getTree(left, post);

        return root;
    }

    public int[] getLeftArray(int[] in, int ele) {
        int pos = getPos(in, ele);
        if (pos == -1) {
            return null;
        }
        int[] arr = new int[pos];

        for (int i = 0; i < pos; i++) {
            arr[i] = in[i];
        }
        return arr;
    }

    public int[] getRightArray(int[] in, int ele) {
        int pos = getPos(in, ele);
        if (pos == -1) {
            return null;
        }
        int[] arr = new int[in.length - 1 - pos];
        int k = 0;
        for (int i = pos + 1; i < in.length; i++) {
            arr[k++] = in[i];
        }
        return arr;
    }

    public int getPos(int[] arr, int ele) {
        for (int i = 0; i < arr.length; i++) {
            if (ele == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}


//    better Approach
//
//    int search(int arr[],int start,int end,int data)
//    {
//        for(int i=start;i<=end;i++)
//        {
//            if(data==arr[i]) return i;
//        }
//    }
//
//Node* buildTreeUntil(int in[],int post[], int inStart, int inEnd, int *postIndex)
//        {
//        if(inStart>inEnd) return NULL;
//        Node* node=newNode(post[*postIndex]);
//        (*postIndex)--;
//        if(inStart==inEnd) return node;
//        int inIndex=search(in,inStart,inEnd,node->data);
////cout<<inIndex;
//        node->right=buildTreeUntil(in,post,inIndex+1,inEnd,postIndex);
//        node->left=buildTreeUntil(in,post,inStart,inIndex-1,postIndex);
//        return node;
//        }
//
//        Node *buildTree(int in[], int post[], int n)
//        {
//        int pindex=n-1;
//        Node *node=buildTreeUntil(in,post,0,n-1,&pindex);
//        return node;
//        }
