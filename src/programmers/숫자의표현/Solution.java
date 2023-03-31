package programmers.숫자의표현;

class Solution {

    public int solution(int n) {
        int num = 1;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + num++;
        }

        int result = 0;
        int pointerA = 0;
        int pointerB = 1;
        while (pointerB < sums.length) {
            if (sums[pointerB] - sums[pointerA] == n) {
                result++;
                pointerA++;
                pointerB++;
            } else if (sums[pointerB] - sums[pointerA] < n) {
                pointerB++;
            } else {
                pointerA++;
            }
        }

        return result;
    }
}
