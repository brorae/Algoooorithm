package boj.p23288;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] board;
    static int[] dice = {1, 6, 3, 4, 2, 5}; // 위 아래 오 왼 북 남
    static int[] dx = {0, -1, 0, 1}; // 동 북 서 남
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p23288/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        int direction = 0; // 0:동 1:북 2:서 3:남
        int startX = 1;
        int startY = 1;
        for (int i = 0; i < K; i++) {
            int tmpX = startX + dx[direction];
            int tmpY = startY + dy[direction];
            if (tmpX <= 0 || tmpY <= 0 || tmpX > N || tmpY > M) {
                direction = (direction + 2) % 4;
                startX += dx[direction];
                startY += dy[direction];
            } else {
                startX = tmpX;
                startY = tmpY;
            }
            int num = turnDice(direction);
            int value = board[startX][startY];
            int count = bfs(startX, startY, value);
            sum += value * count;
            if (num > value) {
                // 90도 시계 방향 회전
                direction = (direction - 1) % 4;
                if (direction < 0) {
                    direction = 4 + direction;
                }
            } else if (num < value) {
                // 90도 반시계 방향 회전
                direction = (direction + 1) % 4;
            }
        }
        bw.write(sum + "");
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y, int value) {
        int count = 1;
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Point(x, y));
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx <= 0 || ny <= 0 || nx > N || ny > M) {
                    continue;
                }

                if (!visited[nx][ny] && board[nx][ny] == value) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    count++;
                }
            }
        }
        return count;
    }

    static int turnDice(int direction) {
        int[] tmp = new int[6];
        if (direction == 0) { // 동
            tmp[0] = dice[3];
            tmp[1] = dice[2];
            tmp[2] = dice[0];
            tmp[3] = dice[1];
            tmp[4] = dice[4];
            tmp[5] = dice[5];
        } else if (direction == 1) { // 북
            tmp[0] = dice[5];
            tmp[1] = dice[4];
            tmp[2] = dice[2];
            tmp[3] = dice[3];
            tmp[4] = dice[0];
            tmp[5] = dice[1];

        } else if (direction == 2) { // 서
            tmp[0] = dice[2];
            tmp[1] = dice[3];
            tmp[2] = dice[1];
            tmp[3] = dice[0];
            tmp[4] = dice[4];
            tmp[5] = dice[5];
        } else { // 남
            tmp[0] = dice[4];
            tmp[1] = dice[5];
            tmp[2] = dice[2];
            tmp[3] = dice[3];
            tmp[4] = dice[1];
            tmp[5] = dice[0];
        }
        dice = tmp;
        return dice[1];
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
