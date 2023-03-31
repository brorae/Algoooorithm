package programmers.피보나치수;

class Solution {

    public int solution(int n) {

        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i=2;i<n+1;i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
        }

        return dp[n];
    }
}
