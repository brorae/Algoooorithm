package boj.p11003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 우선순위 큐로 하면 시간초과
public class Main {

    static PriorityQueue<Number> pq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p11003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(new Number(i, Integer.parseInt(st.nextToken())));
            while (pq.peek().index < i - L + 1) pq.poll();
            sb.append(pq.peek().value);
            sb.append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

//    public static void main(String[] args) throws IOException {
////        System.setIn(new FileInputStream("src/boj/p11003/input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int L = Integer.parseInt(st.nextToken());
//        LinkedList<Number> list = new LinkedList<>();
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            Number number = new Number(i, Integer.parseInt(st.nextToken()));
//            while(!list.isEmpty() && list.getLast().value > number.value) list.removeLast();
//            list.add(number);
//            while (!list.isEmpty() && list.getFirst().index < i - L + 1) list.removeFirst();
//            sb.append(list.getFirst().value);
//            sb.append(" ");
//        }
//
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//        br.close();
//    }
}

class Number {
    int index;
    int value;

    public Number(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
