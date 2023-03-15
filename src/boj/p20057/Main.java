package boj.p20057;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int[] count;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][][] wind = {{{1, 1, 1}, {-1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10},
            {1, -1, 10}, {0, -2, 5}, {0, -1, 55}},
            {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, 1, 10},
                    {1, -1, 10}, {2, 0, 5}, {1, 0, 55}},
            {{-1, -1, 1}, {1, -1, 1}, {1, 0, 7}, {-1, 0, 7}, {2, 0, 2}, {-2, 0, 2}, {1, 1, 10},
                    {-1, 1, 10}, {0, 2, 5}, {0, 1, 55}},
            {{1, 1, 1}, {1, -1, 1}, {0, 1, 7}, {0, -1, 7}, {0, 2, 2}, {0, -2, 2}, {-1, -1, 10},
                    {-1, 1, 10}, {-2, 0, 5}, {-1, 0, 55}}};

    static int out = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p20057/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        count = new int[2 * N - 1];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 2; j++) {
                count[2 * i + j] = cnt;
            }
            cnt++;
        }
        count[2 * N - 2] = N - 1;

        int turn = 0;
        int x = N / 2;
        int y = N / 2;
        while (turn < 2 * N - 1) {
            for (int i = 0; i < count.length; i++) {
                int i1 = count[i];
                for (int j = 0; j < i1; j++) {
                    x += dx[turn % 4];
                    y += dy[turn % 4];
                    calculate(x, y, turn % 4);
                }
                turn++;
            }
        }
        bw.write(out + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void calculate(int x, int y, int direction) {
        int sum = 0;
        int[][] ints = wind[direction];
        for (int i = 0; i < ints.length; i++) {
            int[] anInt = ints[i];
            int nx = x + anInt[0];
            int ny = y + anInt[1];
            int percent = anInt[2];
            if (i == ints.length - 1) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    int v = board[x][y] - sum;
                    out += v;
                    sum += v;
                } else {
                    int v = board[x][y] - sum;
                    board[nx][ny] += v;
                    sum += v;
                }

            } else {
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    int v = board[x][y] * percent / 100;
                    out += v;
                    sum += v;
                } else {
                    int v = board[x][y] * percent / 100;
                    board[nx][ny] += v;
                    sum += v;
                }
            }
        }
    }
}
