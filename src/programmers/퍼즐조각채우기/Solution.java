package programmers.퍼즐조각채우기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    static int[][] GAME_BOARD;
    static int[][] TABLE;
    static int boardLength;
    static boolean[][] gameBoardVisited;
    static boolean[][] tableVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static List<List<Point>> figures = new ArrayList<>();
    static boolean[] isUsed;

    public int solution(int[][] game_board, int[][] table) {
        GAME_BOARD = game_board;
        TABLE = table;
        boardLength = game_board.length;
        gameBoardVisited = new boolean[boardLength][boardLength];
        tableVisited = new boolean[boardLength][boardLength];

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (TABLE[i][j] == 1 && !tableVisited[i][j]) {
                    bfs(i, j, new ArrayList<>());
                }
            }
        }
        isUsed = new boolean[figures.size()];

        int rotateCount = 4;
        while (rotateCount-- > 0) {
            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength; j++) {
                    if (GAME_BOARD[i][j] == 0 && !gameBoardVisited[i][j]) {
                        for (int l = 0; l < figures.size(); l++) {
                            List<Point> figure = figures.get(l);
                            if(isUsed[l]) continue;
                            boolean canFill = true;
                            for (Point point : figure) {
                                int nx = i + point.x;
                                int ny = j + point.y;
                                if (nx < 0 || ny < 0 || nx >= boardLength || ny >= boardLength
                                        || GAME_BOARD[nx][ny] == 1 || gameBoardVisited[nx][ny]) {
                                    canFill = false;
                                    break;
                                }
                            }

                            if (canFill) {
                                for (Point point : figure) {
                                    int nx = i + point.x;
                                    int ny = j + point.y;
                                    gameBoardVisited[nx][ny] = true;
                                }

                                for (Point point : figure) {
                                    int nx = i + point.x;
                                    int ny = j + point.y;
                                    for (int k = 0; k < 4; k++) {
                                        int nnx = nx + dx[k];
                                        int nny = ny + dy[k];
                                        if (nnx < 0 || nny < 0 || nnx >= boardLength || nny >= boardLength) continue;
                                        if (GAME_BOARD[nnx][nny] == 0 && !gameBoardVisited[nnx][nny]) {
                                            canFill = false;
                                            break;
                                        }
                                    }
                                    if (!canFill) break;
                                }

                                if (!canFill) {
                                    for (Point point : figure) {
                                        int nx = i + point.x;
                                        int ny = j + point.y;
                                        gameBoardVisited[nx][ny] = false;
                                    }
                                } else {
                                    isUsed[l] = true;
                                }
                            }
                        }

                    }
                }
            }
            GAME_BOARD = rotateToRight(GAME_BOARD);
            gameBoardVisited = rotateToRight(gameBoardVisited);
        }

        int count = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (gameBoardVisited[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    void bfs(int x, int y, List<Point> points) {
        tableVisited[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        while (!q.isEmpty()) {
            Point now = q.poll();
            points.add(new Point(now.x - x, now.y - y));
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= boardLength || ny >= boardLength) {
                    continue;
                }
                if (TABLE[nx][ny] == 1 && !tableVisited[nx][ny]) {
                    tableVisited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
        figures.add(points);
    }


    public int[][] rotateToRight(int[][] board) {
        int size = board.length;
        int[][] tmpBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmpBoard[j][size - 1 - i] = board[i][j];
            }
        }
        return tmpBoard;
    }

    public boolean[][] rotateToRight(boolean[][] board) {
        int size = board.length;
        boolean[][] tmpBoard = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmpBoard[j][size - 1 - i] = board[i][j];
            }
        }
        return tmpBoard;
    }
}

class Point {
    int x;
    int y;

    public Point(int boardX, int boardY) {
        this.x = boardX;
        this.y = boardY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
