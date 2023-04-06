package programmers.행렬테두리회전하기;

import java.util.*;

class Solution {

    static int[][] board;

    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                board[i][j] = num++;
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = rotate(query[0], query[1], query[2], query[3]);
        }

        return answer;
    }

    int rotate(int x1, int y1, int x2, int y2) {
        int prev = board[x1][y1];
        int min = prev;
        for (int i = y1 + 1; i <= y2; i++) {
            min = Math.min(min, prev);
            int tmp = board[x1][i];
            board[x1][i] = prev;
            prev = tmp;
        }

        for (int i = x1 + 1; i <= x2; i++) {
            min = Math.min(min, prev);
            int tmp = board[i][y2];
            board[i][y2] = prev;
            prev = tmp;
        }

        for (int i = y2 - 1; i >= y1; i--) {
            min = Math.min(min, prev);
            int tmp = board[x2][i];
            board[x2][i] = prev;
            prev = tmp;
        }

        for (int i = x2 - 1; i >= x1; i--) {
            min = Math.min(min, prev);
            int tmp = board[i][y1];
            board[i][y1] = prev;
            prev = tmp;
        }
        return min;
    }
}
