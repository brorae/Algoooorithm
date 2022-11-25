package boj.p15651;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M + 1];
        isUsed = new boolean[N + 1];

        recursive(0);

        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static void recursive(int i) {
        if (i == M) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[j] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int j = 1; j <= N; j++) {
            arr[i] = j;
            recursive(i + 1);
        }
    }
}
