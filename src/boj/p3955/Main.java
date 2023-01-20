package boj.p3955;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int K;
    static int C;
    static List<Integer> s;
    static List<Integer> t;
    static List<Integer> r;
    static List<Integer> q;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p3955/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            s = new ArrayList<>();
            t = new ArrayList<>();
            r = new ArrayList<>();
            q = new ArrayList<>();
            s.add(1);
            s.add(0);
            t.add(0);
            t.add(1);
            r.add(K);
            r.add(C);
            q.add(-1);
            q.add(-1);

            int result = 0;
            int resultIndex = 0;
            if (1 % gcd(K, C) != 0) {
                bw.write("IMPOSSIBLE\n");
            } else {
                int index = 2;
                while (true) {
                    int R = r.get(index - 2) % r.get(index - 1);
                    int Q = r.get(index - 2) / r.get(index - 1);
                    if (R == 0) {
                        resultIndex = index - 1;
                        break;
                    }
                    r.add(R);
                    q.add(Q);
                    t.add(t.get(index - 2) - t.get(index - 1) * Q);
                    s.add(s.get(index - 2) - s.get(index - 1) * Q);

                    index++;
                }

                Integer X0 = s.get(resultIndex);
                Integer Y0 = t.get(resultIndex);

                double max1 = -1.0 * X0 * gcd(K, C) / C;
                double max2 = 1.0 * Y0 * gcd(K, C) / K;
                double min = Math.min(max1, max2);
                if (min > 0) {
                    if (min % 1.0 == 0) {
                        result = (int) (min - 1);
                    } else {
                        result = (int) min;
                    }
                } else {
                    result = (int) (min - 1);
                }

                int answer = Y0 - K / gcd(K, C) * result;
                if (answer > 1000000000) {
                    bw.write("IMPOSSIBLE\n");
                } else {
                    bw.write(answer + "\n");
                }
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
