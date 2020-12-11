package sample.Problems.Graph;

import java.util.*;
import java.lang.*;

/**
 * GRAPH ALGO - TOPOLOGICAL SORT
 * https://www.youtube.com/watch?v=ddTC4Zovtbc
 *
 * Given a sorted dictionary of an alien language
 * having N words and k starting alphabets of standard dictionary.
 * Find the order of characters in the alien language.
 *
 * 2
 * 5 4
 * baa abcd abca cab cad
 * 3 3
 * caa aaa aab
 *
 * Output
 * bdac
 * cab
 *
 * Algo -
 *
 * 1) Create a graph g with number of vertices equal to the size of alphabet in the given alien language.
 * For example, if the alphabet size is 5, then there can be 5 characters in words. Initially there are no edges in graph.
 *
 * 2) Do following for every pair of adjacent words in given sorted array.
 * .....a) Let the current pair of words be word1 and word2. One by one compare
 *         characters of both words and find the first mismatching characters.
 * .....b) Create an edge in g from mismatching character of word1 to that of word2.
 *
 * 3) Print topological sorting of the above created graph.
 */

class Pair<T> {
    T first;
    T second;

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
}

class Graph {
    private Map<Character, ArrayList<Character>> adjList = new HashMap<>();
    private Map<Character, Boolean> visited = new HashMap<>();
    Stack<Character> topologicalResult = new Stack<>();

    public void createGraph(String[] dict, int count) {
        initListAndVisited(dict, count);

        for(int i = 0; i < dict.length-1; i++) {
            Pair<Character> firstDifferentCharacter = getfirstDifferentCharacter(dict[i], dict[i+1]);
            if(firstDifferentCharacter != null) {
                adjList.get(firstDifferentCharacter.first).add(firstDifferentCharacter.second);
            }
        }
    }

    private void initListAndVisited(String[] dict, int count) {
        for(String string: dict) {
            if(adjList.size() == count) {
                break;
            }
            for(Character c: string.toCharArray()) {
                if(!adjList.containsKey(c)) {
                    adjList.put(c, new ArrayList<>());
                    visited.put(c, false);
                }
            }
        }
    }

    private Pair<Character> getfirstDifferentCharacter(String s1, String s2) {
        int i = 0;
        int j = 0;
        while(i < s1.length() && j < s2.length()) {
            if(s1.charAt(i) != s2.charAt(j)) {
                return new Pair<>(s1.charAt(i), s2.charAt(j));
            }
            i++;
            j++;
        }
        return null;
    }

    public void topologicalSort() {
        adjList.forEach((vertex, edges)-> {
            if(!visited.get(vertex)) {
                visited.put(vertex, true);
                topologicalSortUtil(vertex);
            }
        });

    }

    private void topologicalSortUtil(Character vertex) {
        adjList.get(vertex).forEach((child) -> {
            if(!visited.get(child)) {
                visited.put(child, true);
                topologicalSortUtil(child);
            }
        });
        topologicalResult.push(vertex);
    }
}

class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        Graph graph = new Graph();
        graph.createGraph(dict, K);
        graph.topologicalSort();

        String result = "";
        while(!graph.topologicalResult.empty()) {
            result += graph.topologicalResult.pop();
        }

        return result;
    }
}

class TopologicalSort {
    public static void main(String [] arg) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            scan.nextLine();
            String arr[] = scan.nextLine().split(" ");
            System.out.println(new Solution().findOrder(arr, n, k));
            t--;
        }
    }
}
