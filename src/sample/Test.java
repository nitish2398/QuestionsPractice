package sample;

// put - params (value double, long timeStamp) -
// get - Statistics (sum, average) - 1 minute


import java.util.*;

class Pair {
    double price;
    double count;
}

public class Test {

    private static Map<Long, Map<Long, Double>> bookings = new HashMap<>(); // maxSize = 60

    private final int DELIMITER = 60;

    public static void main(String[] args) {
        System.currentTimeMillis();
    }


    public static boolean put(double price, long timeStamp) {
        long second = timeStamp / 1000;
        long currentTimeInSecond = System.currentTimeMillis() / 1000;

        long index = currentTimeInSecond - second;


        if (!bookings.containsKey(second)) {
            bookings.put(index, new HashMap<>());
        }

        bookings.get(second).put(timeStamp, price);

        return true;
    }

    public static BookingStat get() {
        Long dateTimeNowInsec = System.currentTimeMillis();

        return new BookingStat();
    }
}

class BookingStat {
    int sum;
    int avg;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }
}

class So {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> adj, int S) {
        ArrayList<ArrayList<ArrayList<Integer>>> graph = new ArrayList<>();
        createGraph(adj, graph, V);
        int[] distance = new int[V];
        Arrays.fill(distance, 100000000);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        priorityQueue.add(new Node(S, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int vertex = node.val;
            int weight = node.weight;

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                distance[vertex] = weight;

                ArrayList<ArrayList<Integer>> edges = graph.get(vertex);
                for (ArrayList<Integer> next : edges) {
                    priorityQueue.add(new Node(next.get(0), weight + next.get(1)));
                }
            }
        }
        return distance;
    }

    static void createGraph(ArrayList<ArrayList<Integer>> adj,
                            ArrayList<ArrayList<ArrayList<Integer>>> graph,
                            int v) {
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> edge : adj) {
            graph.get(edge.get(0)).add(new ArrayList<Integer>() {{
                add(edge.get(1));
                add(edge.get(2));
            }});
        }
    }

    static class Node implements Comparable<Node> {
        int val;
        int weight;

        public Node(int val, int weight) {
            this.val = val;
            this.weight = weight;
        }

        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bellman_ford(2, new ArrayList<ArrayList<Integer>>() {{
            add(new ArrayList<Integer>() {{
                add(0);
                add(1);
                add(9);
            }});
        }}, 0)));
    }
}


class Solution66 {
    static ArrayList<ArrayList<String>> allPalindromicPerms(String s) {
        boolean arr[][] = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            arr[i][i] = true;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                arr[i][i + 1] = true;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                // System.out.println("i = " + i + "j = " + j);
                if (s.charAt(i) == s.charAt(j) && arr[i + 1][j - 1]) {
                    arr[i][j] = true;
                }
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j])
                    list.get(i).add(j);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(j + "   ");
            }
            System.out.println();
        }
        print(list, 0, 0, s);

        return new ArrayList();
    }

    static void print(List<List<Integer>> list, int i, int j, String s) {
        if (i >= list.size() || j >= list.size()) {
            return;
        }
        for (int p = 0; p < list.size(); p++) {
            if (p == i) {
                System.out.print(s.substring(p, j + 1));
                p = j + 1;
                continue;
            }
        }
    }

    void bfs(String target,
             Set<String> wordList,
             Set<String> visited,
             boolean[] freezeQueue,
             ArrayList<ArrayList<String>> result,
             Queue<String[]> queue,
             int [] minLen) {
        if (queue.isEmpty()) {
            return;
        }

        String[] data = queue.poll();
        String word = data[0];
        String path = data[1];


        if (word.equals(target)) {
            String [] paths = path.split(",");

            if(paths.length <= minLen[0]) {
                freezeQueue[0] = true;
                minLen[0] = paths.length;
                result.add(new ArrayList<>(Arrays.asList(paths)));
            }
        }

        if (word.length() != target.length()) {
            return;
        }

        visited.add(word);
        if (!freezeQueue[0]) {
            for (int i = 0; i < word.length(); i++) {
                char[] startArr = word.toCharArray();
                for (int j = 0; j < 26; j++) {
                    startArr[i] = (char) ('a' + j);
                    String nextWord = new String(startArr);
                    if (wordList.contains(nextWord) && !visited.contains(nextWord) && !nextWord.equals(word)) {
                        queue.add(new String[]{nextWord, path + "," + nextWord});
                    }
                }
            }
        }

        bfs(target, wordList, visited, freezeQueue, result, queue, minLen);
        visited.add(word);
    }

    public ArrayList<ArrayList<String>> findSequences(String start, String target, String[] wordList) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        bfs(target, new HashSet<>(Arrays.asList(wordList)), visited, new boolean[]{false}, result,
                new LinkedList<String[]>() {{
                    add(new String[]{start, start});
                }}, new int[] {Integer.MAX_VALUE});
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution66().findSequences("m", "l",
                new String[]{"n", "l", "m"}));

        System.out.println(new Solution66().findSequences("der", "dfs",
                new String[]{"des", "der", "dfr", "dgt", "dfs"}));

        System.out.println(new Solution66().findSequences("f", "g",
                new String[]{"e", "f", "g"}));
    }
};
