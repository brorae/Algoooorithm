package boj.p2243;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 1000000;
    static int n, A, B, C;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        S = 1;
        while (S < MAX) {
            S *= 2;
        }
        tree = new long[S * 2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if (A == 1) {
                // 꺼내는 경우 사탕 맛의 번호 출력
                int result = query(1, S, 1, B);
                update(1, S, 1, result, -1);
                bw.write(result + "\n");
            } else if (A == 2) {
                // 사탕을 넣거나 빼는 경우 update
                C = Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int query(int left, int right, int node, int target) {
        // 리프노드인 경우 -> 사탕 찾음
        if (left == right) {
            return left;
        }
        // 내부노드인 경우
        else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= target) {
                return query(left, mid, node * 2, target);
            } else {
                return query(mid + 1, right, node * 2 + 1, (int) (target - tree[node * 2]));
            }
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        if (target < left || target > right) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }
}
