package boj.p20044;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p20044/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N * 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int value = arr[i] + arr[2 * N - 1 - i];
            if (result > value) {
                result = value;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
