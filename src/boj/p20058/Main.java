package boj.p20058;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[][] board;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p20058/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int r = (int) Math.pow(2, N);
        board = new int[r + 1][r + 1];

        for (int i = 1; i < r + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < r + 1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int amount = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int l = Integer.parseInt(st.nextToken());
            rotate(l);
            amount = removeAndCal();
        }

        int answer = 0;
        boolean[][] visited = new boolean[r + 1][r + 1];
        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < r + 1; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    int value = bfs(i, j, visited);
                    answer = Math.max(answer, value);
                }
            }
        }

        bw.write(amount + "\n");
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void rotate(int l) {
        int len = board.length;
        int[][] tmp = new int[len][len];
        int c = (int) Math.pow(2, l);
        for (int i = 1; i < len; i += c) {
            for (int j = 1; j < len; j += c) {
                rotate(i, j, c, tmp);
            }
        }
        board = tmp;
    }

    static void rotate(int x, int y, int c, int[][] newBoard) {
        int column = y + c - 1;
        for (int i = x; i < x + c; i++) {
            int index = x;
            for (int j = y; j < y + c; j++) {
                newBoard[index++][column] = board[i][j];
            }
            column--;
        }
    }

    static int removeAndCal() {
        int amount = 0;
        int len = board.length;
        int[][] newBoard = new int[len][len];
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx <= 0 || ny <= 0 || nx >= len || ny >= len) {
                        continue;
                    }
                    if (board[nx][ny] != 0) {
                        count++;
                    }
                }
                if (count < 3) {
                    newBoard[i][j]--;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                board[i][j] += newBoard[i][j];
                amount += board[i][j];
            }
        }
        return amount;
    }

    static int bfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(x, y));
        int count = 0;
        while (!q.isEmpty()) {
            count++;
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx <= 0 || ny <= 0 || nx >= board.length || ny >= board.length) {
                    continue;
                }
                if (board[nx][ny] != 0 && !visited[nx][ny]) {
                    q.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return count;
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
