package boj.p2212;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] sensors;
    static int[] distances;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p2212/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        sensors = new int[N];
        distances = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);
        for (int i = 1; i < N; i++) {
            int distance = sensors[i] - sensors[i-1];
            distances[i-1] = distance;
        }

        Arrays.sort(distances);

        int result = 0;
        for (int i = 0; i < N - K; i++) {
            result += distances[i];
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
