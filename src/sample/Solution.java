package sample;

import java.util.*;

public class Solution {
    public boolean canReach(int[] arr, int start) {
        int length = arr.length;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> indexQueue = new LinkedList<>();

        indexQueue.offer(start);
        visited.add(start);

        while (!indexQueue.isEmpty()) {
            int index = indexQueue.poll();
            int value = arr[index];

            if (value == 0) {
                return true;
            }

            if (index + value < length && !visited.contains(index + value)) {
                indexQueue.offer(index + value);
            }

            if (index - value >= 0 && !visited.contains(index - value)) {
                indexQueue.offer(index - value);
            }
        }

        return false;
    }
}

class Codec {

    private static Map<String, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (true) {
            String randomString = UUID.randomUUID().toString().substring(0, 6);

            if (!map.containsKey(randomString)) {
                String tinyURL = "https://tinyURL/" + randomString;
                map.put(tinyURL, longUrl);
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}