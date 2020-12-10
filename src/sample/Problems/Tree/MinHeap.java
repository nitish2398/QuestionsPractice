package sample.Problems.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class MinHeap {

    private static int[] heap;
    private static int size = -1;

    private static int getRightChild(int node) {
        return 2 * node + 2;
    }

    private static int getLeftChild(int node) {
        return 2 * node + 1;
    }

    private static int getParent(int pos) {
        return (pos - 1) / 2;
    }

    private static void addElement(int ele) {
        heap[++size] = ele;

        int current = size;

        if(getParent(current) >= 0)
            while (heap[getParent(current)] > heap[current]) {
                swap(getParent(current), current);

                current = getParent(current);
            }

    }

    private static int getMin() {
        if (size == -1) {
            return size;
        }
        return heap[0];
    }

    private static int extractMin() {
        int ele = getMin();

        heap[0] = heap[size];
        size--;
        heapify(0);

        return ele;
    }

    private static void heapify(int pos) {
        if(size <= 0) {
            return;
        }
        if(isValidPosition(getLeftChild(pos)) && isValidPosition(getRightChild(pos))) {
            int smallerChild = (heap[getLeftChild(pos)] < heap[getRightChild(pos)]) ?
                    getLeftChild(pos) : getRightChild(pos);
            if(heap[smallerChild] < heap[pos]) {
                swap(pos, smallerChild);
                heapify(smallerChild);
            }
        } else if (isValidPosition(getLeftChild(pos)) &&
                heap[getLeftChild(pos)] < heap[pos]) {
            swap(pos, getLeftChild(pos));
            heapify(getLeftChild(pos));
        } else if (isValidPosition(getRightChild(pos)) &&
                heap[getRightChild(pos)] < heap[pos]) {
            swap(pos, getRightChild(pos));
            heapify(getRightChild(pos));
        }
    }

    private static boolean isValidPosition(int pos) {
        return pos <= size && pos >= 0;
    }
    private static void swap(int pos1, int pos2) {
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private static void removeAll() {
        size = -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        heap = new int[n];
        for (int i = 0; i < n; i++) {
            addElement(scanner.nextInt());
        }

        for (int i = 0; i < n; i++) {
            System.out.print(extractMin() + " ");
        }
        System.out.println("");
    }

    static boolean isCyclic(ArrayList<ArrayList<Integer>> adj, int V)
    {
        boolean visited[] = new boolean[adj.size()];
        int counter = 0;
        boolean isCycleExist = false;

        for(ArrayList<Integer> list: adj) {
            visited[counter++] = true;
            for(int data: list) {
                if(visited[data] == true) {
                    isCycleExist = true;
                    break;
                }
            }
            if(isCycleExist) {
                break;
            }
        }

        return isCycleExist;
    }
}

/*
9
5 3 17 10 84 19 6 22 9
 */

