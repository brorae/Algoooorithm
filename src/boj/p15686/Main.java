package boj.p15686;

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
    static List<Point> chickenHouses;

    static List<Integer> c = new ArrayList<>();
    static List<List<Integer>> cResult = new ArrayList<>();
    static boolean[][] willClose;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        chickenHouses = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num == 2) {
                    chickenHouses.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < chickenHouses.size(); i++) {
            combination(i);
        }

        int result = Integer.MAX_VALUE;

        for (List<Integer> integers : cResult) {
            willClose = new boolean[N+1][N+1];
            int chickenDistance = 0;
            for (Integer integer : integers) {
                Point point = chickenHouses.get(integer);
                willClose[point.x][point.y] = true;
            }
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N ; j++) {
                    if (board[i][j] == 1) {
                        int distance = bfs(i, j);
                        chickenDistance += distance;
                    }
                }
            }
            result = Math.min(result, chickenDistance);
        }

        if (cResult.size() == 0) {
            willClose = new boolean[N+1][N+1];
            int chickenDistance = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N ; j++) {
                    if (board[i][j] == 1) {
                        int distance = bfs(i, j);
                        chickenDistance += distance;
                    }
                }
            }
            result = Math.min(result, chickenDistance);
        }

        bw.write(result+"");
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int x, int y) {
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<Point> q = new LinkedList<>();
        int distance = 0;
        visited[x][y] = true;
        q.add(new Point(x,y));
        while(!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Point now = q.poll();
                if (board[now.x][now.y] == 2 && !willClose[now.x][now.y]) {
                    return distance;
                }
                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (nx < 0 || ny < 0 || nx > N || ny > N) continue;
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    static void combination(int index) {
        c.add(index);
        if (c.size() == chickenHouses.size() - M) {
            cResult.add(new ArrayList<>(c));
        }
        for (int i = index + 1; i < chickenHouses.size(); i++) {
            combination(i);
        }
        c.remove(c.size() - 1);
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
