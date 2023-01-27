package boj.p1256;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 1000000000;
    static int N, M, K;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + M + 1][N + M + 1];

        for (int i = 0; i < N + M + 1; i++) {
            for (int j = 0; j < N + M + 1; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                }
                if (i == j) {
                    dp[i][j] = 1;
                }
            }
        }

        for (int i = 2; i < N + M + 1; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i - 1][j - 1] + dp[i - 1][j] > MAX) {
                    dp[i][j] = MAX;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        int n = N + M;
        int k = M;
        if (K > dp[n][k]) {
            bw.write("-1");
        } else {
            find(n, k, K);
            bw.write(sb.toString());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static void find(int n, int k, int index) {
        if (index == 0 || n < 1 || k < 0) {
            return;
        }
        if (index <= dp[n - 1][k]) {
            sb.append('a');
            find(n - 1, k, index);
        } else {
            sb.append('z');
            find(n - 1, k - 1, index - dp[n - 1][k]);
        }
    }
}
