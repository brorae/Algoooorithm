package boj.p2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[1000000];
        int max = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }
        int result = 0;
        int left = 0;
        int right = max;
        while(left <= right) {
            long sum = 0;
            int mid = (left + right) / 2;
            for (int i = 0; i < N; i++) {
                if (nums[i] > mid) {
                    sum += (nums[i] - mid);
                }
            }
            if (sum == M) {
                result = mid;
                break;
            } else if (sum < M) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }

        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}
