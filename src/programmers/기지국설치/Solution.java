package programmers.기지국설치;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        for (int i = 0; i < stations.length; i++) {
            if (start > n) {
                break;
            }
            int first = stations[i] - w;
            int second = stations[i] + w;
            if (first <= start) {
                start = second + 1;
                continue;
            } else if (first > start) {
                if ((first - start) % ((w * 2) + 1) == 0) {
                    answer += (first - start) / ((w * 2) + 1);
                } else {
                    answer += (first - start) / ((w * 2) + 1) + 1;
                }
            }
            start = second + 1;
        }

        if (start <= n) {
            if ((n - start + 1) % ((w * 2) + 1) == 0) {
                answer += (n - start + 1) / ((w * 2) + 1);
            } else {
                answer += (n - start + 1) / ((w * 2) + 1) + 1;
            }
        }

        return answer;
    }
}
