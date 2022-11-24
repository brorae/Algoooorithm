package boj.p4963;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr = new int[51][51];
    static boolean[][] check = new boolean[51][51];
    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while (true) {
            for (int[] ints : arr) {
                Arrays.fill(ints, 0);
            }
            for (boolean[] booleans : check) {
                Arrays.fill(booleans, false);
            }
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            for (int i = 0; i < h; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(line[j]);
                }
            }

            int result = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!check[i][j] && arr[i][j] == 1) {
                        dfs(i, j);
                        result++;
                    }
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    static void dfs(int a, int b) {
        check[a][b] = true;
        for (int i = 0; i < 8; i++) {
            int next1 = a + dx[i];
            int next2 = b + dy[i];
            if (next1 < 0 || next1 >= h || next2 < 0 || next2 >= w) {
                continue;
            }
            if (!check[next1][next2] && arr[next1][next2] == 1) {
                dfs(next1, next2);
            }
        }
    }
}
