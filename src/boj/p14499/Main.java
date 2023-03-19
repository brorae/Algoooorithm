package boj.p14499;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X, Y, K;
    static int[] dice = {0, 0, 0, 0, 0, 0}; //위, 아래, 오, 왼, 북, 남
    static int[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p14499/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());
            int tmpX = X + dx[direction - 1];
            int tmpY = Y + dy[direction - 1];
            if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= M) continue;
            X = X + dx[direction - 1];
            Y = Y + dy[direction - 1];
            throwDice(direction - 1);
            sb.append(dice[0]);
            sb.append("\n");
            int diceFloorNumber = dice[1];
            int boardNum = board[X][Y];
            if (boardNum == 0) {
                board[X][Y] = diceFloorNumber;
            } else {
                dice[1] = boardNum;
                board[X][Y] = 0;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void throwDice(int direction) { // 0(동) 1(서) 2(북) 3(남)
        int[] tmpDice = new int[6];
        if (direction == 0) { // 동
            tmpDice[0] = dice[3];
            tmpDice[1] = dice[2];
            tmpDice[2] = dice[0];
            tmpDice[3] = dice[1];
            tmpDice[4] = dice[4];
            tmpDice[5] = dice[5];
        } else if (direction == 1) { // 서
            tmpDice[0] = dice[2];
            tmpDice[1] = dice[3];
            tmpDice[2] = dice[1];
            tmpDice[3] = dice[0];
            tmpDice[4] = dice[4];
            tmpDice[5] = dice[5];
        } else if (direction == 2) { // 북
            tmpDice[0] = dice[5];
            tmpDice[1] = dice[4];
            tmpDice[2] = dice[2];
            tmpDice[3] = dice[3];
            tmpDice[4] = dice[0];
            tmpDice[5] = dice[1];
        } else { // 남
            tmpDice[0] = dice[4];
            tmpDice[1] = dice[5];
            tmpDice[2] = dice[2];
            tmpDice[3] = dice[3];
            tmpDice[4] = dice[1];
            tmpDice[5] = dice[0];
        }
        dice = tmpDice;
    }
}
