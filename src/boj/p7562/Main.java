package boj.p7562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

    static int[][] arr = new int[301][301];
    static boolean[][] check = new boolean[301][301];
    static Queue<Position> q = new LinkedList<>();
    static int l, startX, startY, moveX, moveY, count = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            for (int[] ints : arr) {
                Arrays.fill(ints, 0);
            }
            for (boolean[] booleans : check) {
                Arrays.fill(booleans, false);
            }
            q.clear();

            l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            moveX = Integer.parseInt(st.nextToken());
            moveY = Integer.parseInt(st.nextToken());

            q.add(new Position(startX, startY));
            bfs();
        }
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static void bfs() {
        arr[moveX][moveY] = -1;
        check[startX][startY] = true;
        while (!q.isEmpty()) {
            Position first = q.poll();
            for (int i = 0; i < 8; i++) {
                int x = first.x + dx[i];
                int y = first.y + dy[i];
                if (x < 0 || x >= l || y < 0 || y >= l) {
                    continue;
                }
                if (!check[x][y]) {
                    if (arr[x][y] == -1) {
                        sb.append(arr[first.x][first.y] + 1 + "\n");
                        return;
                    }
                    q.add(new Position(x, y));
                    arr[x][y] = arr[first.x][first.y] + 1;
                    check[x][y] = true;
                }
            }
        }
        sb.append("0\n");
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
