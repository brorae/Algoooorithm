package boj.p15683;

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
    static int[][] board;
    static List<CCTV> cctvs = new ArrayList<>();
    static List<List<int[]>> directionsByNumber = new ArrayList<>();

    static int[] dx = {0, 0, 1, -1}; // 0:동 1:서 2:남 3:북
    static int[] dy = {1, -1, 0, 0};

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p15683/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0 && board[i][j] != 6) {
                    cctvs.add(new CCTV(board[i][j], i, j));
                }
            }
        }
        init();

        if (!cctvs.isEmpty()) {
            CCTV first = cctvs.get(0);
            List<int[]> directions = directionsByNumber.get(first.num);
            for (int[] direction : directions) {
                dfs(0, direction);
            }
        } else {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) count++;
                }
            }
            result = count;
        }

        if (result == Integer.MAX_VALUE) {
            bw.write("0");
        } else {
            bw.write(result+"");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int index, int[] directions) {
        int[][] tmpBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpBoard[i][j] = board[i][j];
            }
        }

        calculate(index, directions);
        if (index == cctvs.size() - 1) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) count++;
                }
            }
            result = Math.min(result, count);
        } else {
            for (int i = index + 1; i < cctvs.size(); i++) {
                CCTV next = cctvs.get(i);
                List<int[]> nextDirections = directionsByNumber.get(next.num);
                for (int[] nextDirection : nextDirections) {
                    dfs(i, nextDirection);
                }
            }
        }
        board = tmpBoard;
    }

    static void calculate(int index, int[] directions) {
        CCTV cctv = cctvs.get(index);
        Queue<Point> q = new LinkedList<>();
        for (int direction : directions) {
            q.add(new Point(cctv.x, cctv.y, direction));
        }

        while (!q.isEmpty()) {
            Point now = q.poll();
            int nx = now.x + dx[now.direction];
            int ny = now.y + dy[now.direction];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            if (board[nx][ny] != 6) {
                if (board[nx][ny] == 0) {
                    board[nx][ny] = -1;
                    q.add(new Point(nx, ny, now.direction));
                } else {
                    q.add(new Point(nx, ny, now.direction));
                }
            }
        }
    }

    static void init() {
        for (int i = 0; i < 6; i++) {
            directionsByNumber.add(new ArrayList<>());
        }

        directionsByNumber.get(1).add(new int[]{0});
        directionsByNumber.get(1).add(new int[]{1});
        directionsByNumber.get(1).add(new int[]{2});
        directionsByNumber.get(1).add(new int[]{3});

        directionsByNumber.get(2).add(new int[]{0, 1});
        directionsByNumber.get(2).add(new int[]{2, 3});

        directionsByNumber.get(3).add(new int[]{0, 3});
        directionsByNumber.get(3).add(new int[]{3, 1});
        directionsByNumber.get(3).add(new int[]{1, 2});
        directionsByNumber.get(3).add(new int[]{2, 0});

        directionsByNumber.get(4).add(new int[]{0, 3, 1});
        directionsByNumber.get(4).add(new int[]{3, 1, 2});
        directionsByNumber.get(4).add(new int[]{1, 2, 0});
        directionsByNumber.get(4).add(new int[]{2, 0, 3});

        directionsByNumber.get(5).add(new int[]{0, 1, 2, 3});
    }
}

class CCTV {
    int num;
    int x;
    int y;

    public CCTV(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Point {
    int x;
    int y;
    int direction;

    public Point(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
