package boj.p3830;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[] root, weight;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            root = new int[100001];
            weight = new int[100001];
            for (int i = 1; i <= 100000; i++) {
                root[i] = i;
            }

            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if (cmd.equals("!")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());

                    union(a, b, w);
                } else if (cmd.equals("?")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if (find(a) != find(b)) {
                        bw.write("UNKNOWN\n");
                    } else {
                        bw.write(weight[b] - weight[a] + "\n");
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();;
    }

    private static void union(int a, int b, int w) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return;
        }
        root[bRoot] = aRoot;
        weight[bRoot] += (w - weight[b] + weight[a]);
    }

    private static int find(int a) {
        if (root[a] == a) {
            return a;
        } else {
            int prv = find(root[a]);
            weight[a] += weight[root[a]];
            return root[a] = prv;
        }
    }
}
