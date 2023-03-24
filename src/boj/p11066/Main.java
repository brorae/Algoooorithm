package boj.p11066;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/boj/p11066/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[K];
            for (int j = 0; j < K; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            bw.write(solve(arr) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int sum(int[] arr, int s, int e) {
        if (s == 0) {
            return arr[e];
        } else {
            return arr[e] - arr[s - 1];
        }
    }

    public static int solve(int[] arr) {
        int dp[][] = new int[arr.length][arr.length];
        int s[] = new int[arr.length];
        s[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            s[i] = s[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            dp[i][i + 1] = arr[i] + arr[i + 1];
        }
        for (int gap = 2; gap < arr.length; gap++) {
            for (int i = 0; i + gap < arr.length; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][k] + dp[k + 1][j] + sum(s, i, j), dp[i][j]);
                }
            }
        }
        return dp[0][arr.length - 1];
    }
}
