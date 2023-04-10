package programmers.전력망을둘로나누기;

import java.util.*;

class Solution {

    static int N;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        N = n;
        int removeIndex = 0;
        while(removeIndex < wires.length) {
            adj = new ArrayList<>();
            for (int i=0;i<n+1;i++) {
                adj.add(new ArrayList<>());
            }
            visited = new boolean[n+1];

            for (int i=0;i<wires.length;i++) {
                if (i == removeIndex) continue;
                int[] wire = wires[i];
                int from = wire[0];
                int to = wire[1];
                adj.get(from).add(to);
                adj.get(to).add(from);
            }

            List<Integer> counts = new ArrayList<>();
            for (int i=1;i<N+1;i++) {
                if (!visited[i]) {
                    int count = bfs(i);
                    counts.add(count);
                }
            }
            answer = Math.min(answer, Math.abs(counts.get(0) - counts.get(1)));
            removeIndex++;
        }

        return answer;
    }

    static int bfs(int num) {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        visited[num] = true;
        int count = 1;
        while(!q.isEmpty()) {
            int now = q.poll();
            List<Integer> l = adj.get(now);
            for (Integer e : l) {
                if(!visited[e]) {
                    q.add(e);
                    visited[e] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
