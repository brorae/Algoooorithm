package boj.p2217;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static Integer[] ropes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ropes = new Integer[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes, Comparator.reverseOrder());

        int max = ropes[0];

        for (int i = 1; i < N; i++) {
            max = Math.max(max, ropes[i] * (i+1));
        }

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
