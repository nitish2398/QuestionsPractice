package sample.Problems.Arrays;

import java.util.ArrayList;

/*
* https://www.youtube.com/watch?v=LPFhl65R7ww
* */

public class MedianOfSortedArrays {
    private static int medianOfSortedArrays(int a[], int b[]) {
        int [] arr1, arr2;

        // store smaller array in arr 1 and bigger in array 2;
        if(a.length > b.length) {
            arr1 = b;
            arr2 = a;
        } else {
            arr1 = a;
            arr2 = b;
        }

        // apply binary search on smaller array and find the partitions
        return getMedianUsingBinarySearch(arr1, arr2, 0, arr1.length-1);
    }

    private static int getMedianUsingBinarySearch(int [] arr1, int [] arr2, int min, int max) {
        if(min > max) {
            return -1;
        }

        int partition1 = (max + min) / 2;
        // length + 1 will take care of both cases, even and odd array
        // element on left = element of right (even case)
        // element on left - 1 = element on right (odd case)
        int partition2 = ((arr1.length + arr2.length + 1)/2) - partition1;

        if(partition1 > 0 && partition2 > 0 &&
                arr1[partition1-1] <= arr2[partition2] &&
                arr1[partition1] >= arr2[partition2-1]) {
            return (arr1.length + arr2.length) % 2 == 1 ? Math.max(arr1[partition1-1], arr2[partition2-1]) :
                    (Math.max(arr1[partition1-1], arr2[partition2-1]) + Math.min(arr1[partition1], arr2[partition2]))/2;
        }

        if(partition1 > 0 && arr1[partition1-1] > arr2[partition2]) {
            return getMedianUsingBinarySearch(arr1, arr2, min, partition1-1);
        }

        return getMedianUsingBinarySearch(arr1, arr2, partition1+1, max);
    }

    public static void main(String arg[]) {
        int [] arr1 = {1,2,4,6,10};
        int [] arr2 = {4,5,6,9,12};

        System.out.println(medianOfSortedArrays(arr1, arr2));
    }
}
