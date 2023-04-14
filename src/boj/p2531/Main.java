package boj.p2531;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p2531/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int result = map.size();
        if (map.getOrDefault(c, 0) == 0) {
            result += 1;
        }


        for (int i = 0; i < N; i++) {
            map.put(nums[i], map.get(nums[i]) - 1);
            if (map.get(nums[i]) == 0) {
                map.remove(nums[i]);
            }

            map.put(nums[(k+i) % N], map.getOrDefault(nums[(k+i) % N], 0) + 1);

            int r = map.size();
            if (map.getOrDefault(c, 0) == 0) {
                r += 1;
            }
            result = Math.max(result, r);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
