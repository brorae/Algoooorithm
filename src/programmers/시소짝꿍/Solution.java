package programmers.시소짝꿍;

import java.util.*;

class Solution {
    public long solution(int[] weights) {
        int[] w = new int[1001];
        Arrays.sort(weights);

        long count = 0;
        for (int i=weights.length-1;i>=0;i--) {
            int weight = weights[i];
            if (w[weight] >= 1) {
                count += w[weight];
            }
            if (weight * 3 % 2 == 0) {
                int value = weight * 3 / 2;
                if (value <= 1000 && w[value] >= 1) count += w[value];
            }
            if (weight * 4 % 3 == 0) {
                int value = weight * 4 / 3;
                if (value <= 1000 && w[value] >= 1) count += w[value];
            }
            if (weight * 2 <= 1000 && w[weight * 2] >= 1) count+= w[weight * 2];
            w[weight]++;
        }

        return count;
    }
}
