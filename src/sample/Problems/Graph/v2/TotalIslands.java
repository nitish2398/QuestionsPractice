package sample.Problems.Graph.v2;

import java.io.IOException;

/**
 * Given a grid of size n*m (n is number of rows and m is number of columns grid has)
 * consisting of '0's(Water) and '1's(Land).
 * Find the number of islands.
 * Note: An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically or diagonally i.e., in all 8 directions.
 *
 *
 * Input:
 * grid = {{0,1,1,1,0,0,0},{0,0,1,1,0,1,0}}
 * Output:
 * 2
 * Explanation:
 * The grid is-
 * 0 1 1 1 0 0 0
 * 0 0 1 1 0 1 0
 * There are two islands.
 *
 */
public class TotalIslands {
    // Function to find the number of islands.
    public int numIslands(char[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    exploreIsland(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private int[][] dirs = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };

    private void exploreIsland(char[][] grid, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValid(newX, newY, grid) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                exploreIsland(grid, visited, newX, newY);
            }
        }
    }

    private boolean isValid(int x, int y, char[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public static void main(String[] args) throws IOException {
        char [][] grid = new char[2][];
        grid[0] = "0111000".toCharArray();
        grid[1] = "0011010".toCharArray();
        int ans = new TotalIslands().numIslands(grid);
        System.out.println(ans);
    }

}
