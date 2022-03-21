package programmers.신고결과받기;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        List<String> pause = new ArrayList<>();
        LinkedHashMap<String, Integer> reported = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> reporting = new LinkedHashMap<>();

        for (String id : id_list) {
            reported.put(id, 0);
            reporting.put(id, new ArrayList<>());
        }

        report = Arrays.stream(report)
                .distinct()
                .toArray(String[]::new);

        List<String> reportResult = Arrays.stream(report)
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .collect(Collectors.toList());

        for (int i = 0; i < reportResult.size(); i++) {
            if (i % 2 == 1) {
                String name = reportResult.get(i);
                reported.put(name, reported.get(name) + 1);
                if (reported.get(name) == k) {
                    pause.add(name);
                }
            } else {
                List<String> list = reporting.get(reportResult.get(i));
                list.add(reportResult.get(i + 1));
                reporting.put(reportResult.get(i), list);
            }
        }
        int i = 0;
        for (String strKey : reporting.keySet()) {
            for (String name : pause) {
                if (reporting.get(strKey).contains(name)) {
                    answer[i]++;
                }
            }
            i++;
        }
        return answer;
    }
}