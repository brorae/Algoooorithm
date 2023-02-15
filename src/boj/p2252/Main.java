package boj.p2252;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int in[] = new int[32001];
    static List<Vertex> list = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) {
            list.add(new Vertex());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).addAdj(to);
            in[to]++;
        }

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            Integer now = q.poll();
            bw.write(now + " ");
            List<Integer> adj = list.get(now).adj;
            for (Integer integer : adj) {
                if (--in[integer] == 0) {
                    q.add(integer);
                }
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

}

class Vertex {
    List<Integer> adj = new ArrayList<>();

    void addAdj(int num) {
        adj.add(num);
    }
}
