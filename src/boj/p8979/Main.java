package boj.p8979;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p8979/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Medal> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.gold == o2.gold) {
                if (o1.silver == o2.silver) {
                    return o2.dong - o1.dong;
                }
                return o2.silver - o1.silver;
            }
            return o2.gold - o1.gold;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int dong = Integer.parseInt(st.nextToken());
            pq.add(new Medal(num, gold, silver, dong));
        }

        Medal prev = pq.poll(); // 1번
        int same = 0;
        int rank = 1;
        if (prev.num != K) {
            while (!pq.isEmpty()) {
                Medal now = pq.poll();
                if (prev.gold == now.gold && prev.silver == now.silver && prev.dong == now.dong) {
                    same++;
                } else {
                    rank++;
                    rank += same;
                    same = 0;
                    prev = now;
                }
                if (now.num == K) {
                    break;
                }
            }
        }

        bw.write(rank + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Medal {
    int num;
    int gold;
    int silver;
    int dong;

    public Medal(int num, int gold, int silver, int dong) {
        this.num = num;
        this.gold = gold;
        this.silver = silver;
        this.dong = dong;
    }
}
