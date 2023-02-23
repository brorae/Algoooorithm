package boj.p11000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<Lecture> lectures;
    static PriorityQueue<Lecture> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        lectures = new ArrayList<>();
        pq = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(start, end));
        }

        Collections.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });
        pq.add(lectures.get(0));

        for (int i = 1; i < N; i++) {
            Lecture now = pq.peek();
            Lecture next = lectures.get(i);
            if (next.start >= now.end) {
                pq.poll();
                pq.add(next);
            } else {
                pq.add(next);
            }
        }

        bw.write(pq.size() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Lecture {
    int start;
    int end;

    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
