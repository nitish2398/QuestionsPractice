package sample.Problems.Arrays;

import sample.Problems.Hard;

import java.util.*;
import java.lang.*;
import java.io.*;

class KthSmallestElement
{
    /**
     * Given an array arr[] and a number K where K is smaller
     * than size of array, the task is to find the Kth smallest
     * element in the given array. It is given that all array elements
     * are distinct.
     */

    /** @author nitish-jindal
     * we can use MinHeap, but the complexity will O(n+klogn)
     * Hence we will use QuickSelect, which is a modification of HeapSort
     * its worst complexity is O(n^2) but avg is O(n)
     */
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            int n = scan.nextInt();
            int [] arr = new int[n];

            for(int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            int k = scan.nextInt();

            System.out.println(getKthSmallestElement(arr, 0, n-1, k));
            t--;
        }
    }

    public static int getKthSmallestElement(int []arr, int low, int high, int k) {
        if(k <= arr.length && low <= high) {
            int pivotPosition = partition(arr, low, high);

            if(pivotPosition == k-1) {
                return arr[pivotPosition];
            }

            if(pivotPosition > k-1) {
                return getKthSmallestElement(arr, low, pivotPosition-1, k);
            }

            return getKthSmallestElement(arr, pivotPosition+1, high, k-pivotPosition);
        }

        return Integer.MAX_VALUE;
    }

    public static int partition(int [] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for(int j = low; j < high; j++) {
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    public static void swap(int [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
