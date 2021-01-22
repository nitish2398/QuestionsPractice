package sample.Problems.Arrays;

import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Zero Sum Subarrays
 *
 * Idea is simple, to keep track of sum at every point
 * put the sum and index in map
 *
 * if the sum is already encountered then we have find a zero sum sub array
 * as if sum from i -> j is S and sum from i -> z is also S then sum from j -> z must be zero
 *
 * https://www.youtube.com/watch?v=C9-n_H7dsvU&feature=youtu.be
 */

class ZeroSumSubarrays
{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String [] arr = scan.nextLine().split(" ");

            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            int sum = 0;
            int countOfZeroSubArray = 0;
            for(int i =0; i <arr.length; i++) {
                if(!isParsable(arr[i])) {
                    continue;
                }
                sum += Integer.valueOf(arr[i]);

                if(sum == 0) {
                    countOfZeroSubArray++;
                }

                if(map.containsKey(sum)) {
                    countOfZeroSubArray += map.get(sum).size();
                    map.get(sum).add(i);
                } else {
                    final int index = i;
                    map.put(sum, new ArrayList<Integer>() {{
                        add(index);
                    }});
                }
            }
            System.out.println(countOfZeroSubArray);
            t--;
        }
    }

    private static boolean isParsable(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


