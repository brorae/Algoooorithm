package boj.p4485;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int num = 1;
    static int N;
    static int[][] board;
    static PriorityQueue<Score> pq;
    static int[][] score;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p4485/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            board = new int[N][N];
            pq = new PriorityQueue<>((o1, o2) -> o1.score - o2.score);
            score = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    score[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra(0, 0);
            sb.append("Problem ")
                    .append(num++)
                    .append(": ")
                    .append(score[N - 1][N - 1])
                    .append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int x, int y) {
        score[x][y] = board[x][y];
        pq.add(new Score(0, x, y));
        loop:
        while (!pq.isEmpty()) {
            Score now = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (score[nx][ny] > score[now.x][now.y] + board[nx][ny]) {
                    score[nx][ny] = score[now.x][now.y] + board[nx][ny];
                    if (nx == N - 1 && ny == N - 1) {
                        break loop;
                    }
                    pq.add(new Score(score[nx][ny], nx, ny));
                }
            }
        }
    }
}

class Score {
    int score;
    int x;
    int y;

    public Score(int score, int x, int y) {
        this.score = score;
        this.x = x;
        this.y = y;
    }
}
