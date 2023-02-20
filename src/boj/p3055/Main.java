package boj.p3055;

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

    static int R, C;
    static char[][] arr;
    static int[][] dp;
    static boolean[][] visited;
    static Queue<Position> q = new LinkedList<>();
    static int result = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        dp = new int[R][C];
        visited = new boolean[R][C];

        List<Position> waters = new ArrayList<>();
        Position dochi = null;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = line.charAt(j);
                if (input == '*') {
                    waters.add(new Position(i, j, input));
                } else if (input == 'S') {
                    dochi = new Position(i, j, input);
                }
                arr[i][j] = input;
            }
        }
        q.addAll(waters);
        for (Position water : waters) {
            visited[water.x][water.y] = true;
        }
        q.add(dochi);
        visited[dochi.x][dochi.y] = true;

        bfs();

        if (result == 0) {
            bw.write("KAKTUS");
        } else {
            bw.write(result + "");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static void bfs() {
        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Position now = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || arr[nx][ny] == 'X') {
                        continue;
                    }
                    if (now.object == '*') {
                        if (!visited[nx][ny]) {
                            if (arr[nx][ny] == 'D' || arr[nx][ny] == '*') {
                                continue;
                            }
                            if (arr[nx][ny] == '.') {
                                arr[nx][ny] = '*';
                                q.add(new Position(nx, ny, now.object));
                                visited[nx][ny] = true;
                            }
                        }
                    } else if (now.object == 'S') {
                        if (!visited[nx][ny]) {
                            if (arr[nx][ny] == '.') {
                                q.add(new Position(nx, ny, now.object));
                                dp[nx][ny] = time;
                                visited[nx][ny] = true;
                            } else if (arr[nx][ny] == 'D') {
                                result = time;
                                return;
                            }
                        }
                    }
                }
            }
            time++;
        }
    }

    static class Position {
        int x;
        int y;
        char object;

        public Position(int x, int y, char object) {
            this.x = x;
            this.y = y;
            this.object = object;
        }
    }
}
