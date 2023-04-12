package boj.p11501;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p11501/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                arr[j] = value;
                pq.add(new Point(j, value));
            }

            long cost = 0;
            int buyCount = 0;
            Point p = pq.poll();
            for (int j = 0; j < N; j++) {
                if (p.x == j) {
                    // 판매
                    cost += (long) arr[j] * buyCount;
                    buyCount = 0;
                    p = pq.poll();
                    while (!pq.isEmpty() && p.x <= j) {
                        p = pq.poll();
                    }
                } else {
                    if (p.value > arr[j]) {
                        // 삼
                        buyCount++;
                        cost -= arr[j];
                    }
                }
            }

            sb.append(cost + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }
}

class Point {
    int x;
    int value;

    public Point(int x, int value) {
        this.x = x;
        this.value = value;
    }
}
