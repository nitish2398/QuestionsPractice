package sample.Problems.Graph;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Implementing Dijkstra
 * Given a graph of V nodes represented in the form of the adjacency matrix.
 * The task is to find the shortest distance of all the vertex's from the source vertex.
 *
 * https://www.youtube.com/watch?v=lAXZGERcDf4
Note – to get the path, we can store chile, parent map along with weight map in Solution class 
 */

class Vertex {
    int vertex;
    int distance;

    Vertex(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    static Comparator comparator = new Comparator<Vertex>() {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return o1.distance - o2.distance;
        };
    };
}

class HeapMapLocal {
    private PriorityQueue<Vertex> queue;
    private Map<Integer, Vertex> map;

    HeapMapLocal() {
        this.queue = new PriorityQueue<>(Vertex.comparator);
        this.map = new HashMap<>();
    }

    void add(Vertex v) {
        queue.add(v);
        map.put(v.vertex, v);
    }

    boolean contains(int v) {
        return map.get(v) != null;
    }

    Vertex getMinimum() {
        Vertex vertex = queue.poll();
        if(vertex != null) {
            map.remove(vertex.vertex);
        }
        return vertex;
    }

    void decrease(int key, int weight) {
        Vertex current = getCurrentVertex(key);

        queue.remove(current);

        current.distance = weight;

        queue.add(current);
    }

    Vertex getCurrentVertex(int key) {
        return map.get(key);
    }

    boolean hasElement() {
        return !queue.isEmpty();
    }
}
class Dijkstra
{
    static int[] dijkstra(ArrayList<ArrayList<Integer>> g, int src, int V)
    {
        HeapMapLocal minHeap = new HeapMapLocal();
        Map<Integer, Integer> finalDistance = new HashMap<>();

        createMinHeap(minHeap, src, V);

        while(minHeap.hasElement()) {
            Vertex vertex = minHeap.getMinimum();
            finalDistance.put(vertex.vertex, vertex.distance);

            int currentDistance = vertex.distance;
            ArrayList<Integer> edges = g.get(vertex.vertex);
            int i = 0;
            for(int edgeWeight: edges) {
                if(edgeWeight != 0 && minHeap.contains(i)) {
                    if(minHeap.getCurrentVertex(i).distance > edgeWeight + currentDistance) {
                        minHeap.decrease(i, edgeWeight+currentDistance);
                    }
                }
                i++;
            }
        }

        return createResultArray(finalDistance, V);
    }

    private static void createMinHeap(HeapMapLocal minHeap, int src, int V) {
        for(int i = 0; i < V; i++) {
            if(i == src) {
                minHeap.add(new Vertex(src, 0));
            } else {
                minHeap.add(new Vertex(i, Integer.MAX_VALUE));
            }
        }
    }

    private static int[] createResultArray(Map<Integer, Integer> finalDistance, int V) {
        int[] resultDistnace = new int[V];
        for(int i=0; i<V; i++) {
            resultDistnace[i] = finalDistance.get(i);
        }
        return resultDistnace;
    }
}
class Gfg {
    static void printSolution(int dist[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(dist[i] + " ");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());

        while (t > 0) {
            int V = Integer.parseInt(sc.next());
            ;
            ArrayList<ArrayList<Integer>> list = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = new ArrayList<>(V);
                list.add(i, temp);
            }

            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = list.get(i);
                for (int j = 0; j < V; j++) {
                    temp.add(Integer.parseInt(sc.next()));
                }
                list.add(temp);
            }
            int s = Integer.parseInt(sc.next());
            ;
            Dijkstra T = new Dijkstra();
            int[] res = T.dijkstra(list, s, V);
            printSolution(res, V);
            System.out.println();
            t--;
        }
    }
}
