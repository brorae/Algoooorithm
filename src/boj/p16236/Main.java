package boj.p16236;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int babySharkSize = 2;
    static Fish babyShark;
    static int eatingCount = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p16236/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num != 0) {
                    if (num == 9) {
                        babyShark = new Fish(i, j, 9, 0);
                    }
                }
            }
        }

        int result = 0;
        while (true) {
            PriorityQueue<Fish> eatable = bfs(babyShark);
            if (eatable.isEmpty()) {
                break;
            }
            board[babyShark.x][babyShark.y] = 0;
            Fish eating = eatable.poll();
            board[eating.x][eating.y] = 0;
            babyShark = new Fish(eating.x, eating.y, 9, 0);
            eatingCount++;
            if (eatingCount == babySharkSize) {
                babySharkSize++;
                eatingCount = 0;
            }
            result += eating.time;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static PriorityQueue<Fish> bfs(Fish babyShark) {
        PriorityQueue<Fish> eatableFishes = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) {
                if (o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
            return o1.time - o2.time;
        });

        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(babyShark.x, babyShark.y));
        visited[babyShark.x][babyShark.y] = true;
        int time = 0;
        boolean stop = false;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int k = 0; k < len; k++) {
                Point now = q.poll();

                if (board[now.x][now.y] != 0 && board[now.x][now.y] != 9 && board[now.x][now.y] < babySharkSize) {
                    eatableFishes.add(new Fish(now.x, now.y, board[now.x][now.y], time));
                    stop = true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue;
                    }
                    if (!visited[nx][ny] && board[nx][ny] <= babySharkSize) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }
                }
            }
            if (stop) {
                break;
            }
            time++;
        }
        return eatableFishes;
    }
}


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Fish {
    int x;
    int y;
    int size;
    int time;
    boolean alive;

    public Fish(int x, int y, int size, int time) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.time = time;
        this.alive = true;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", time=" + time +
                ", alive=" + alive +
                '}';
    }
}
