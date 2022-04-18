package sample;

import java.util.*;

public class Solution {
    public boolean canReach(int[] arr, int start) {
        int length = arr.length;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> indexQueue = new LinkedList<>();

        indexQueue.offer(start);
        visited.add(start);

        while (!indexQueue.isEmpty()) {
            int index = indexQueue.poll();
            int value = arr[index];

            if (value == 0) {
                return true;
            }

            if (index + value < length && !visited.contains(index + value)) {
                indexQueue.offer(index + value);
            }

            if (index - value >= 0 && !visited.contains(index - value)) {
                indexQueue.offer(index - value);
            }
        }

        return false;
    }
}

class Codec {
    public String[] wordBoggle(char board[][], String[] values) {
        Set<String> result = new TreeSet<>();

        for(String val: values) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if(val.charAt(0) == board[i][j]) {
                        boolean[][] visited = new boolean[board.length][board[0].length];
                        visited[i][j] = true;
                        dfs(board, val, visited, result, i, j, "" + board[i][j]);
                        if(result.contains(val)) {
                            break;
                        }
                    }
                }
                if(result.contains(val)) {
                    break;
                }
            }
        }

        String [] array = new String[result.size()];
        int k = 0;
        for(String ele: result) {
            array[k++] = ele;
        }
        return array;
    }

    private int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    void dfs(char[][] board, String target, boolean[][] visited, Set<String> result,
             int x, int y, String current) {
        if(target.equals(current)) {
            result.add(current);
        }

        for (int[] dir : directions) {
            int nx = dir[0] + x;
            int ny = dir[1] + y;

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length) {
                if (!visited[nx][ny]) {
                    visited[x][y] = true;
                    dfs(board, target, visited, result, nx, ny, current + board[nx][ny]);
                    visited[x][y] = false;
                }
            }
        }
    }
}