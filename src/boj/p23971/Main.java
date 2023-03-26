package boj.p23971;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p23971/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int garo = 0;
        int sero = 0;
        if (H % (N + 1) != 0) {
            garo = H / (N + 1) + 1;
        } else {
            garo = H / (N + 1);
        }
        if (W % (M + 1) != 0) {
            sero = W / (M + 1) + 1;
        } else {
            sero = W / (M + 1);
        }
        int result = garo * sero;
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
