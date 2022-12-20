package boj.p2644;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Map<Integer, List<Integer>> map;
    static boolean[] visited;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        visited = new boolean[n + 1];
        q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            map.get(first).add(second);
            map.get(second).add(first);
        }

        bfs(from, to, 0);
        bfs(to, from, 0);

        int size = new HashSet<>(result).size();

        if (size == 1) {
            bw.write(result.get(0) + "");
        } else {
            bw.write("-1");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static List<Integer> result = new ArrayList<>();

    static void bfs(int from, int to, int  count) {
        q.add(from);
        visited[from] = true;
        while(!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Integer now = q.poll();
                if (now == to) {
                    result.add(count);
                }
                List<Integer> list = map.get(now);
                for (Integer integer : list) {
                    if (!visited[integer]) {
                        visited[integer] = true;
                        q.add(integer);
                    }
                }
            }
            count++;
        }
    }
}
