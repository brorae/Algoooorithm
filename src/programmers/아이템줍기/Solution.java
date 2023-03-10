package programmers.아이템줍기;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int[][] board;
    static boolean[][] visited;
    static int[][] result;
    static Queue<Position> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new int[102][102];
        visited = new boolean[102][102];
        result = new int[102][102];
        q = new LinkedList<>();

        for (int[] ints : rectangle) {
            int firstX = ints[0]; //1
            int firstY = ints[1]; //1
            int lastX = ints[2];//7
            int lastY = ints[3]; //4
            for (int i = 2 * firstX; i <= 2 * lastX; i++) {
                for (int j = 2 * firstY; j <= 2 * lastY; j++) {
                    if (board[i][j] == -1) {
                        continue;
                    } else if (i == 2 * firstX || i == 2 * lastX || j == 2 * firstY || j == 2 * lastY) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                result[i][j] = Integer.MAX_VALUE;
            }
        }
        result[2 * characterX][2 * characterY] = 1;
        bfs(2 * characterX, 2 * characterY);
        return result[2 * itemX][2 * itemY] / 2;
    }

    void bfs(int x, int y) {
        visited[x][y] = true;
        q.add(new Position(x, y));
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (board[nx][ny] != 1) {
                    continue;
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny));
                    result[nx][ny] = Math.min(result[nx][ny], result[now.x][now.y] + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}, 1, 4, 6,
                3);
        System.out.println(solution1);
    }
}

class Position {
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
