package boj.p2042;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] nums;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        init();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            long second = Long.parseLong(st.nextToken());
            if (flag == 1) {
                // first번째 수를 second로 바꿔
                long targetValue = tree[S + first - 1];
                long diff = second - targetValue;
                update(1, S, 1, first, diff);
            } else if (flag == 2) {
                // first번째 부터 second까지 합
                long result = query(1, S, 1, first, (int) second);
                bw.write(result + "\n");
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }

    static void init() {
        // leaf는 데이터로
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }
        // 내부노드는 자식의 합
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 연관 없음
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 2. 판단 가능( 쏙 들어감 )
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 3. 판단 불가 ( 걸쳐 있음 )
        else {
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 1. 연관 없음
        if (target < left || target > right) {
            return;
        }
        // 2. 연관 있음
        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}
