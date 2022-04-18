package sample.Problems;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    private static Comparator comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    };
    public static void main(String[] args) {
        System.out.println(new Main().solution(19, new int[] {23, 28, 8, 20, 28, 27, 18}));
        System.out.println(new Main().solution(10, new int[] {1,3,5,7}));



        int [][] arr = new int[][] {
                {1, 3}, {0, 1}
        };

        Arrays.sort(arr, comparator);


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    int solution(int n, int[] cabTripTime) {
        int max = Integer.MIN_VALUE;
        for(int val : cabTripTime) {
            max = Math.max(max, val);
        }

        int low = 0;
        int high = (max * n);
        int mid = (high + low)/2;

        while(low <= high) {
            mid = (high + low)/2;

            int ans = 0;
            for(int val : cabTripTime) {
                //System.out.println(mid + " " + val);
                ans += (mid/val);
            }

            if(low == high) {
                //System.out.println(ans + " " + n);
                if(ans < n) {
                    return mid + 1;
                } else if (ans > n) {
                    return mid;
                }
            }

            if(ans == n) {
                break;
            }

            if(ans < n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return mid;
    }
}
