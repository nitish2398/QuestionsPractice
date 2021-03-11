package sample.Problems.LL;

import java.io.*;
import java.util.*;
import java.lang.*;

class LRUDesign {

    public static void main(String[] args) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {

            int capacity = Integer.parseInt(read.readLine());
            int queries = Integer.parseInt(read.readLine());
            LRUCache cache = new LRUCache(capacity);
            String str[] = read.readLine().trim().split(" ");
            int len = str.length;
            int itr = 0;

            for (int i = 0; (i < queries) && (itr < len); i++) {
                String queryType = str[itr++];
                if (queryType.equals("SET")) {
                    int key = Integer.parseInt(str[itr++]);
                    int value = Integer.parseInt(str[itr++]);
                    cache.set(key, value);
                }
                if (queryType.equals("GET")) {
                    int key = Integer.parseInt(str[itr++]);
                    System.out.print(cache.get(key) + " ");
                }
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// design the class in the most optimal way

class LRUNode {
    int key;
    int value;
    LRUNode next;
    LRUNode prev;

    LRUNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class LRUCache
{
    static Map<Integer, LRUNode> cache;
    static int capacity;
    static LRUNode head, tail;

    LRUCache(int cap)
    {
        cache = new HashMap<Integer, LRUNode>();
        capacity = cap;
        head = new LRUNode(0,0);
        tail = new LRUNode(0,0);
        head.next = tail;
        tail.prev = head;
    }

    static void deleteNode(LRUNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    static void addToHead(LRUNode node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    // This method works in O(1)
    public static int get(int key)
    {
        if(cache.get(key) != null) {
            deleteNode(cache.get(key));
            addToHead(cache.get(key));
            return cache.get(key).value;
        }
        return -1;
    }

    // This method works in O(1)
    public static void set(int key, int value)
    {
        // order dependent task
        if(cache.containsKey(key)) {
            deleteNode(cache.get(key));
            cache.remove(key);
        }

        if(cache.size() >= capacity) {
            cache.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        cache.put(key, new LRUNode(key, value));
        addToHead(cache.get(key));
    }
}
