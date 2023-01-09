package kakaotechintern2022.p1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = getStringIntegerMap();
        int length = survey.length;
        for (int i = 0; i < length; i++) {
            if (choices[i] > 4) {
                map.put(survey[i].substring(1), map.get(survey[i].substring(1)) + choices[i] - 4);
            } else {
                map.put(survey[i].substring(0,1), map.get(survey[i].substring(0,1)) + 4-choices[i]);
            }
        }
        String answer = calculateResult(map);
        return answer;
    }

    private Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("R", 0);
        map.put("T", 0);
        map.put("C", 0);
        map.put("F", 0);
        map.put("M", 0);
        map.put("J", 0);
        map.put("A", 0);
        map.put("N", 0);
        return map;
    }

    private String calculateResult(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        if (map.get("R") >= map.get("T")) {
            sb.append("R");
        } else {
            sb.append("T");
        }
        if (map.get("C") >= map.get("F")) {
            sb.append("C");
        } else {
            sb.append("F");
        }
        if (map.get("J") >= map.get("M")) {
            sb.append("J");
        } else {
            sb.append("M");
        }
        if (map.get("A") >= map.get("N")) {
            sb.append("A");
        } else {
            sb.append("N");
        }
        return sb.toString();
    }
}
