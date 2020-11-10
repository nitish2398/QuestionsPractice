package sample.Problems.PriorityQueue;

import java.util.*;

/*
* Given N numbers in an array.
* Your task is to keep at-most K numbers at the top (According to their frequency).
*  We basically need to print top k numbers when input stream
* has included k distinct elements, else need to print all distinct elements sorted by frequency.

Example 1:

Input:
N=5, K=4
arr[] = {5, 2, 1, 3, 2}
Output: 5 2 5 1 2 5 1 2 3 5 2 1 3 5
Explanation:
Firstly their was 5 whose frequency
is max till now. so print 5.
Then 2 , which is smaller than 5 but
their frequency is same so print 2 5.
Then 1, which is smallet among all the
number arrived, so print 1 2 5.
Then 3 , so print 1 2 3 5
Then again 2, which has the highest
frequency among all number so 2 1 3 5.
*
*
* */

public class TopkNumbersInAStream {
    public static void main(String [] arg) {
        int [] arr = {6, 1, 11, 4, 5, 8, 12, 4, 3};
        Solution.kTop(arr, arr.length, 4).forEach(
                val -> {
                    System.out.print(val + " ");
                }
        );
    }
}

class Input {
    int val;
    int freq;

    public Input(int val, int freq) {
        this.val = val;
        this.freq = freq;
    }

    public static Comparator comparator = new Comparator<Input>() {
        @Override
        public int compare(Input o1, Input o2) {
            if (o1.freq != o2.freq) {
                return o2.freq - o1.freq;
            }
            return o1.val - o2.val;
        }
    };
}

class Solution {
    static Map<Integer, Input> map = new HashMap<>();

    static ArrayList<Integer> kTop(int[] a, int n, int k) {
        PriorityQueue<Input> priorityQueue = new PriorityQueue<>(Input.comparator);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            // remove the input object from queue if already present
            if (map.containsKey(a[i])) {
                priorityQueue.remove(map.get(a[i]));
                map.put(a[i], new Input(a[i], map.get(a[i]).freq + 1));
            } else {
                map.put(a[i], new Input(a[i], 1));
            }

            // add the updated object in queue
            priorityQueue.add(map.get(a[i]));

            // print k top objects of queue
            int j = 0;
            PriorityQueue<Input> tempQueue = new PriorityQueue<>(Input.comparator);
            while(j != k && !priorityQueue.isEmpty()) {
                Input temp = priorityQueue.poll();
                arrayList.add(temp.val);
                tempQueue.add(temp);
                j++;
            }
            priorityQueue.addAll(tempQueue);
        }
        return arrayList;
    }

    // time complexity = O(n* 3k) -> O(n*k)
    // space complexity = O(n)
}
