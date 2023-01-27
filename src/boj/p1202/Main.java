package boj.p1202;

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
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);

        List<Jewel> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bags);
        Collections.sort(jewels, Comparator.comparingInt(o -> o.weight));

        int jIndex = 0;
        long result = 0;
        for (int i = 0; i < K; i++) {
            Integer currentBag = bags.get(i);
            while (jIndex < N && jewels.get(jIndex).weight <= currentBag) {
                pq.add(jewels.get(jIndex++));
            }

            if (!pq.isEmpty()) {
                result += pq.poll().price;
            }
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}

class Jewel {
    int weight;
    int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}
