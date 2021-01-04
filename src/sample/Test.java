package sample;


import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

        int root = 0;
        int left;
        int right;

        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            try {
                int element = arr[root];
                if (element == 1) {
                    System.out.println("index of first one = " + root);
                    break;
                } else {
                    left = 2 * root + 1;
                    right = 2 * root + 2;

                    queue.offer(left);
                    queue.offer(right);
                    if (arr[left] == 1) {
                        int index = binarySearch(arr, root, left);
                        System.out.println("index of first one = " + index);
                        break;
                    } else if (arr[right] == 1) {
                        if (arr[left] == 0) {
                            System.out.println("index of first one = " + right);
                            break;
                        }
                    } else {
                        root = !queue.isEmpty() ? queue.poll() : -1;
                    }
                }
            } catch (Exception e) {
                System.out.println("Array out of bound, result does not exist");
                break;
            }
        }
    }


    private static int binarySearch(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }

        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;

        if (arr[mid] == 0) {
            return binarySearch(arr, mid + 1, high);
        }

        return binarySearch(arr, low, mid - 1);
    }
}
