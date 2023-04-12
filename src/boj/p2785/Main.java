package boj.p2785;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p2785/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        int count = pq.size() - 1;
        while (!pq.isEmpty()) {
            int now = pq.poll();
            if (now < count) {
                count -= now;
                count -= 1;
                result += now;
            } else {
                result += count;
                break;
            }
            if (count == 0) {
                break;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
