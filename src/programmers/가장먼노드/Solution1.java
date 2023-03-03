package programmers.가장먼노드;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution1 {

    static List<List<Integer>> adj;
    static int N;
    static int[][] edges;
    static Queue<Integer> q;
    static int[] count;
    static int max = Integer.MIN_VALUE;
    static int resultCount = 0;

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
            if (count[i] == max) {
                resultCount++;
            }
        }
        return resultCount;
    }

    void bfs() {
        count[1] = 0;
        q.add(1);

        while (!q.isEmpty()) {
            Integer now = q.poll();
            max = Math.max(max, count[now]);
            List<Integer> adjs = adj.get(now);
            for (int i = 0; i < adjs.size(); i++) {
                int next = adjs.get(i);
                if (count[now] + 1 < count[next]) {
                    count[next] = count[now] + 1;
                    q.add(next);
                }
            }
        }
    }
}


