package sample.Problems.Stack;

import java.util.*;
import java.lang.*;

/**
 * An array A containing heights of building was given.
 * Its a rainy season. Calculate the amount of water collected between all the buildings.
 *
 * calculate left maximum of all and store in array
 * calculate right max of all and store in array
 * at every moment do min(leftMax, rightMax) - arr[i]
 *
 * space optimization
 * nstead of maintaing two arrays of size n for storing left and right max of each element,
 * maintain two variables to store the maximum till that point.
 * Since water trapped at any element = min(max_left, max_right) – arr[i].
 * Calculate water trapped on smaller element out of A[lo] and A[hi] first and move the pointers till lo doesn’t cross hi.
 */

class TappingWater {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String[] arr = scan.nextLine().split(" ");

            int leftMax = 0;
            int rightMax = 0;
            int lo = 0;
            int high = n - 1;
            int res = 0;

            while (lo <= high) {
                if (Integer.valueOf(arr[lo]) < Integer.valueOf(arr[high])) {
                    if (Integer.valueOf(arr[lo]) > leftMax) {
                        leftMax = Integer.valueOf(arr[lo]);
                    } else {
                        res += (leftMax - Integer.valueOf(arr[lo]) > 0) ? leftMax - Integer.valueOf(arr[lo]) : 0;
                    }
                    lo ++;
                } else {
                    if (Integer.valueOf(arr[high]) > rightMax) {
                        rightMax = Integer.valueOf(arr[high]);
                    } else {
                        res += (rightMax - Integer.valueOf(arr[high]) > 0) ? rightMax - Integer.valueOf(arr[high]) : 0;
                    }
                    high --;
                }
            }

            System.out.println(res);
            t--;
        }
    }
}

