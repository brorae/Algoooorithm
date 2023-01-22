package boj.p7579;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[] mem;
    static int[] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p7579/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        mem = new int[N + 1];
        cost = new int[N + 1];
        dp = new int[N + 1][10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 10000; j++) {
                if (j < cost[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], mem[i] + dp[i - 1][j - cost[i]]);
                }
            }
        }

        for (int i = 0; i <= 10000; i++) {
            if (dp[N][i] >= M) {
                bw.write(String.valueOf(i));
                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();

        String a = "a";
        a.contains("abc");
    }
}
