package programmers.캐시;

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> cache = new LinkedList<>();
        int time = 0;

        for (String city : cities) {
            boolean isContained = cache.contains(city.toLowerCase());
            if (!isContained) {
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                if (cache.size() < cacheSize) {
                    cache.add(city.toLowerCase());
                }
                time += 5;
            } else if (isContained) {
                cache.remove(city.toLowerCase());
                cache.add(city.toLowerCase());
                time += 1;
            }
        }
        return time;
    }
}
