package sample.Problems.Graph;


/*
 * Minimum Spanning tree using Prim's Algo
 *
 * We use a HeapMap data structure which supports
 * 1. extracr Min - O(logn)
 * 2. add - O(logn)
 * 3. contains - O(1)
 * 4. decrease - O(logn)
 *
 * https://www.youtube.com/watch?v=oP2-8ysT3QQ
 * */

import java.util.*;

class Couple<T, P> {
    T vertex;
    P weight;

    public Couple(T vertex, P weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class HeapMap {
    private static Comparator<Couple<Character, Integer>> comparator = new Comparator<Couple<Character, Integer>>() {
        @Override
        public int compare(Couple<Character, Integer> o1, Couple<Character, Integer> o2) {
            return o1.weight - o2.weight;
        }
    };

    PriorityQueue<Couple<Character, Integer>> queue = new PriorityQueue<>(comparator);
    Map<Character, Couple<Character, Integer>> instancesMap = new HashMap<>();

    Map<Character, Pair<Character>> resultEdge = new HashMap<>();
}

public class PrimsAlgorithm {
    private static void getMinimumSpanningTree(Map<Character, Map<Character, Integer>> graph) {
        HeapMap heapMap = new HeapMap();

        // init instance Map
        graph.forEach((vertex, edges) -> {
            heapMap.instancesMap.put(vertex, new Couple<>(vertex, Integer.MAX_VALUE));
        });

        Character vertex = 'A';
        while (vertex != null) {
            Map<Character, Integer> edges = graph.get(vertex);

            final char currentVertex = vertex;

            // if child has weight less than current weight, then put child in the queue with new weight
            // and update the instance Map
            edges.forEach((child, weight) -> {
                if (heapMap.instancesMap.containsKey(child) &&
                        heapMap.instancesMap.get(child).weight > weight) {
                    heapMap.queue.remove(heapMap.instancesMap.get(child));
                    heapMap.instancesMap.get(child).weight = weight;
                    heapMap.queue.offer(heapMap.instancesMap.get(child));

                    heapMap.resultEdge.remove(child);
                    heapMap.resultEdge.put(child, new Pair<Character>(currentVertex, child));
                }
            });

            // remove current vertex from instanceMap and choose the minimum weight vertex as new instance
            heapMap.instancesMap.remove(vertex);
            if (heapMap.queue.isEmpty()) {
                vertex = null;
            } else {
                Couple<Character, Integer> minimumWeightVertex = heapMap.queue.poll();
                Pair<Character> minimumWeightEdge = heapMap.resultEdge.get(minimumWeightVertex.vertex);
                System.out.println(minimumWeightEdge.first + " " + minimumWeightEdge.second);
                vertex = minimumWeightVertex.vertex;
            }
        }
    }


    public static void main(String[] arg) {
        Map<Character, Map<Character, Integer>> graph = new HashMap<Character, Map<Character, Integer>>() {{
            put('A', new HashMap<Character, Integer>() {{
                put('B', 3);
                put('D', 1);
            }});
            put('B', new HashMap<Character, Integer>() {{
                put('D', 3);
                put('A', 3);
                put('C', 1);
            }});
            put('C', new HashMap<Character, Integer>() {{
                put('B', 1);
                put('D', 1);
                put('E', 5);
                put('F', 4);
            }});
            put('D', new HashMap<Character, Integer>() {{
                put('A', 1);
                put('B', 3);
                put('C', 1);
                put('E', 6);
            }});
            put('E', new HashMap<Character, Integer>() {{
                put('C', 5);
                put('D', 6);
                put('F', 2);
            }});
            put('F', new HashMap<Character, Integer>() {{
                put('C', 4);
                put('E', 2);
            }});
        }};

        getMinimumSpanningTree(graph);
    }
}
