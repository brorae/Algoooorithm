package boj.p1103;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int result = Integer.MIN_VALUE;
    static boolean hasCycle;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1103/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        visited[0][0] = true;
        dfs(0, 0, 1);
        if (hasCycle) {
            bw.write("-1");
        } else {
            bw.write(result + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int x, int y, int count) {
        if (count > result) {
            result = count;
        }
        dp[x][y] = count;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * (board[x][y] - '0');
            int ny = y + dy[i] * (board[x][y] - '0');
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == 'H') {
                continue;
            }
            if (visited[nx][ny]) {
                hasCycle = true;
                return;
            }
            if (dp[nx][ny] > count) {
                continue;
            }
            visited[nx][ny] = true;
            count++;
            dfs(nx, ny, count);
            count--;
            visited[nx][ny] = false;
        }
    }
}
