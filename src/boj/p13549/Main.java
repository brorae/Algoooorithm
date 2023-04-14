package boj.p13549;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p13549/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K <= N) {
            bw.write(N-K + "");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        int[] board = new int[K * 2 + 1];
        boolean[] visited = new boolean[K * 2 + 1];

        // 5 17
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visited[N] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            int a = now - 1;
            int b = now + 1;
            int c = now * 2;
            if (c < K * 2 + 1 && !visited[c]) {
                board[c] = board[now];
                visited[c] = true;
                q.add(c);
            }
            if (a > 0 && !visited[a]) {
                board[a] = board[now] + 1;
                visited[a] = true;
                q.add(a);
            }
            if (b < K * 2 + 1 && !visited[b]) {
                board[b] = board[now] + 1;
                visited[b] = true;
                q.add(b);
            }
        }
        bw.write(board[K] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
