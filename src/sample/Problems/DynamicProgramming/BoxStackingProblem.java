package sample.Problems.DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Box Stacking
 * <p>
 * You are given a set of N types of rectangular 3-D boxes,
 * where the ith box has height h, width w and length l.
 * You task is to create a stack of boxes which is as tall as possible,
 * but you can only stack a box on top of another box if the dimensions
 * of the 2-D base of the lower box are each strictly larger than those of
 * the 2-D base of the higher box. Of course, you can rotate a box so that any
 * side functions as its base.It is also allowable to use multiple instances of
 * the same type of box. You task is to complete the function maxHeight which returns
 * the height of the highest possible stack so formed.
 * <p>
 * Input:
 * n = 4
 * height[] = {4,1,4,10}
 * width[] = {6,2,5,12}
 * length[] = {7,3,6,32}
 * Output: 60
 * Explanation: One way of placing the boxes is
 * as follows in the bottom to top manner:
 * (Denoting the boxes in (l, w, h) manner)
 * (12, 32, 10) (10, 12, 32) (6, 7, 4) (5, 6, 4)
 * (4, 5, 6) (2, 3, 1) (1, 2, 3)
 * Hence, the total height of this stack is
 * 10 + 32 + 4 + 4 + 6 + 1 + 3 = 60.
 * No other combination of boxes produces a height
 * greater than this.
 * <p>
 * https://www.youtube.com/watch?v=9mod_xRB-O0
 * <p>
 * we will use same algo of longest increasing sub sequence
 * <p>
 * 1. get all the eligible instances of boxes, such that length >= width
 * 2. sort this list according to base area
 * 3. apply longest increasing sub sequence on Height of boxes
 * 4. store a resul array to backtrack the boxes used
 */

class BoxAndBaseArea {
    int length;
    int width;
    int height;
    private int baseArea;

    BoxAndBaseArea(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.baseArea = length * width;
    }

    @Override
    public String toString() {
        return "Length = " + this.length +
                " Width = " + this.width +
                " Height = " + this.height;
    }

    static Comparator comparator = new Comparator<BoxAndBaseArea>() {
        @Override
        public int compare(BoxAndBaseArea o1, BoxAndBaseArea o2) {
            return o2.baseArea - o1.baseArea;
        }
    };
}

class Point3D {
    int x;
    int y;
    int z;

    Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class BoxStackingProblem {
    private static ArrayList<BoxAndBaseArea> boxList;

    private static int maxHeight(int[] height, int[] width, int[] length, int n) {
        boxList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            addBoxToArray(height[i], width[i], length[i]);
        }
        Collections.sort(boxList, BoxAndBaseArea.comparator);

        int[] maxHeight = new int[boxList.size()];
        for (int i = 0; i < boxList.size(); i++) {
            maxHeight[i] = boxList.get(i).height;
        }

        int[] result = new int[boxList.size()];
        for (int i = 0; i < boxList.size(); i++) {
            result[i] = i;
        }

        for (int i = 1; i < boxList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (boxList.get(i).length < boxList.get(j).length &&
                        boxList.get(i).width < boxList.get(j).width &&
                        maxHeight[i] < maxHeight[j] + boxList.get(i).height) {
                    maxHeight[i] = maxHeight[j] + boxList.get(i).height;
                    result[i] = j;
                }
            }
        }

        int index = maxElemnet(maxHeight);
        int maxEle = maxHeight[index];

        while (true) {
            System.out.println(boxList.get(index).toString());

            if (result[index] == index) {
                break;
            }

            index = result[index];
        }

        boxList = null;
        return maxEle;
    }

    private static void addBoxToArray(int x, int y, int z) {

        Point3D point = new Point3D(x, y, z);
        sort(point); // x = minimum of 3, z = maximum of 3

        boxList.add(new BoxAndBaseArea(point.z, point.y, point.x));
        boxList.add(new BoxAndBaseArea(point.y, point.x, point.z));
        boxList.add(new BoxAndBaseArea(point.z, point.x, point.y));
    }

    private static void sort(Point3D point) {
        if (point.x > point.y) {
            int temp = point.x;
            point.x = point.y;
            point.y = temp;
        }
        if (point.x > point.z) {
            int temp = point.x;
            point.x = point.z;
            point.z = temp;
        }
        if (point.y > point.z) {
            int temp = point.y;
            point.y = point.z;
            point.z = temp;
        }
    }

    private static int maxElemnet(int[] arr) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        //sc.next();
        while (t-- > 0) {
            int n = sc.nextInt();

            int A[] = new int[1000];
            int B[] = new int[1000];
            int C[] = new int[1000];

            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();

                A[i] = a;
                B[i] = b;
                C[i] = c;
            }

            System.out.println(maxHeight(A, B, C, n));
        }
    }
}

/*
1
4
4 6 7 1 2 3 4 5 6 10 12 32
output = 60
 */

