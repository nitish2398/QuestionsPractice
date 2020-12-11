package sample.Problems.Graph;

import java.util.*;
import java.lang.*;

/**
 * Snake and Ladder Problem
 * Given a snake and ladder board of order 5x6,
 * find the minimum number of dice throws required to reach
 * the destination or last cell (30th cell) from source (1st cell) .
 *
 * Input:
 * The first line of input contains an integer T denoting the no of test cases.
 * Then T test cases follow. Each test case contains two lines.
 * The first line of input contains an integer N denoting the no of ladders and snakes present.
 * Then in the next line are 2*N space separated values a,b
 * which denotes a ladder or a snake at position 'a' which takes to a position 'b'.
 *
 * Example:
 * Input:
 * 2
 * 6
 * 11 26 3 22 5 8 20 29 27 1 21 9
 * 1
 * 2 30
 *
 * Output:
 * 3
 * 1
 *
 */

class Pair1 {
    int x;
    int distance;

    Pair1(int x, int distance) {
        this.x = x;
        this.distance = distance;
    }
}

class GFG {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String[] arr = scan.nextLine().split(" ");

            Map<Integer, ArrayList<Integer>> ladders = new HashMap<>();
            Map<Integer, ArrayList<Integer>> snakes = new HashMap<>();

            initSnakeAndLadders(arr, ladders, snakes);

            Queue<Pair1> queue = new LinkedList<>();
            boolean[] visited = new boolean[31];

            queue.add(new Pair1(1, 0));
            visited[1] = true;

            int result = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                Pair1 current = queue.poll();

                if (current.x == 30) {
                    result = Math.min(result, current.distance);
                }

                for (int i = 1; i <= 6; i++) {
                    if (current.x + i <= 30 &&
                            !visited[current.x + i] &&
                            !snakes.containsKey(current.x + i)) {
                        visited[current.x + i] = true;
                        if (ladders.containsKey(current.x + i)) {
                            for (int dest : ladders.get(current.x + i)) {
                                if (!visited[dest]) {
                                    visited[dest] = true;
                                    queue.add(new Pair1(dest, current.distance + 1));
                                }
                            }
                        } else {
                            queue.add(new Pair1(current.x + i, current.distance + 1));
                        }
                    }
                }
            }

            System.out.println(result);
            t--;
        }
    }

    private static void initSnakeAndLadders(String[] arr, Map<Integer,
            ArrayList<Integer>> ladders, Map<Integer,
            ArrayList<Integer>> snakes) {
        for (int i = 0; i < arr.length; i = i + 2) {
            int x = Integer.valueOf(arr[i]);
            int y = Integer.valueOf(arr[i + 1]);

            if (x <= y) {
                if (ladders.containsKey(x)) {
                    ladders.get(x).add(y);
                } else {
                    ladders.put(x, new ArrayList<Integer>() {{
                        add(y);
                    }});
                }
            } else {
                if (snakes.containsKey(x)) {
                    snakes.get(x).add(y);
                } else {
                    snakes.put(x, new ArrayList<Integer>() {{
                        add(y);
                    }});
                }
            }
        }
    }
}
