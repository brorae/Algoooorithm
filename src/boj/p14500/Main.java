package boj.p14500;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int max = Integer.MIN_VALUE;
    static int[][] arr;
    static boolean[][] visit;
    static int N;
    static int M;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, arr[i][j], 1);
                visit[i][j] = false;
            }
        }

        bw.write(max + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static void dfs(int row, int col, int sum, int count) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int curRow = row + dx[i];
            int curCol = col + dy[i];

            if (curRow < 0 || curRow >= N || curCol < 0 || curCol >= M) {
                continue;
            }

            if (!visit[curRow][curCol]) {

                if (count == 2) {
                    visit[curRow][curCol] = true;
                    dfs(row, col, sum + arr[curRow][curCol], count + 1);
                    visit[curRow][curCol] = false;
                }

                visit[curRow][curCol] = true;
                dfs(curRow, curCol, sum + arr[curRow][curCol], count + 1);
                visit[curRow][curCol] = false;
            }
        }
    }
}
