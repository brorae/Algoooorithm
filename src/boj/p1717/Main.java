package boj.p1717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nums[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd.equals("0")) {
                union(a, b);
            } else if (cmd.equals("1")) {
                int i1 = find(a);
                int i2 = find(b);
                if (i1 == i2) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        nums[rootA] = rootB;
    }

    static int find(int a) {
        if (nums[a] == a) {
            return a;
        }
        return nums[a] = find(nums[a]);
    }
}
