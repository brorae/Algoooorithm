package boj.p1946;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            List<Score> scores = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                Score score = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                scores.add(score);
            }
            scores.sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return o1.first - o2.first;
                }
            });

            int index = 0;
            int count = 1;
            for (int j = 1; j < scores.size(); j++) {
                Score current = scores.get(index);
                Score other = scores.get(j);
                if (current.second > other.second) {
                    count++;
                    index = j;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}

class Score {

    int first;
    int second;

    public Score(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Score{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
