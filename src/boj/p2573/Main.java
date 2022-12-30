package boj.p2573;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] tmp;
    static boolean[][] visited;
    static Queue<Position> q;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int countAfterMelt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        tmp = new int[N][M];
        visited = new boolean[N][M];
        q = new LinkedList<>();

        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                tmp[i][j] = arr[i][j];
                if (x == 0 && y == 0 && arr[i][j] != 0) {
                    x = i;
                    y = j;
                }
            }
        }

        int year = 0;
        while (true) {
            bfs(x, y);
            year++;
            countAfterMelt = 0;
            visited = new boolean[N][M];
            int startX = 0;
            int startY = 0;
            int countAll = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = tmp[i][j];
                    if (arr[i][j] != 0) {
                        countAll++;
                        if (startX == 0 && startY == 0) {
                            startX = i;
                            startY = j;
                        }
                    }
                }
            }
            if (countAll == 0) {
                bw.write("0");
                break;
            }

            dfs(startX, startY);

            if (countAll != countAfterMelt) {
                bw.write(year + "");
                break;
            }
            visited = new boolean[N][M];
            x = startX;
            y = startY;
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        countAfterMelt++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            if (arr[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    static void bfs(int x, int y) {
        q.add(new Position(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Position now = q.poll();
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (arr[nx][ny] == 0) {
                    count++;
                } else if (arr[nx][ny] != 0 && !visited[nx][ny]) {
                    q.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            tmp[now.x][now.y] -= count;
            if (tmp[now.x][now.y] < 0) {
                tmp[now.x][now.y] = 0;
            }
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
