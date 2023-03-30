package programmers.뉴스클러스터링;

import java.util.*;

class Solution {

    public static int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String s = str1.substring(i, i + 2);
            s = s.toLowerCase();
            char first = s.charAt(0);
            char second = s.charAt(1);

            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                map1.put(s, map1.getOrDefault(s, 0) + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String s = str2.substring(i, i + 2);
            s = s.toLowerCase();
            char first = s.charAt(0);
            char second = s.charAt(1);

            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                map2.put(s, map2.getOrDefault(s, 0) + 1);
            }
        }

        Map<String, Integer> newMap = new HashMap<>();

        if (map1.isEmpty() && map2.isEmpty()) {
            return 65536;
        } else {
            int gyo = 0;
            for (String key : map1.keySet()) {
                Integer first = map1.get(key);
                Integer second = map2.getOrDefault(key, 0);
                gyo += Math.min(first, second);

                newMap.put(key, map1.get(key));
            }

            for (String key : map2.keySet()) {
                int a = newMap.getOrDefault(key, 0);
                int b = map2.get(key);
                newMap.put(key, Math.max(a,b));
            }

            int hap = 0;
            for (Integer ss : newMap.values()) {
                hap += ss;
            }

            double result = 1.0 * gyo / hap;
            return (int) (result * 65536);
        }
    }
}
