package sample.Problems.Graph;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels.
 * Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * @Link - https://leetcode.com/problems/open-the-lock/
 */

public class OpenTheLock {
    class LockNode {
        String value;
        int dist;

        public LockNode(String value, int dist) {
            this.value = value;
            this.dist = dist;
        }
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadEndSet = new HashSet<>();
        deadEndSet.addAll(Arrays.asList(deadends));

        Queue<LockNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        if(!deadEndSet.contains("0000")) {
            queue.add(new LockNode("0000", 0));
            visited.add("0000");
        }

        while (!queue.isEmpty()) {
            LockNode node = queue.poll();

            if (node.value.equals(target)) {
                return node.dist;
            }

            List<String> possibleNeighbours = getPossibleNeighbours(node.value);

            for (String s : possibleNeighbours) {
                if (!visited.contains(s) && !deadEndSet.contains(s)) {
                    queue.add(new LockNode(s, node.dist + 1));
                    visited.add(s);
                }
            }
        }

        return -1;
    }

    public List<String> getPossibleNeighbours(String value) {
        int[] lockDigits = new int[4];
        int k = 0;
        for (int i = 0; i < value.length(); i++) {
            lockDigits[k++] = Integer.valueOf(String.valueOf(value.charAt(i)));
        }

        return new ArrayList<String>() {{
            add("" + (addOne(lockDigits[0])) + lockDigits[1] + lockDigits[2] + lockDigits[3]);
            add("" + lockDigits[0] + (addOne(lockDigits[1])) + lockDigits[2] + lockDigits[3]);
            add("" + lockDigits[0] + lockDigits[1] + (addOne(lockDigits[2])) + lockDigits[3]);
            add("" + lockDigits[0] + lockDigits[1] + lockDigits[2] + (addOne(lockDigits[3])));
            add("" + (subtractOne(lockDigits[0])) + lockDigits[1] + lockDigits[2] + lockDigits[3]);
            add("" + lockDigits[0] + (subtractOne(lockDigits[1])) + lockDigits[2] + lockDigits[3]);
            add("" + lockDigits[0] + lockDigits[1] + (subtractOne(lockDigits[2])) + lockDigits[3]);
            add("" + lockDigits[0] + lockDigits[1] + lockDigits[2] + (subtractOne(lockDigits[3])));
        }};
    }

    public int addOne(int i) {
        return i == 9 ? 0 : i + 1;
    }

    public int subtractOne(int i) {
        return i == 0 ? 9 : i - 1;
    }

    public static void main(String[] args) {
        OpenTheLock openTheLock = new OpenTheLock();

        System.out.println(openTheLock.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
    }
}
