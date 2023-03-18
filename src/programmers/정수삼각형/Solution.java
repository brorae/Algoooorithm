package programmers.정수삼각형;

public class Solution {

    static int[][] dp;

    public int solution(int[][] triangle) {
        int answer = -1;

        int tLength = triangle.length;
        dp = new int[tLength][tLength];
        dp[0][0] = triangle[0][0];
        int count = 2;
        for (int i = 1; i < tLength; i++) {
            for (int j = 0; j < count; j++) {
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j] + triangle[i][j], dp[i - 1][j - 1] + triangle[i][j]);
                } else {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                }
                answer = Math.max(answer, dp[i][j]);
            }
            count++;
        }
        return answer;
    }
}
