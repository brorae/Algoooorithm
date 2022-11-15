package boj.p2178;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                arr[i+1][j + 1] = str.charAt(j) - '0';
            }
        }

        bfs(1, 1);

        int result = arr[n][m];
        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        while (!q.isEmpty()) {
            Point point = q.poll();
            visited[point.x][point.y] = true;
            List<Point> points = point.getPoints();
            for (Point point1 : points) {
                if (arr[point1.x][point1.y] == 1 && !visited[point1.x][point1.y]) {
                    arr[point1.x][point1.y] = arr[point.x][point.y] + 1;
                    visited[point1.x][point1.y] = true;
                    q.add(point1);
                }
            }
        }
    }

    static class Point {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Point> getPoints() {
            List<Point> list = new ArrayList<>();
            for (int i = 0; i < dx.length; i++) {
                if (x + dx[i] > 0 && y + dy[i] > 0 && x + dx[i] <= n && y + dy[i] <= m && !visited[x+dx[i]][y+dy[i]]) {
                    list.add(new Point(x+dx[i], y+dy[i]));
                }
            }
            return list;
        }
    }
}
