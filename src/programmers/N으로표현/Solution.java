package programmers.N으로표현;

import java.util.*;

class Solution {

    static List<Set<Integer>> numbers;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(5, 12);
    }

    public int solution(int N, int number) {
        numbers = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            numbers.add(new HashSet<>());
        }

        numbers.get(1).add(N);

        numbers.get(2).add(N - N);
        numbers.get(2).add(N + N);
        numbers.get(2).add(N * N);
        numbers.get(2).add(N / N);
        numbers.get(2).add(Integer.parseInt(String.valueOf(N).repeat(2)));

        for (int i = 3; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                Set<Integer> a = numbers.get(j);
                Set<Integer> b = numbers.get(i - j);
                for (Integer num1 : a) {
                    for (Integer num2 : b) {
                        int plus = num1 + num2;
                        int minus = num1 - num2;
                        int mul = num1 * num2;
                        numbers.get(i).add(plus);
                        numbers.get(i).add(minus);
                        numbers.get(i).add(mul);
                        if (num2 != 0) {
                            int div = num1 / num2;
                            numbers.get(i).add(div);
                        }
                    }
                }
            }
            numbers.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }

        int answer = -1;
        for (Set<Integer> integers : numbers) {
            if (integers.contains(number)) {
                answer = numbers.indexOf(integers);
                break;
            }
        }
        return answer;
    }
}

