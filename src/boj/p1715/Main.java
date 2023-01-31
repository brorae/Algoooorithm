package boj.p1715;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Integer> pq;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        if (N != 1) {
            while (true) {
                Integer first = pq.poll();
                Integer second = pq.poll();
                result += first + second;
                if (pq.isEmpty()) {
                    break;
                }
                pq.add(first + second);
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
