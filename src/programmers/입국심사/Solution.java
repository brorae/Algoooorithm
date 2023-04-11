package programmers.입국심사;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution.solution(6, new int[]{7, 10});
    }

    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long shortest = times[0];
        long longest = times[times.length - 1];
        long l = shortest;
        long r;
        if (shortest * n > longest) {
            r = shortest * n;
        } else {
            r = longest;
        }
        while (r -l > 1) {
            long sum = (l + r) / 2;
            long count = 0;
            for (int i = 0; i < times.length; i++) {
                int time = times[i];
                count += sum / time;
            }
            if (count >= n) {
                answer = sum;
                r = sum;
            } else if (count < n) {
                l = sum;
            }
        }

        return answer;
    }
}
