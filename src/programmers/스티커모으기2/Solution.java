package programmers.스티커모으기2;

class Solution {
    public int solution(int sticker[]) {
        int max = 0;
        int len = sticker.length;

        if (len == 1) return sticker[0];

        // 0 -> 첫번째 선택 , 1 -> 두번째 선택
        int[][] dp = new int[2][len];


        dp[0][0] = sticker[0];
        dp[0][1] = sticker[0];
        for (int i=2;i<len-1;i++) {
            dp[0][i] = Math.max(dp[0][i-1], dp[0][i-2] + sticker[i]);
        }

        dp[1][0] = 0;
        dp[1][1] = sticker[1];
        for (int i=2;i<len;i++) {
            dp[1][i] = Math.max(dp[1][i-1], dp[1][i-2] + sticker[i]);
        }

        return Math.max(dp[0][len-2], dp[1][len-1]);
    }
}
