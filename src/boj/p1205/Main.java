package boj.p1205;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p1205/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        if (N == 0) {
            bw.write("1");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        if (N == P && arr[N - 1] >= score) {
            result = -1;
        } else if (arr[N-1] > score) {
            result = N + 1;
        } else {   // 100 90 90 80    80
            int pointer = 0;
            while (true) {
                if (score >= arr[pointer]) {
                    result = pointer + 1;
                    break;
                } else if (score < arr[pointer]) {
                    pointer++;
                }
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
