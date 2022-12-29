package boj.p9205;

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

    static Position house, festival;
    static List<Position> stores = new ArrayList<>();
    static Queue<Position> q = new LinkedList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            stores = new ArrayList<>();
            q = new LinkedList<>();
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            house = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                stores.add(new Position(x, y));
            }

            st = new StringTokenizer(br.readLine());
            festival = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            stores.add(festival);

            visited = new boolean[stores.size()];
            q.add(house);

            if (bfs()) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static boolean bfs() {
        while (!q.isEmpty()) {
            Position now = q.poll();
            if (now.x == festival.x && now.y == festival.y) {
                return true;
            }
            for (int i = 0; i < stores.size(); i++) {
                Position store = stores.get(i);
                if (Math.abs(store.x - now.x) + Math.abs(store.y - now.y) <= 1000 && !visited[i]) {
                    visited[i] = true;
                    q.add(store);
                }
            }
        }
        return false;
    }

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
