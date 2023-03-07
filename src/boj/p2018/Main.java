package boj.p2018;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int plus = 1;
        sum = new int[N+1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + plus++;
        }

        int count = 0;

        int pointerA = sum.length - 2;
        int pointerB = sum.length - 1;

        while(pointerA < pointerB && pointerA >= 0) {
            int value = sum[pointerB] - sum[pointerA];
            if (value == N) {
                count++;
                pointerA--;
            } else if (value < N) {
                pointerA--;
            } else {
                pointerB--;
            }
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
