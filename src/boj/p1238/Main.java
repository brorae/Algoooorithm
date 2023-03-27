package boj.p1238;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X;
    static List<List<Edge>> edges;


    static int[][] d;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1238/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.get(from).add(new Edge(to, cost));
        }

        d = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i <= N; i++) {
            d[i][i] = 0;
            dijkstra(i);
        }

        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, d[i][X] + d[X][i]);
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int from) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(from, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            List<Edge> edges = Main.edges.get(now.next);
            for (Edge edge : edges) {
                if (d[from][edge.next] > edge.cost + now.cost) {
                    d[from][edge.next] = edge.cost + now.cost;
                    pq.add(new Edge(edge.next, edge.cost + now.cost));
                }
            }
        }
    }
}

class Edge {
    int next;
    int cost;

    public Edge(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "next=" + next +
                ", cost=" + cost +
                '}';
    }
}
