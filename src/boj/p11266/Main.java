package boj.p11266;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static List<List<Integer>> list;
    static int[] discovered;
    static boolean[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }
        discovered = new int[V + 1];
        res = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.get(A).add(B);
            list.get(B).add(A);
        }

        for (int i = 1; i <= V; i++) {
            if (discovered[i] == 0) {
                dfs(i, true);
            }
        }

        int sum = 0;
        for (int i = 1; i <= V; i++) {
            if (res[i]) {
                sum += 1;
            }
        }
        bw.write(sum + "\n");
        for (int i = 1; i <= V; i++) {
            if (res[i]) {
                bw.write(i + " ");
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int cnt;

    static int dfs(int current, boolean isRoot) {
        discovered[current] = ++cnt;
        int num = discovered[current];

        int child = 0;
        for (int i = 0; i < list.get(current).size(); i++) {
            Integer next = list.get(current).get(i);
            if (discovered[next] == 0) {
                child++;
                int low = dfs(next, false);
                num = Math.min(num, low);
                if (!isRoot && low >= discovered[current]) {
                    res[current] = true;
                }
            } else {
                num = Math.min(num, discovered[next]);
            }
        }
        if (isRoot && child > 1) {
            res[current] = true;
        }
        return num;
    }
}
