package sample.Problems.Arrays;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Given an input stream of n integers, find the kth largest element for each element in the stream.
 *
 * Example:
 * Input:
 * 2
 * 4 6
 * 1 2 3 4 5 6
 * 1 2
 * 3 4
 *
 * Output:
 * -1 -1 -1 1 2 3
 * 3 4
 */

public class KthLargestElementInStream {
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();

            int k = scan.nextInt();
            int length = scan.nextInt();

            scan.nextLine();
            String [] stream = scan.nextLine().split(" ");

            for(String s: stream) {
                if(queue.size() < k) {
                    queue.add(Integer.valueOf(s));
                } else if(queue.peek() <= Integer.valueOf(s)) {
                    queue.poll();
                    queue.add(Integer.valueOf(s));
                }
                if(queue.size() < k) {
                    System.out.print("-1 ");
                } else {
                    System.out.print(queue.peek() + " ");
                }
            }
            System.out.println();
            t--;
        }
    }
}
