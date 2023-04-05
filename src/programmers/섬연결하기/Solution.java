package programmers.섬연결하기;

import java.util.*;

class Solution {

    static int[] arr;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int[] cost : costs) {
            pq.add(new Edge(cost[0], cost[1], cost[2]));
        }

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (find(now.from) == find(now.to)) {
                continue;
            }
            union(now.from, now.to);
            answer += now.cost;

            boolean isConnected = true;
            int value = find(0);
            for (int i = 1; i < n; i++) {
                if (value != find(i)) {
                    isConnected = false;
                    break;
                }
            }
            if (isConnected) {
                break;
            }
        }

        return answer;
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            arr[b] = a;
        }
    }

    public int find(int a) {
        if (arr[a] == a) {
            return a;
        }
        return arr[a] = find(arr[a]);
    }
}

class Edge {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
