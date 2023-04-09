package programmers.줄서는방법;

import java.util.*;

class Solution {

    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();

        int idx = 0;
        long fac = 1;
        for (int i=1;i<=n;i++) {
            fac *= i;
            list.add(i);
        }

        k--;

        while(idx < n) {
            fac /= n - idx;
            int value = (int) (k / fac);

            answer[idx++] = list.remove(value);

            k %= fac;
        }

        return answer;
    }
}
