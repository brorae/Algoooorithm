package boj.p2468;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<Position> q;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int result = Integer.MIN_VALUE;
    static int areaCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        q = new LinkedList<>();
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, arr[i][j]);
            }
        }

        for (int i = 0; i <= maxHeight; i++) {
            areaCount = 0;
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (arr[j][k] > i && !visited[j][k]) {
                        bfs(j, k, i);
                    }
                }
            }
            result = Math.max(result, areaCount);
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static void bfs(int x, int y, int height) {
        q.add(new Position(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()) {
            Position now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                    continue;
                }
                if (arr[nextX][nextY] > height && !visited[nextX][nextY]) {
                    q.add(new Position(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
        areaCount++;
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
