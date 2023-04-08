package boj.p21611;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[][] board;
    static int sharkX, sharkY;
    static List<Integer> list;

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    static int[] rotate = {3,2,4,1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p21611/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        for (int i=1;i<N+1;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<N+1;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sharkX = (N+1)/2;
        sharkY = (N+1)/2;

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            distroy(d,s);
            pick();
            List<Integer> prev = list;
            List<Integer> bomb = new ArrayList<>();
            while(true) {
                bomb = bomb(prev);
                if (prev.size() == bomb.size()) break;
                prev = bomb;
            }
            list = group(bomb);
            setBoard();
        }
        int result = bombed[1] + bombed[2]*2 + bombed[3]*3;
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void distroy(int d, int s) {
        int x = sharkX;
        int y = sharkY;
        while(s-->0) {
            x += dx[d];
            y += dy[d];
            if (x <= 0 || y <= 0 || x > N || y > N) break;
            board[x][y] = 0;
        }
    }

    static void pick() {
        list = new ArrayList<>();
        int x = sharkX;
        int y = sharkY;
        int go = 1;
        int index = 0;
        while(true) {
            if (index != 0 && index % 2 == 0) go++;
            int direction = rotate[index++ % 4];
            for (int i=0;i<go;i++) {
                x += dx[direction];
                y += dy[direction];
                if (board[x][y] != 0) list.add(board[x][y]);
                if (x==1 && y==1) {
                    return ;
                }
            }
        }
    }

    static int[] bombed = new int[4];

    static List<Integer> bomb(List<Integer> list) {
        if (list.isEmpty()) {
            return list;
        }
        boolean[] deleted = new boolean[list.size()];
        int prev = list.get(0);
        int count = 1;
        for (int i=1;i<list.size();i++) {
            if (prev == list.get(i)) {
                count++;
            } else {
                if (count >= 4) {
                    for (int j=i-1;j>=i-count;j--) {
                        deleted[j] = true;
                    }
                }
                prev = list.get(i);
                count = 1;
            }
        }

        if (count >= 4) {
            for (int j=list.size()-1;j>=list.size()-count;j--) {
                deleted[j] = true;
            }
        }

        List<Integer> tmp = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            if (!deleted[i]) {
                tmp.add(list.get(i));
            } else {
                bombed[list.get(i)]++;
            }
        }
        return tmp;
    }

    static List<Integer> group(List<Integer> list) {
        if (list.isEmpty()) {
            return list;
        }
        List<Integer> tmp = new ArrayList<>();

        int num = list.get(0);
        int count = 1;
        for (int i=1;i<list.size();i++) {
            if (num == list.get(i)) {
                count++;
            } else {
                tmp.add(count);
                tmp.add(num);
                num = list.get(i);
                count = 1;
            }
        }
        tmp.add(count);
        tmp.add(num);

        if (tmp.size() >= N*N) {
            return tmp.subList(0, N*N-1);
        }
        return tmp;
    }

    static void setBoard() {
        Queue<Integer> q = new LinkedList<>(list);
        int[][] tmp = new int[N+1][N+1];

        int x = sharkX;
        int y = sharkY;
        int go = 1;
        int index = 0;
        while(true) {

            if (index!=0 && index % 2==0) go++;

            int direction = rotate[index++ % 4];
            for (int i=0;i<go;i++) {
                if (q.isEmpty()) {
                    board = tmp;
                    return;
                }
                x += dx[direction];
                y += dy[direction];
                tmp[x][y] = q.poll();
                if (x==1 && y==1) {
                    board = tmp;
                    return;
                }
            }
        }
    }
}

