package kakaotechintern.y2022.p4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static int[] vertexes;
    static int[] intensive;
    static List<List<Edge>> adj;
    static PriorityQueue<Edge> pq;
    static PriorityQueue<Edge> result;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(6,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3}, new int[]{5});
//        solution.solution(7,
//                new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
//                new int[]{1}, new int[]{2, 3, 4});
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        vertexes = new int[n + 1];
        intensive = new int[n + 1];
        Arrays.fill(intensive, Integer.MAX_VALUE);
        // 출발 지점 1
        for (int i = 0; i < gates.length; i++) {
            vertexes[gates[i]] = 1;
        }
        // 도착 지점 -1
        for (int i = 0; i < summits.length; i++) {
            vertexes[summits[i]] = -1;
        }

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < paths.length; i++) {
            int start = paths[i][0];
            int end = paths[i][1];
            int cost = paths[i][2];

            adj.get(start).add(new Edge(end, cost));
            adj.get(end).add(new Edge(start, cost));
        }

        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost == o2.cost) {
                return o1.vertex - o2.vertex;
            }
            return o1.cost - o2.cost;
        });

        result = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost == o2.cost) {
                return o1.vertex - o2.vertex;
            }
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < gates.length; i++) {
            dijkstra(gates[i]);
        }
        System.out.println((result));
        return result.poll().getResult();
    }

    void dijkstra(int vertex) {
        pq.add(new Edge(vertex, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.cost > intensive[now.vertex]) {
                continue;
            }

            if (vertexes[now.vertex] == -1) {
                result.add(now);
            } else {
                List<Edge> edges = adj.get(now.vertex);
                for (int i = 0; i < edges.size(); i++) {
                    Edge next = edges.get(i);
                    if (vertexes[next.vertex] == 1) {
                        continue;
                    }
                    int intensity = Math.max(now.cost, next.cost);
                    if (intensity < intensive[next.vertex]) {
                        intensive[next.vertex] = intensity;
                        pq.add(new Edge(next.vertex, intensive[next.vertex]));
                    }
                }
            }
        }

    }

    class Edge {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int[] getResult() {
            return new int[]{vertex, cost};
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "vertex=" + vertex +
                    ", cost=" + cost +
                    '}';
        }
    }
}
