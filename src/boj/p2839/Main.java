package boj.p2839;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        for (int i = 3; i <= N; i += 3) {
            dp[i] = i / 3;
        }

        for (int i = 5; i <= N; i += 5) {
            dp[i] = i / 5;
        }

        for (int i = 7; i <= N; i++) {
            if (dp[i - 3] != 0 && dp[i - 5] != 0) {
                dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
            }
        }

        if (dp[N] == 0) {
            bw.write("-1");
        } else {
            bw.write(dp[N] + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
