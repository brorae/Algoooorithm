package boj.p14502;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Point> points;
    static int[][] board;
    static List<List<Integer>> combinations = new ArrayList<>();
    static List<Integer> list = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = -1;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p14502/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        points = new ArrayList<>();
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                points.add(new Point(i, j));
            }
        }

        for (int i = 0; i < points.size(); i++) {
            combination(i);
        }

        for (List<Integer> combination : combinations) {
            int[][] tmpBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tmpBoard[i][j] = board[i][j];
                }
            }
            visited = new boolean[N][M];

            int count = 0;
            for (Integer integer : combination) {
                int x = points.get(integer).x;
                int y = points.get(integer).y;
                if (tmpBoard[x][y] == 0) {
                    tmpBoard[x][y] = 1;
                    count++;
                }
            }

            if (count != 3) {
                continue;
            }

            int sum = bfs(tmpBoard);
            result = Math.max(result, sum);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int[][] tmpBoard) {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpBoard[i][j] == 2) {
                    visited[i][j] = true;
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (tmpBoard[nx][ny] == 0 && !visited[nx][ny]) {
                    tmpBoard[nx][ny] = 2;
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpBoard[i][j] == 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    static void combination(int index) {
        list.add(index);
        if (list.size() == 3) {
            ArrayList<Integer> e = new ArrayList<>(list);
            combinations.add(e);
        } else {
            for (int i = index + 1; i < points.size(); i++) {
                combination(i);
            }
        }
        list.remove(list.size() - 1);
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
