package boj.p1459;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long result = 0;

        if (W * 2 > S) {
            int min = Math.min(X, Y);
            result += (long) min * S;
            X -= min;
            Y -= min;
            int max = Math.max(X, Y);
            while (max != 0) {
                if (max == 1) {
                    result += W;
                    max -= 1;
                } else if (max > 1) {
                    if (W * 2 > S * 2) {
                        result += S * 2L;
                    } else {
                        result += W * 2L;
                    }
                    max -= 2;
                }
            }
        } else {
            result = (long) (X + Y) * W;
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}
