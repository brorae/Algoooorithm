package boj.p9252;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p9252/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String first = br.readLine();
        String second = br.readLine();
        int firstLength = first.length();
        int secondLength = second.length();
        dp = new int[firstLength + 1][secondLength + 1];

        for (int i = 1; i <= firstLength; i++) {
            for (int j = 1; j <= secondLength; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int resultLength = dp[firstLength][secondLength];
        int i = firstLength;
        int j = secondLength;
        while (i != 0 && j != 0) {
            int up = dp[i - 1][j];
            int left = dp[i][j - 1];
            if (dp[i][j] == up) {
                i--;
            } else if (dp[i][j] == left) {
                j--;
            } else {
                sb.append(first.charAt(i - 1));
                i--;
                j--;
            }
        }
        bw.write(resultLength + "\n" + sb.reverse());
        bw.flush();
        bw.close();
        br.close();
    }
}
