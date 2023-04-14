package programmers.방문길이;

import java.util.*;

class Solution {

    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; //상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public int solution(String dirs) {
        int answer = 0;
        visited = new boolean[11][11][11][11];
        Map<Character, Integer> map = new HashMap<>();
        map.put('U', 0);
        map.put('D', 1);
        map.put('L', 2);
        map.put('R', 3);

        int x = 5;
        int y = 5;
        for (int i = 0; i < dirs.length(); i++) {
            int direction = map.get(dirs.charAt(i));
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx < 0 || ny < 0 || nx > 10 || ny > 10) {
                continue;
            }
            if (!visited[x][y][nx][ny]) {
                answer++;
                visited[x][y][nx][ny] = true;
                visited[nx][ny][x][y] = true;
                x = nx;
                y = ny;
            } else {
                x = nx;
                y = ny;
            }
        }

        return answer;
    }
}
