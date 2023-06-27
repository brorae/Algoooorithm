package programmers.프렌즈4블록;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static char[][] BOARD;
    static boolean[][] bomb;

    static int[] dx = {1, 1, 0};
    static int[] dy = {0, 1, 1};

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        BOARD = new char[m][n];

        for (int i = 0; i < board.length; i++) {
            String s = board[i];
            for (int j = 0; j < s.length(); j++) {
                BOARD[i][j] = s.charAt(j);
            }
        }

        while(true) {
            bomb = new boolean[m][n];
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (BOARD[i][j] == ' ') continue;
                    char c = BOARD[i][j];
                    int count = 1;
                    for (int k = 0; k < 3; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (BOARD[nx][ny] == c) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        bomb[i][j] = true;
                        for (int k = 0; k < 3; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            bomb[nx][ny] = true;
                        }
                    }
                }
            }
            int bombCount = 0;

            for (int i = 0; i < n; i++) {
                Queue<Character> q = new LinkedList<>();
                for (int j = m-1; j >=0 ; j--) {
                    if (bomb[j][i]) bombCount++;
                    else {
                        q.add(BOARD[j][i]);
                    }
                }

                int index = m-1;

                while(!q.isEmpty()) {
                    BOARD[index--][i] = q.poll();
                }
                for (int j = index; j >= 0 ; j--) {
                    BOARD[j][i] = ' ';
                }
            }

            if (bombCount == 0) break;
            answer+=bombCount;
        }
        return answer;
    }
}
