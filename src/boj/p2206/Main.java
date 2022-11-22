package boj.p2206;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        boolean[][][] visited = new boolean[2][N][M];
        Queue<Position> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        q.add(new Position(0, 0, 1, false));
        visited[0][0][0] = true;
        visited[1][0][0] = true;
        while (!q.isEmpty()) {
            Position position = q.poll();

            if (position.x == N - 1 && position.y == M - 1) {
                bw.write(position.count + "");
                bw.flush();

                bw.close();
                br.close();
                return;
            }
            for (int i = 0; i < 4; i++) {
                int moveX = position.x + dx[i];
                int moveY = position.y + dy[i];
                if (moveX >= 0 && moveX < N && moveY >= 0 && moveY < M) {
                    if (arr[moveX][moveY] == 0) {
                        if (!position.pass && !visited[0][moveX][moveY]) {
                            q.add(new Position(moveX, moveY, position.count + 1, false));
                            visited[0][moveX][moveY] = true;
                        } else if (position.pass && !visited[1][moveX][moveY]) {
                            q.add(new Position(moveX, moveY, position.count + 1, true));
                            visited[1][moveX][moveY] = true;
                        }
                    } else if (arr[moveX][moveY] == 1) {
                        if (!position.pass) {
                            q.add(new Position(moveX, moveY, position.count + 1, true));
                            visited[1][moveX][moveY] = true;
                        }
                    }
                }
            }
        }

        bw.write("-1");
        bw.flush();

        bw.close();
        br.close();
    }

    static class Position {
        int x;
        int y;
        int count;
        boolean pass;

        public Position(int x, int y, int count, boolean pass) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.pass = pass;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", pass=" + pass +
                    '}';
        }
    }
}

//5 5
//01000
//01010
//01010
//01011
//00010

