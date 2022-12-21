package boj.p14503;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] backX = {1, 0, -1, 0};
    static int[] backY = {0, -1, 0, 1};
    static int[] dir = {3, 0, 1, 2};

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[r][c] = true;
        result++;
        dfs(r, c, d);

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static void dfs(int x, int y, int d) {
        int nextX = x + dx[d];
        int nextY = y + dy[d];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || visited[nx][ny] || arr[nx][ny] == 1) {
                count++;
            }
        }
        if (count == 4) {
            if (arr[x + backX[d]][y + backY[d]] == 1) {
                return;
            }
            dfs(x + backX[d], y + backY[d], d);
        }

        else if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
            if (arr[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                result++;
                dfs(nextX, nextY, dir[d]);
            }
            else if (arr[nextX][nextY] == 1 || visited[nextX][nextY]) {
                dfs(x, y, dir[d]);
            }
        }
        else if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
            dfs(x, y, dir[d]);
        }
    }
}
