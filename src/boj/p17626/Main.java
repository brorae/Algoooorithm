package boj.p17626;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p17626/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        int num = 1;
        while (num * num <= N) {
            dp[num * num] = 1;
            num++;
        }

        for (int i = 2; i < N + 1; i++) {
            if (dp[i] == 0) {
                for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                    if (dp[i] == 0) {
                        dp[i] = dp[j * j] + dp[i - j * j];
                    } else {
                        dp[i] = Math.min(dp[i], dp[j * j] + dp[i - j * j]);
                    }
                }
            }
        }
        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
