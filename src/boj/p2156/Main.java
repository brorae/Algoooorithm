package boj.p2156;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    static int n;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        setUp(br);

        for (int i = 1; i < n; i++) {
            if (i == 2) {
                dp[i] = Math.max(arr[i] + arr[i-1], arr[i] + arr[i-2]);
            } else if (i > 2) {
                for (int j = 3; j <= i; j++) {
                    dp[i] = Math.max(dp[i], arr[i] + arr[i - 1] + dp[i - j]);
                }
                for (int j = 2; j <= i; j++) {
                    dp[i] = Math.max(dp[i], arr[i] + dp[i - j]);
                }
            }
        }
        int result = Arrays.stream(dp).max().getAsInt();

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }

    private static void setUp(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        dp[1] = arr[0] + arr[1];
    }
}
