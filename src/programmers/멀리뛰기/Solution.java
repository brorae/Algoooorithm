package programmers.멀리뛰기;

import java.util.HashMap;
import java.util.Map;

class Solution {

    static long[] dp;

    public long solution(int n) {
        dp = new long[n+2];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<n+1;i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer value : map.values()) {

        }
        return dp[n];
    }


}
