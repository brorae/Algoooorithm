package kakaotechintern2022.p4;

import java.util.Arrays;

public class Solution {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        Graph graph = new Graph(n);
        for (int[] path : paths) {
            graph.input(path[0], path[1], path[2]);
        }
        int[] dijkstra = graph.dijkstra(1);
        System.out.println(Arrays.toString(dijkstra));
        return answer;
    }

    class Graph {
        private int n;           //노드들의 수
        private int maps[][];    //노드들간의 가중치 저장할 변수

        public Graph(int n) {
            this.n = n;
            maps = new int[n + 1][n + 1];
        }

        public void input(int i, int j, int w) {
            maps[i][j] = w;
            maps[j][i] = w;
        }

        public int[] dijkstra(int v) {
            int[] intensity = new int[n + 1];
            boolean[] check = new boolean[n + 1];

            for (int i = 1; i < n + 1; i++) {
                intensity[i] = Integer.MIN_VALUE;
            }

            intensity[v] = 0;
            check[v] = true;

            for (int i = 1; i < n + 1; i++) {
                if (!check[i] && maps[v][i] != 0) {
                    intensity[i] = maps[v][i];
                }
            }

            for (int i = 0; i < n - 1; i++) {
                //원래는 모든 노드가 true될때까지 인데
                //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
                //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
                int min = Integer.MAX_VALUE;
                int min_index = -1;

                //최소값 찾기
                for (int j = 1; j < n + 1; j++) {
                    if (!check[j] && intensity[j] != Integer.MAX_VALUE) {
                        if (intensity[j] < min) {
                            min = intensity[j];
                            min_index = j;
                        }
                    }
                }

                check[min_index] = true;
                for (int j = 1; j < n + 1; j++) {
                    if (!check[j] && maps[min_index][j] != 0) {
                        if (intensity[j] < Math.max(intensity[min_index], maps[min_index][j])) {
                            intensity[j] = Math.max(intensity[min_index], maps[min_index][j]);
                        }
                    }
                }
            }
            return intensity;
        }
    }
}
