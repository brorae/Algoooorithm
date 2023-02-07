package boj.p1806;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        long sum = nums[0];
        int min = Integer.MAX_VALUE;
        while (true) {
            if (sum >= S) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            } else {
                right++;
                sum += nums[right];
            }
            if (left >= N || right >= N) {
                break;
            }
        }

        if (min == Integer.MAX_VALUE) {
            bw.write("0");
        } else {
            bw.write(min + "");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
