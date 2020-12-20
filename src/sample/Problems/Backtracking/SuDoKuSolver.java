package sample.Problems.Backtracking;

import java.util.*;
import java.lang.*;

/**
 * Given an incomplete Sudoku configuration in terms of a 9 x 9  2-D square matrix (mat[][]).
 * The task to print a solved Sudoku. For simplicity you may assume that there will be only one unique solution.
 * https://www.geeksforgeeks.org/sudoku-backtracking-7/
 */

class SuDoKuSolver {
    private static final int SIZE = 9;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            int[][] arr = new int[SIZE][SIZE];



            int[][] grid = {
                    {3, 0, 6, 5, 0, 8, 4, 0, 0},
                    {5, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 7, 0, 0, 0, 0, 3, 1},
                    {0, 0, 3, 0, 1, 0, 0, 8, 0},
                    {9, 0, 0, 8, 6, 3, 0, 0, 5},
                    {0, 5, 0, 0, 9, 0, 6, 0, 0},
                    {1, 3, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 4},
                    {0, 0, 5, 2, 0, 6, 3, 0, 0}
            };

            solveSuDuKo(grid);
            t--;
        }
    }

    private static void solveSuDuKo(int[][] arr) {
        if (findSolution(arr, 0, 0)) {
            printArr(arr);
        }
    }

    private static boolean findSolution(int[][] arr, int row, int col) {

        for (int i = row; i < SIZE; i++, col = 0) {
            for (int j = col; j < SIZE; j++) {
                if (arr[i][j] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(arr, i, j, num)) {
                            arr[i][j] = num;

                            if (findSolution(arr, i, j + 1)) {
                                return true;
                            } else {
                                arr[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
            ;
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false;
            }
            if (board[row][i] == val) {
                return false;
            }
        }

        int startRow = getStartRowOrColumn(row);
        int startCol = getStartRowOrColumn(col);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int getStartRowOrColumn(int index) {
        switch (index) {
            case 0:
            case 1:
            case 2:
                return 0;

            case 3:
            case 4:
            case 5:
                return 3;

            case 6:
            case 7:
            case 8:
                return 6;
        }
        return -1;
    }
}

