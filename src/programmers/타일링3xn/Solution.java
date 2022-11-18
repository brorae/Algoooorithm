package programmers.타일링3xn;

class Solution {

    static long[] dp = new long[5001];
    static int MOD = 1000000007;

    public int solution(int n) {
        dp[0] = 1;
        dp[2] = 3;
        dp[4] = 11;
        for (int i = 6; i < 5001; i += 2) {
            dp[i] = dp[i - 2] * dp[2];
            for (int j = 0; j <= i - 4; j += 2) {
                dp[i] += 2 * dp[j];
            }
            dp[i] %= MOD;
        }

        return (int) dp[n];
    }
}
