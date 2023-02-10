package boj.p1922;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nums[i] = i;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            pq.add(edge);
        }

        int count = 0;
        int result = 0;
        while (count < N - 1) {
            Edge now = pq.poll();
            if (find(now.current) != find(now.dest)) {
                union(now.current, now.dest);
                result += now.cost;
                count++;
            }
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }

    static class Edge {
        int current;
        int dest;
        int cost;

        public Edge(int current, int dest, int cost) {
            this.current = current;
            this.dest = dest;
            this.cost = cost;
        }
    }

    static void union(int a, int b) {
        int rootA = nums[a];
        int rootB = nums[b];
        nums[rootA] = rootB;
    }

    static int find(int a) {
        if (nums[a] == a) {
            return a;
        }
        return nums[a] = find(nums[a]);
    }
}
