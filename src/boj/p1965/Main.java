package boj.p1965;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int result = Arrays.stream(dp).max().getAsInt();
        bw.write(result + "");
        bw.flush();

        br.close();
        bw.close();
    }
}
