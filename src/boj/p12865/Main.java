package boj.p12865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static int[] weight;
    static int[] value;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p12865/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];
        weight = new int[N + 1];
        value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }


        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j - weight[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
