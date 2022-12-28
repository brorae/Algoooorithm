package boj.p5014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D;
    static boolean[] visited;
    static Queue<Integer> q;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[F + 1];
        q = new LinkedList<>();

        if (bfs()) {
            bw.write(result + "");
        } else {
            bw.write("use the stairs");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static boolean bfs() {
        q.add(S);
        visited[S] = true;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Integer now = q.poll();
                if (now == G) {
                    return true;
                }
                int up = now + U;
                int down = now - D;
                if (up <= F && !visited[up]) {
                    q.add(up);
                    visited[up] = true;
                }
                if (down >= 1 && !visited[down]) {
                    q.add(down);
                    visited[down] = true;
                }
            }
            result++;
        }
        return false;
    }
}
