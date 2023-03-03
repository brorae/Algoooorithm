package programmers.가장먼노드;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    static List<List<Integer>> adj;
    static int N;
    static int[][] edges;
    static Queue<Edge> q;
    static int[] count;
    static int result = Integer.MIN_VALUE;
    static int resultCount = 0;

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
//    }

    public int solution(int n, int[][] edge) {
        N = n;
        edges = edge;
        adj = new ArrayList<>();
        q = new LinkedList<>();
        count = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        Arrays.fill(count, Integer.MAX_VALUE);

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            adj.get(start).add(end);
            adj.get(end).add(start);
        }

        bfs();
        for (int i = 1; i <= n; i++) {
            if (count[i] == result) {
                resultCount++;
            }
        }
//        System.out.println(Arrays.toString(count));
//        System.out.println(result);
//        System.out.println(resultCount);
        return resultCount;
    }

    void bfs() {
        count[1] = 0;
        q.add(new Edge(1, 0));

        while (!q.isEmpty()) {
            Edge now = q.poll();
            result = Math.max(result, now.count);
            List<Integer> adjs = adj.get(now.index);
            for (int i = 0; i < adjs.size(); i++) {
                int next = adjs.get(i);
                if (now.count + 1 < count[next]) {
                    count[next] = now.count + 1;
                    q.add(new Edge(next, now.count + 1));
                }
            }
        }
    }

    class Edge {
        int index;
        int count;

        public Edge(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
