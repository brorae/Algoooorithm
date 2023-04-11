package boj.p12018;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p12018/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            Integer[] arr = new Integer[p];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < p; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            if (p < l) {
                score[i] = 1;
            } else {
                Arrays.sort(arr, Comparator.reverseOrder());
                score[i] = arr[l-1];
            }
        }
        Arrays.sort(score);

        int index = score.length;
        int sum = 0;
        for (int i=0;i<score.length;i++) {
            sum += score[i];
            if (sum > m) {
                index = i;
                break;
            }
        }

        bw.write(index + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
