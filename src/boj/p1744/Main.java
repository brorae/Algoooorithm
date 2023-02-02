package boj.p1744;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static List<Integer> positive;
    static List<Integer> negativeAndZero;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        positive = new ArrayList<>();
        negativeAndZero = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                negativeAndZero.add(num);
            } else {
                positive.add(num);
            }
        }

        Collections.sort(negativeAndZero);
        Collections.sort(positive, Collections.reverseOrder());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(negativeAndZero);
        while(!pq.isEmpty()) {
            if (pq.size() == 1) {
                result += pq.poll();
            } else {
                result += pq.poll() * pq.poll();
            }
        }

        pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(positive);
        while(!pq.isEmpty()) {
            if (pq.size() == 1) {
                result += pq.poll();
            } else {
                Integer first = pq.poll();
                Integer second = pq.poll();
                if (first == 1 || second == 1) {
                    result += first + second;
                } else {
                    result += first * second;
                }
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
