package boj.p14476;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Integer[] nums;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nums = new Integer[N];
        a = new int[N];
        b = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        a[0] = nums[0];
        for (int i = 1; i < N; i++) {
            a[i] = gcd(a[i - 1], nums[i]);
        }

        b[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            b[i] = gcd(b[i + 1], nums[i]);

        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            // 왼쪽 끝
            if (i == 0) {
                tmp = b[1];
            }
            // 오른쪽 끝
            else if(i == N-1) {
                tmp = a[N-2];
            }
            // 중간
            else {
                tmp = gcd(a[i-1], b[i+1]);
            }

            if (nums[i] % tmp != 0 && tmp > max) {
                max = tmp;
                maxIndex = i;
            }
        }

        if (max == 0) {
            bw.write("-1");
        } else {
            bw.write(max + " " + nums[maxIndex]);
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int gcd(int a, int b) {
        // gcd(a,b) = gcd(b,a%b)
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
