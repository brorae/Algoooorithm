package boj.p11003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


// 우선순위 큐로 하면 시간초과
public class Main {

    static int[] D;
    static PriorityQueue<Number> pq;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p11003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        D = new int[N];
        pq = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(new Number(i, Integer.parseInt(st.nextToken())));
            while (pq.peek().index < i - L + 1) pq.poll();
            D[i] = pq.peek().value;
        }

        String result = Arrays.stream(D)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        bw.write(result);
        bw.flush();
        bw.close();
        br.close();
    }
}
