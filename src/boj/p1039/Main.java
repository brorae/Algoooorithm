package boj.p1039;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[][] visited;
    static Queue<Point> q;

    static int result = -1;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1039/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[1000001][11];
        q = new LinkedList<>();

        bfs();

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs() {
        q.add(new Point(N, 0));
        visited[N][0] = true;
        while (!q.isEmpty()) {
            Point now = q.poll();
            if (now.count == K) {
                result = Math.max(result, now.num);
            } else {
                String num = String.valueOf(now.num);

                for (int i = 0; i < num.length() - 1; i++) {
                    for (int j = i + 1; j < num.length(); j++) {
                        int next = swap(num, i, j);

                        if (next != -1 && !visited[next][now.count + 1]) {
                            visited[next][now.count + 1] = true;
                            q.add(new Point(next, now.count + 1));
                        }
                    }
                }
            }
        }
    }

    static int swap(String num, int i, int j) {
        char[] chars = num.toCharArray();
        if (i == 0 && chars[j] == '0') {
            return -1;
        }
        char first = chars[i];
        char second = chars[j];
        chars[j] = first;
        chars[i] = second;

        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return Integer.parseInt(sb.toString());
    }

    static class Point {
        int num;
        int count;

        public Point(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
