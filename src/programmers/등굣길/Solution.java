package programmers.등굣길;

class Solution {

    static int[][] board;

    // 0,0 -> m-1, n-1
    public int solution(int m, int n, int[][] puddles) {
        board = new int[m][n];
        board[0][0] = 1;
        for (int[] puddle : puddles) {
            board[puddle[0]-1][puddle[1]-1] = -1;
        }

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (board[i][j] == -1) continue;

                // i-1,j
                if (i-1 >= 0 && board[i-1][j] != -1) {
                    board[i][j] = (board[i][j] + board[i-1][j]) % 1000000007;
                }

                if (j-1 >= 0 && board[i][j-1] != -1) {
                    board[i][j] = (board[i][j] +board[i][j-1]) % 1000000007;
                }
            }
        }
        return board[m-1][n-1];
    }
}
