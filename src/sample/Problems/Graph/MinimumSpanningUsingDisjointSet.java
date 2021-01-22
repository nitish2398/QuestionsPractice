package sample.Problems.Graph;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Given a weighted, undirected and connected graph.
 * The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
 *
 * https://www.youtube.com/watch?v=fAuF0EuZVCk - minimum spanning tree
 * https://www.youtube.com/watch?v=ID00PMy0-vE - disjoint sets
 *
 */
public class MinimumSpanningUsingDisjointSet {
    static class Node {
        int data;
        int rank;
        Node parent;

        public Node(int data) {
            this.data = data;
            rank = 0;
            parent = this;
        }
    }

    static class DisjointSet {

        private Map<Integer, Node> map = new HashMap<>();

        public void makeSet(int data) {
            Node node = new Node(data);
            map.put(data, node);
        }

        public Node findSet(Node child) {
            if (child.data == child.parent.data) {
                return child;
            } else {
                child.parent = findSet(child.parent);
            }
            return child.parent;
        }

        public void union(int data1, int data2) {
            Node parent1 = findSet(map.get(data1));
            Node parent2 = findSet(map.get(data2));

            if (parent1 == parent2) {
                return;
            }
            if (parent1.rank >= parent2.rank) {
                parent1.rank = parent1.rank == parent2.rank ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }
        }

        Map<Integer, Node> getMap() {
            return this.map;
        }
    }

    static class Edge {
        int vertex1;
        int vertex2;
        int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        public static Comparator comparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        };

        public String toString() {
            return this.vertex1 + " " + this.vertex2 + " " + this.weight;
        }
    }

    static class MST {

        public static void main(String args[]) throws IOException {

            BufferedReader read =
                    new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(read.readLine());
            while (t-- > 0) {
                String str[] = read.readLine().trim().split(" ");
                int V = Integer.parseInt(str[0]);
                int E = Integer.parseInt(str[1]);

                ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < V; i++) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = 0; j < V; j++) temp.add(Integer.MAX_VALUE);
                    graph.add(temp);
                }
                str = read.readLine().trim().split(" ");
                int k = 0;
                int i = 0;
                while (i++ < E) {
                    int u = Integer.parseInt(str[k++]);
                    int v = Integer.parseInt(str[k++]);
                    int w = Integer.parseInt(str[k++]);
                    u--;
                    v--;
                    graph.get(u).set(v, w);
                    graph.get(v).set(u, w);
                }

                System.out.println(spanningTree(V, E, graph));
            }
        }

        static int spanningTree(int V, int E, ArrayList<ArrayList<Integer>> graph) {
            Object[] edgeList = getAllEdgesSortedByWeight(V, graph);

            int minimumSpanningWeight = 0;

            DisjointSet disjointSet = new DisjointSet();
            for (int i = 0; i < V; i++) {
                disjointSet.makeSet(i);
            }

            for (Object edge : edgeList) {
                Edge currentEdge = (Edge) edge;

                if (disjointSet.findSet(disjointSet.getMap().get(currentEdge.vertex1)) !=
                        disjointSet.findSet(disjointSet.getMap().get(currentEdge.vertex2))) {
                    disjointSet.union(currentEdge.vertex1, currentEdge.vertex2);
                    minimumSpanningWeight += currentEdge.weight;
                }
            }

            return minimumSpanningWeight;
        }

        public static Object[] getAllEdgesSortedByWeight(int V, ArrayList<ArrayList<Integer>> graph) {
            ArrayList<Edge> edgeList = new ArrayList<>();
            int i = 0;
            for (ArrayList<Integer> list : graph) {
                if (i >= V) {
                    break;
                }
                int j = 0;
                for (Integer weight : list) {
                    if (weight != Integer.MAX_VALUE) {
                        edgeList.add(new Edge(i, j, weight));
                    }
                    j++;
                }
                i++;
            }
            Object[] array = edgeList.toArray();
            Arrays.sort(array, Edge.comparator);
            return array;
        }
    }
}
