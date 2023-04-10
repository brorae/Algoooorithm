package programmers.배달;

import java.util.*;

class Solution {

    static List<List<Edge>> adj;
    static int[] arr;

    public int solution(int N, int[][] road, int K) {
        adj = new ArrayList<>();
        for (int i=0;i<N+1;i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] r : road) {
            int from = r[0];
            int to = r[1];
            int cost = r[2];
            adj.get(from).add(new Edge(to, cost));
            adj.get(to).add(new Edge(from, cost));
        }
        arr = new int[N+1];
        arr[1] = 0;
        for(int i=2;i<N+1;i++) {
            arr[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        pq.add(new Edge(1,0));

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            List<Edge> le = adj.get(now.e);
            for (Edge e : le) {
                if (now.cost + e.cost < arr[e.e]) {
                    arr[e.e] = now.cost + e.cost;
                    pq.add(new Edge(e.e, arr[e.e]));
                }
            }
        }

        int answer = 0;
        for(int i=1;i<N+1;i++) {
            if (arr[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}

class Edge {
    int e;
    int cost;

    public Edge(int e, int cost) {
        this.e = e;
        this.cost = cost;
    }
}
