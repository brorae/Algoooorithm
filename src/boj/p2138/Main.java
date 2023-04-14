package boj.p2138;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int N;
    static char[] from;
    static char[] to;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p2138/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        from = br.readLine().toCharArray();
        to = br.readLine().toCharArray();

        // 첫번째 꺼진 경우
        int count1 = 0;
        char[] tmp1 = from.clone();
        for (int i = 1; i < N; i++) {
            if (tmp1[i - 1] != to[i - 1]) {
                turnOn(i, tmp1);
                count1++;
            }
        }
        if (tmp1[N - 1] != to[N - 1]) {
            count1 = Integer.MAX_VALUE; // ->못만듬
        }

        // 첫번째 켜진 경우
        int count2 = 1;
        char[] tmp2 = from.clone();
        turnOn(0, tmp2);
        for (int i = 1; i < N; i++) {
            if (tmp2[i - 1] != to[i - 1]) {
                turnOn(i, tmp2);
                count2++;
            }
        }

        if (tmp2[N - 1] != to[N - 1]) {
            count2 = Integer.MAX_VALUE; // ->못만듬
        }

        int result = Math.min(count1, count2);
        if (result == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(result + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void turnOn(int index, char[] arr) {
        int start = index - 1;
        int end = index + 1;
        if (index == 0) {
            start = index;
        }
        if (index == N-1) {
            end = index;
        }

        for (int i = start; i <= end; i++) {
            if (arr[i] == '0') {
                arr[i] = '1';
            } else if (arr[i] == '1') {
                arr[i] = '0';
            }
        }
    }
}
