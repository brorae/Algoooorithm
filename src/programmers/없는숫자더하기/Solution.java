package programmers.없는숫자더하기;

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public static int solution(int[] numbers) {
        return IntStream.rangeClosed(0,9)
                .filter(i -> Arrays.stream(numbers).noneMatch(num -> i==num))
                .sum();
    }
}