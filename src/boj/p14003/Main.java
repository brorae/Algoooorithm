package boj.p14003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int[] A;
    static int[] dp;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        p = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;
        dp[0] = A[0];
        p[0] = 0;
        for (int i = 1; i < N; i++) {
            if (dp[index] < A[i]) {
                dp[index + 1] = A[i];
                p[i] = index + 1;
                index++;
            } else {
                int low = 0;
                int high = index;

                while (low < high) {
                    int mid = (low + high) / 2;
                    if (dp[mid] < A[i]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                dp[high] = A[i];
                p[i] = high;
            }
        }

        int resultSize = index+1;
        bw.write(resultSize+ "\n");

        Stack<Integer> stack = new Stack<>();

        for (int i = N-1; i >=0 ; i--) {
            if (p[i] == index) {
                index--;
                stack.push(A[i]);
            }
        }

        while(!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
