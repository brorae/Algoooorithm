package programmers.완주하지못한선수;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        for (String name : completion) {
            map.put(name, map.get(name) - 1);
        }

        List<Entry<String, Integer>> collect = map.entrySet()
                .stream()
                .filter(it -> it.getValue() != 0)
                .collect(Collectors.toList());
        return collect.get(0).getKey();
    }
}
