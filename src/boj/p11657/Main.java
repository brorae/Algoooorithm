package boj.p11657;

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
    static List<List<Edge>> list;
    static long[] distance;
    static long[] last;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        distance = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[1] = 0;
        q = new LinkedList<>();
        q.add(1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Edge(B, C));
        }

        int count = 0;

        while (count < N - 1) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Integer now = q.poll();
                List<Edge> edges = list.get(now);
                for (int j = 0; j < edges.size(); j++) {
                    Edge edge = edges.get(j);
                    long initValue = distance[edge.vertex];
                    distance[edge.vertex] = Math.min(distance[edge.vertex], distance[now] + edge.cost);
                    if (initValue != distance[edge.vertex]) {
                        q.add(edge.vertex);
                    }
                }
            }
            count++;
        }

        last = new long[N+1];
        for (int i = 0; i < N + 1; i++) {
            last[i] = distance[i];
        }

        while (!q.isEmpty()) {
            Integer now = q.poll();
            List<Edge> edges = list.get(now);
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                last[edge.vertex] = Math.min(distance[edge.vertex], distance[now] + edge.cost);
            }
        }

        boolean isCycle = false;
        for (int i = 1; i <= N; i++) {
            if (last[i] != distance[i]) {
                isCycle = true;
            }
        }

        if (isCycle) {
            bw.write("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    bw.write("-1\n");
                } else {
                    bw.write(distance[i] + "\n");
                }
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }
}

class Edge {
    int vertex;
    int cost;

    public Edge(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
