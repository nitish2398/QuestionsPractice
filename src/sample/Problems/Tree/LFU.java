package sample.Problems.Tree;

import java.util.HashMap;
import java.util.Map;

public class LFU {
}

class LFUNode {
    int count;
    int key;
    int value;

    LFUNode(int key, int value) {
        this.count = 1;
        this.key = key;
        this.value = value;
    }
}

class MinHeapMap {
    private LFUNode[] heap;
    private int size = 0;
    private int capacity;
    private Map<Integer, Integer> map = new HashMap<>();

    MinHeapMap(int capacity) {
        this.capacity = capacity;
        heap = new LFUNode[capacity];
    }

    void addNewElement(int key, int value) {
        if(map.containsKey(key)) {
            heap[map.get(key)].value = value;
            incrementExisting(map.get(key));
            return;
        }

        if(capacity > 0) {
            LFUNode ele = new LFUNode(key, value);
            if(size == capacity) {
                map.remove(heap[0].key);
                heap[0] = ele;
                map.put(ele.key, 0);
            } else {
                heap[size++] = ele;

                int currentPos = size - 1;
                while(currentPos != getParent(currentPos) && getParent(currentPos) >= 0) {
                    if(heap[currentPos].count <= heap[getParent(currentPos)].count) {
                        swap(currentPos, getParent(currentPos));
                    }
                    currentPos = getParent(currentPos);
                }
            }
        }
    }

    private void incrementExisting(int pos) {
        heap[pos].count += 1;
        minHeapify(pos);
    }

    int getElement(int key) {
        if(map.containsKey(key)) {
            incrementExisting(map.get(key));
            return heap[map.get(key)].value;
        }
        return -1;
    }

    private void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            if ((getLeftChild(pos) < size &&
                    heap[pos].count >= heap[getLeftChild(pos)].count) ||
                    (getRightChild(pos) < size &&
                            heap[pos].count >= heap[getRightChild(pos)].count)) {
                if (getRightChild(pos) >= size ||
                        heap[getLeftChild(pos)].count <= heap[getRightChild(pos)].count) {
                    swap(pos, getLeftChild(pos));
                    minHeapify(getLeftChild(pos));
                } else {
                    swap(pos, getRightChild(pos));
                    minHeapify(getRightChild(pos));
                }
            }
        }
    }

    private int getLeftChild(int pos) {
        return pos * 2 + 1;
    }

    private int getRightChild(int pos) {
        return pos * 2 + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= size / 2 && pos < size;
    }

    private int getParent(int pos) {
        return (pos - 1)/2;
    }

    private void swap(int pos1, int pos2) {
        LFUNode temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
        map.put(heap[pos1].key, pos1);
        map.put(heap[pos2].key, pos2);
    }
}

class LFUCache {

    private MinHeapMap minHeap;

    public LFUCache(int capacity) {
        this.minHeap = new MinHeapMap(capacity);
    }

    public int get(int key) {
        return minHeap.getElement(key);
    }

    public void put(int key, int value) {
        minHeap.addNewElement(key, value);
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1,1);
        lfu.put(2,2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));

    }
}
