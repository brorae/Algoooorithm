package kakao2023.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        int todayYear = Integer.parseInt(today.substring(0, 4));
        int todayMonth = Integer.parseInt(today.substring(5, 7));
        int todayDay = Integer.parseInt(today.substring(8));
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] kindOfTerm = term.split(" ");
            map.put(kindOfTerm[0], Integer.parseInt(kindOfTerm[1]));
        }
        for (int i = 0; i < privacies.length; i++) {
            String[] inform = privacies[i].split(" ");
            Integer plus = map.get(inform[1]);
            String now = inform[0];
            int year = Integer.parseInt(now.substring(0, 4));
            int month = Integer.parseInt(now.substring(5, 7)) + plus;
            int day = Integer.parseInt(now.substring(8));
            if (month > 12) {
                if (month % 12 != 0) {
                    year += month / 12;
                    month %= 12;
                } else {
                    year += month / 12 - 1;
                    month = 12;
                }
            }

            if (year < todayYear) {
                answer.add(i + 1);
                continue;
            }
            if (year > todayYear) {
                continue;
            }
            if (month < todayMonth) {
                answer.add(i + 1);
                continue;
            }
            if (month > todayMonth) {
                continue;
            }
            if (day <= todayDay) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"},
                new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})));
    }
}
