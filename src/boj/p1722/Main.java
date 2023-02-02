package boj.p1722;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, P;
    static int[] K;
    static long[] dp = new long[21];
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= 20; i++) {
            dp[i] = dp[i - 1] * i;
        }

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        checked = new boolean[N + 1];

        if (P == 1) {
            K = new int[1];
            K[0] = Integer.parseInt(st.nextToken());
            find(N, K[0]);
            bw.write(sb.toString());
        } else {
            K = new int[N];
            for (int i = 0; i < N; i++) {
                K[i] = Integer.parseInt(st.nextToken());
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static StringBuilder sb = new StringBuilder();

    static void find(int count, int index) {
        int sum = 0;
        int j = 1;
        for (int i = 1; i <= count - 1; i++) {
            if (index <= j++ * dp[count - 1]) {
                sum += dp[count - 1];
                sb.append(i);
                checked[i] = true;
                if (i == 1) {
                    find(count - 1, index);
                } else {
                    find(count - 1, index - sum);
                }
                break;
            }
        }
    }
}
