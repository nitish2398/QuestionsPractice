package sample.Problems.Graph;


/**
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges,
 * where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 *
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 *
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 *
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 *
 * @Link https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 */


public class MinimumDegreeOfConnectedGraphTrio {
    public static int minTrioDegree(int n, int[][] e) {
        int [] edgeCount = new int[n];
        int [][] edges = new int[n+1][n+1];

        for (int[] ints : e) {
            edges[ints[0]][ints[1]] = 1;
            edges[ints[1]][ints[0]] = 1;

            edgeCount[ints[0]]++;
            edgeCount[ints[1]]++;
        }

        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = j+1; k <= n; k++) {
                    if(edges[i][j] == 1 && edges[i][k] == 1 && edges[j][k] == 1) {
                        int degree = edgeCount[i] + edgeCount[j] + edgeCount[k] - 6;
                        result = Math.min(result, degree);
                        if(result == 0) {
                            return result;
                        }
                    }
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        int n = 7;
        int [][] edges = {{1,3},{4,1},{4,3},{2,5},{5,6},{6,7},{7,5},{2,6}};
        System.out.println(minTrioDegree(n, edges));
    }
}
