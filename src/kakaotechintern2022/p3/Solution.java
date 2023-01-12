package kakaotechintern2022.p3;

class Solution {

    static int[][] dp = new int[152][152];

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = problems[0][0];
        int maxCop = problems[0][1];
        for (int i = 1; i < problems.length; i++) {
            if (maxAlp < problems[i][0]) {
                maxAlp = problems[i][0];
            }
            if (maxCop < problems[i][1]) {
                maxCop = problems[i][1];
            }
        }

        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        if (alp >= maxAlp) {
            alp = maxAlp;
        }
        if (cop >= maxCop) {
            cop = maxCop;
        }

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] p : problems) {
                    if (i >= p[0] && j >= p[1]) {
                        if (i + p[2] > maxAlp && j + p[3] > maxCop) {
                            dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j] + p[4]);
                        } else if (i + p[2] > maxAlp) {
                            dp[maxAlp][j + p[3]] = Math.min(dp[maxAlp][j + p[3]], dp[i][j] + p[4]);
                        } else if (j + p[3] > maxCop) {
                            dp[i + p[2]][maxCop] = Math.min(dp[i + p[2]][maxCop], dp[i][j] + p[4]);
                        } else if (i + p[2] <= maxAlp && j + p[3] <= maxCop) {
                            dp[i + p[2]][j + p[3]] = Math.min(dp[i + p[2]][j + p[3]], dp[i][j] + p[4]);
                        }
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
