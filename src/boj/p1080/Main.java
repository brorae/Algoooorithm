package boj.p1080;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N + 1][M + 1];
        int[][] B = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        if (N < 3 || M < 3) {
            if (equal(A,B,N,M)) {
                bw.write("0\n");
            } else {
                bw.write("-1\n");
            }
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    if (i + 3 <= N && j + 3 <= M) {
                        count++;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                A[i + k][j + l] = (A[i + k][j + l] + 1) % 2;
                            }
                        }
                    }
                }
            }
        }

        if (!equal(A, B, N, M)) {
            count = -1;
        }

        bw.write(count + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static boolean equal(int[][] A, int[][] B, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
