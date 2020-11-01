package sample;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Given a positive integer N, print its corresponding column title as it would appear in an Excel sheet.
 * For N =1 we have column A, for 27 we have AA and so on.
 * <p>
 * Note: The alphabets are all in uppercase.
 * <p>
 * Input:
 * 1
 * 51
 * Output:
 * AY
 */

class GFG {
    private static String result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t != 0) {
            result = "";
            long n = scan.nextLong();
            getResultExcelValue(n);
            System.out.println(result);
            t--;
        }
    }

    private static void getResultExcelValue(long n) {
        while (n > 0) {
            if (n <= 26) {
                result = getCharacterFromInteger(n) + result;
                break;
            } else {
                long division = n / 26;
                long multipication = division * 26;
                if (multipication == n) {
                    result = 'Z' + result;
                    n--;
                } else {
                    result = getCharacterFromInteger(n - multipication) + result;
                }
            }
            n = n / 26;
        }
    }

    class Node {
        int data;
        Node left;
        Node right;
        Node nextRight;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
            nextRight = null;
        }
    }

    public static void connect(Node p) {
        Queue<Node> queue = new LinkedList<>();
        Node prev = null;
        queue.add(p);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }

            if (prev != null) {
                prev.nextRight = current;
            }

            if (current != null) {
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            prev = current;
        }
    }


    private static char getCharacterFromInteger(long x) {
        x--;
        return (char) ('A' + x);
    }
}

class Sum {
    private static SortedSet<String> result;
    private static int[] arr = new int[1000];

    static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        Object[] input = A.toArray();
        result = null;
        result = new TreeSet<>();
        getCombinationSum(input, input.length - 1, B, 0);

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        for (String string : result) {
            String[] numbers = string.split(" ");
            ArrayList<Integer> newArraylist = new ArrayList<>();
            for (String s : numbers) {
                newArraylist.add(Integer.valueOf(s));
            }
            arrayLists.add(newArraylist);
        }

        return arrayLists;
    }

    private static void getCombinationSum(Object[] input, int max, int target, int k) {
        if (max < 0 || target < 0) {
            return;
        }

        if (target == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                list.add(arr[i]);
            }
            Collections.sort(list);
            result.add(getString(list));
            return;
        }

        arr[k++] = (int) input[max];

        getCombinationSum(input, max, target - (int) input[max], k);
        getCombinationSum(input, max - 1, target - (int) input[max], k);

        k--;
        getCombinationSum(input, max - 1, target, k);
    }

    private static String getString(ArrayList<Integer> list) {
        String res = "";
        for (Integer integer : list) {
            res = res + integer + " ";
        }
        return res;
    }

    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            arrayList.add(x);
        }

        int target = scan.nextInt();

        ArrayList<ArrayList<Integer>> arrayLists = combinationSum(arrayList, target);

        for (ArrayList<Integer> list : arrayLists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println("");
        }
    }
}


class Sum1 {
    private static ArrayList<ArrayList<Integer>> result;
    private static int[] arr = new int[1000];
    private static Set<String> alreadyPresent;

    static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        SortedSet<Integer> sortedSet = new TreeSet<>(Collections.reverseOrder());
        for (Integer integer : A) {
            sortedSet.add(integer);
        }
        Object[] input = sortedSet.toArray();

        result = null;
        result = new ArrayList<>();

        alreadyPresent = null;
        alreadyPresent = new HashSet<>();

        getCombinationSum(input, input.length - 1, B, 0);

        return result;
    }

    private static void getCombinationSum(Object[] input, int max, int target, int k) {
        if (max < 0 || target < 0) {
            return;
        }

        if (target == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                list.add(arr[i]);
            }
            Collections.sort(list);
            String listToString = list.toString();
            if (!alreadyPresent.contains(listToString)) {
                result.add(list);
                alreadyPresent.add(listToString);
            }
            return;
        }

        arr[k++] = (int) input[max];

        getCombinationSum(input, max, target - (int) input[max], k);
        getCombinationSum(input, max - 1, target - (int) input[max], k);

        k--;
        getCombinationSum(input, max - 1, target, k);
    }

    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            arrayList.add(x);
        }

        int target = scan.nextInt();

        ArrayList<ArrayList<Integer>> arrayLists = combinationSum(arrayList, target);

        for (ArrayList<Integer> list : arrayLists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println("");
        }
    }

    int countSubstr(String s) {
        int countOne = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                countOne++;
            }
        }
        if (countOne == 0 || countOne == 1) {
            return 0;
        }
        countOne--;
        return (countOne) * (countOne - 1) / 2;
    }
}

class GFG1 {
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String [] s = scan.nextLine().split(" ");

            System.out.print("1 ");

            int prev = 1;
            for(int i = 1; i <s.length; i++) {
                int current = 1;
                if(Integer.valueOf(s[i-1]) <= Integer.valueOf(s[i])) {
                    current += prev;
                }
                System.out.print(current + " ");
                prev = current;
            }

            System.out.println();
            t--;
        }
    }
}
