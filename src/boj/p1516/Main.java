package boj.p1516;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] times;
    static int[] indegree;
    static int[] result;
    static List<Vertex> list;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        indegree = new int[N + 1];
        result = new int[N + 1];
        list = new ArrayList<>();
        q = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new Vertex());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int next = Integer.parseInt(st.nextToken());
            times[i] = next;
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }

                list.get(num).addAdj(i);
                indegree[i]++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            Integer now = q.poll();

            for (int next : list.get(now).adj) {
                --indegree[next];

                result[next] = Math.max(result[next], result[now] + times[now]);

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + times[i] + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }
}

class Vertex {
    List<Integer> adj = new ArrayList<>();

    void addAdj(int a) {
        adj.add(a);
    }
}
