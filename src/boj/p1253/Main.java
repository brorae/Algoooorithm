package boj.p1253;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < N; i++) {
            int pointerA = 0;
            int pointerB = N - 1;
            while (true) {
                if (pointerA == i) {
                    pointerA++;
                } else if (pointerB == i) {
                    pointerB--;
                }
                if (pointerA == pointerB) {
                    break;
                }
                long value = nums[pointerA] + nums[pointerB];
                if (value > nums[i]) {
                    pointerB--;
                    continue;
                } else if (value < nums[i]) {
                    pointerA++;
                    continue;
                } else {
                    count++;
                    break;
                }
            }
        }
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
