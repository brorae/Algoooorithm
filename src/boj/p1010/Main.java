package boj.p1010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int max = 0;
        int T = Integer.parseInt(br.readLine());
        arr = new int[T][2];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{e, w};
            max = Math.max(max, e);
            max = Math.max(max, w);
        }

        dp = new int[max + 1][max + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= max; i++) {
            for (int j = 0; j <= max; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        for (int[] ints : arr) {
            int e = ints[0];
            int w = ints[1];
            int maxCount = Math.max(e, w);
            int minCount = Math.min(e, w);
            bw.write(dp[maxCount][minCount] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
