package boj.p1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, K;
    static List<List<Edge>> list;
    static int[] distance;
    static PriorityQueue<Index> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        distance = new int[V + 1];
        for (int i = 1; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[K] = 0;
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.sum));

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            List<Edge> edges = list.get(u);
            edges.add(new Edge(v, w));
        }

        pq.add(new Index(K, 0));
        while (!pq.isEmpty()) {
            Index now = pq.poll();
            List<Edge> edges = list.get(now.vertex);
            for (int i = 0; i < edges.size(); i++) {
                int initValue = distance[edges.get(i).vertex];
                distance[edges.get(i).vertex] = Math.min(distance[edges.get(i).vertex],
                        now.sum + edges.get(i).cost);
                if (initValue != distance[edges.get(i).vertex]) {
                    pq.add(new Index(edges.get(i).vertex, distance[edges.get(i).vertex]));
                }
            }
        }

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
            } else {
                bw.write(distance[i] + "\n");
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

class Index {
    int vertex;
    int sum;

    public Index(int vertex, int sum) {
        this.vertex = vertex;
        this.sum = sum;
    }
}
