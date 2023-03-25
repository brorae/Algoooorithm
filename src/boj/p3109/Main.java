package boj.p3109;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1,};

    static int result;
    static boolean[] canGo;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p3109/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        canGo = new boolean[R];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            dfs(i, i, 0);
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int row, int x, int y) {
        visited[x][y] = true;
        if (y == C - 1) {
            result++;
            canGo[row] = true;
        } else {
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    continue;
                }

                if (board[nx][ny] == '.' && !visited[nx][ny]) {
                    if(canGo[row]) {
                       return;
                    }
                    dfs(row, nx, ny);
                }
            }
        }
    }
}
