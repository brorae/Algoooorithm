package boj.p15649;

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
    static StringBuilder sb =  new StringBuilder();

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

    private static void recursive(int a) {
        if (a == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isUsed[i]) {
                arr[a] = i;
                isUsed[i] = true;
                recursive(a + 1);
                isUsed[i] = false;
            }
        }
    }
}
