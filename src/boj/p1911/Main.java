package boj.p1911;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N,L;
    static List<Hole> holes;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1911/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        holes = new ArrayList<>();

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            holes.add(new Hole(start, end));
        }

        Collections.sort(holes, (o1,o2) -> {
            if (o1.start == o2.start) {
                return o1.getGap() - o2.getGap();
            }
            return o1.start - o2.start;
        });

        int result = 0;
        int next = 0;
        for (int i=0;i<holes.size();i++) {
            Hole hole = holes.get(i);
            if (i!= 0 && hole.end <= next) {
                continue;
            } else if (i!=0 && hole.start <= next) {
                hole.start = next + 1;
            }
            int gap = hole.getGap();

            int count = gap/L;
            if (gap % L != 0) {
                count += 1;
            }
            result += count;
            next = hole.end + count*L - gap - 1;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Hole {
    int start;
    int end;

    public Hole(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getGap() {
        return end-start;
    }

    @Override
    public String toString() {
        return "Hole{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
