package sample.Problems.PriorityQueue;

import java.util.*;
import java.lang.*;

/**
 * Given a string S with repeated characters (only lowercase).
 * The task is to rearrange characters in a string such that no
 * two adjacent characters are same.
 * <p>
 * Input: aaabc
 * Output: abaca
 * <p>
 * Input: aaabb
 * Output: ababa
 */

/**
 * 1. Use priority queue/ maximum heap to store the elements accoring to freq such that hightest freq element should be root
 * 2. pop the root
 * 2. the the character to result
 * 3. decrease the freq
 * 4. push the prev element to queue
 * 5. assign prev to current root element
 *
 * https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
 *
 */
class Pair {
    char key;
    int value;

    public Pair(char key, int value) {
        this.key = key;
        this.value = value;
    }

    public static Comparator<Pair> comparator = new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.value - o1.value;
        }
    };
}

class GFG {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();

        while (t != 0) {
            PriorityQueue<Pair> queue = new PriorityQueue<>(Pair.comparator);

            String s = scan.nextLine();
            Map<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                if (!map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), 1);
                } else {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                }
            }

            map.forEach((key, value) -> {
                queue.add(new Pair(key, value));
            });

            String res = "";
            Pair prev = null;
            while (!queue.isEmpty()) {
                Pair current = queue.poll();

                res += current.key;
                current.value--;

                if (prev != null) {
                    queue.add(prev);
                }

                prev = (current.value > 0) ? current : null;
            }

            if (res.length() == s.length()) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }


            t--;
        }
    }
}
