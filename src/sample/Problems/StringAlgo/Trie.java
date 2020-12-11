package sample.Problems.StringAlgo;

import java.util.*;
import java.lang.*;

class Trie {
    boolean arr[] = new boolean[26];
    Trie next;
    boolean isEOI[] = new boolean[26];
}

class GFG
{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while(t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            String data = scan.nextLine();

            Trie trie = insert(data);

            String patt = scan.nextLine();
            for(int i=0; i<patt.length(); i++) {
                if(!trie.arr[patt.charAt(i) - 'a']) {
                    System.out.println("0");
                    break;
                }
                if(i==patt.length()-1 && trie.isEOI[patt.charAt(i) - 'a']) {
                    System.out.println("1");
                } else if(i==patt.length()-1 && !trie.isEOI[patt.charAt(i) - 'a']) {
                    System.out.println("0");
                }
                trie = trie.next;
            }
            t--;
        }
    }

    private static Trie insert(String s) {
        String arr[] = s.split(" ");

        Trie node = new Trie();
        Trie start = node;
        for(String val : arr) {
            for(int i = 0; i < val.length(); i++) {
                if(i == 0) {
                    node.arr[val.charAt(i) - 'a'] = true;
                } else {
                    if(node.next == null) {
                        Trie newNode = new Trie();
                        node.next = newNode;
                    }
                    node = node.next;
                    node.arr[val.charAt(i) - 'a'] = true;
                }
            }
            node.isEOI[val.charAt(val.length() -1) - 'a'] = true;
            node = start;
        }

        return start;
    }
}

