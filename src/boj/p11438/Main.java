package boj.p11438;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Vertex> list;
    static boolean[] check;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        depth = new int[N + 1];
        check = new boolean[N + 1];
        parent = new int[18][N + 1];

        for (int i = 0; i < N + 1; i++) {
            list.add(new Vertex());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            list.get(first).addAdj(second);
            list.get(second).addAdj(first);
        }

        getDepth(1, 0);

        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            int a = first; // 더 깊은거
            int b = second; // 얕은거

            int depA = depth[first];
            int depB = depth[second];

            if (depA < depB) {
                int tmp = depA;
                depA = depB;
                depB = tmp;

                tmp = a;
                a = b;
                b = tmp;
            }

            for (int j = 17; j >= 0; j--) {
                if (depA - (int) Math.pow(2, j) >= depB) {
                    depA = (depA - (int) Math.pow(2, j));
                    a = parent[j][a];
                }
                if (depA == depB) {
                    break;
                }
            }

            int answer = a;
            if (a != b) {
                for (int j = 17; j >= 0; j--) {
                    if (parent[j][a] != parent[j][b]) {
                        a = parent[j][a];
                        b = parent[j][b];
                    } else if (parent[j][a] == parent[j][b]) {
                        answer = parent[j][a];
                    }
                }
            }

            bw.write(answer + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static void getDepth(int currentNode, int dep) {
        check[currentNode] = true;
        depth[currentNode] = dep;
        List<Integer> adjs = list.get(currentNode).adj;
        for (int i = 0; i < adjs.size(); i++) {
            Integer adjNode = adjs.get(i);
            if (!check[adjNode]) {
                getDepth(adjNode, dep + 1);
                parent[0][adjNode] = currentNode;
            }
        }
    }
}

class Vertex {
    List<Integer> adj = new ArrayList<>();

    void addAdj(int a) {
        adj.add(a);
    }
}
