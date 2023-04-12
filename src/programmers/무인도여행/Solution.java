package programmers.무인도여행;

import java.util.*;

class Solution {

    static int[][] board;
    static boolean[][] visited;
    static Queue<Position> q;
    static List<Integer> result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int r, c;

    public int[] solution(String[] maps) {
        int[] answer = {};

        r = maps.length;
        c = maps[0].length();
        board = new int[r][c];
        visited = new boolean[r][c];
        result = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String map = maps[i];
            for (int j = 0; j < c; j++) {
                if (map.charAt(j) == 'X') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = map.charAt(j) - '0';
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] != -1 && !visited[i][j]) {
                    result.add(bfs(i, j));
                }
            }
        }

        Collections.sort(result);
        if (result.isEmpty()) {
            return new int[]{-1};
        } else {
            return result.stream()
                    .mapToInt(it -> it)
                    .toArray();
        }

    }

    int bfs(int x, int y) {
        q = new LinkedList<>();
        q.add(new Position(x, y));
        visited[x][y] = true;
        int sum = board[x][y];
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    continue;
                }
                if (!visited[nx][ny] && board[nx][ny] != -1) {
                    sum += board[nx][ny];
                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny));
                }
            }
        }
        return sum;
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "[ x : " + x + " y : " + y + " ]";
    }
}
