package programmers.튜플;

import java.util.*;

class Solution {

    static List<List<Integer>> tuples;
    static boolean[] visited = new boolean[100001];

    public static int[] solution(String s) {
        tuples = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();

        String replace = s.replace("},{", "/").replace("{{", "").replace("}}", "");
        String[] words = replace.split("/");
        for (String word : words) {
            String[] numbers = word.split(",");
            List<Integer> list = new ArrayList<>();
            for (String number : numbers) {
                list.add(Integer.parseInt(number));
            }
            tuples.add(list);
        }
        Collections.sort(tuples, ((o1, o2) -> o1.size() - o2.size()));
        for (List<Integer> tuple : tuples) {
            for (Integer integer : tuple) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    answer.add(integer);
                    break;
                }
            }
        }

        return answer.stream().mapToInt(it -> it).toArray();
    }
}





