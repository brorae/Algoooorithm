package kakaotechintern.y2021.p2;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int[] straightX = {-1, 1, 0, 0};
    static int[] straightY = {0, 0, -1, 1};

    static int[] doubleStraightX = {-2, 2, 0, 0};
    static int[] doubleStraightY = {0, 0, -2, 2};

    static int[] diagonalX = {1, 1, -1, -1};
    static int[] diagonalY = {1, -1, 1, -1};

    static Queue<Point> q;

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int k = 0; k < 5; k++) {
            q = new LinkedList<>();
            String[] place = places[k];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        q.add(new Point(i, j));
                    }
                }
            }

            answer[k] = bfs(place);
        }

        return answer;
    }

    static int bfs(String[] place) {
        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + straightX[i];
                int ny = now.y + straightY[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }
                if (place[nx].charAt(ny) == 'P') {
                    return 0;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + doubleStraightX[i];
                int ny = now.y + doubleStraightY[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }
                if (place[nx].charAt(ny) == 'P') {
                    int checkX = 0;
                    int checkY = 0;
                    if (doubleStraightX[i] == 0) {
                        checkX = now.x;
                        checkY = now.y + doubleStraightY[i] / 2;
                    } else if (doubleStraightY[i] == 0) {
                        checkX = now.x + doubleStraightX[i] / 2;
                        checkY = now.y;
                    }
                    if (place[checkX].charAt(checkY) != 'X') {
                        return 0;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + diagonalX[i];
                int ny = now.y + diagonalY[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }
                if (place[nx].charAt(ny) == 'P') {
                    int checkX = now.x + diagonalX[i];
                    int checkY = now.y;

                    if (place[checkX].charAt(checkY) != 'X') {
                        return 0;
                    }

                    checkX = now.x;
                    checkY = now.y + diagonalY[i];

                    if (place[checkX].charAt(checkY) != 'X') {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
