package boj.p2003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        int leftPointer = 0;
        int rightPointer = 0;
        int sum = A[0];
        while (true) {
            if (sum > M) {
                sum -= A[leftPointer];
                leftPointer++;
            } else if (sum == M) {
                result++;
                sum -= A[leftPointer];
                leftPointer++;
            } else {
                rightPointer++;
                sum += A[rightPointer];
            }
            if (leftPointer >= N || rightPointer >= N) {
                break;
            }
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}
