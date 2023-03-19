package boj.p14888;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static List<Integer> calculations; // 0(+) 1(-) 2(*) 3(/)

    static boolean[] visited;
    static List<Integer> p = new ArrayList<>();
    static List<List<Integer>> permutations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p14888/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        calculations = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                calculations.add(i);
            }
        }

        // 같은 것이 있는 순열??
        for (int i = 0; i < calculations.size(); i++) {
            visited = new boolean[calculations.size()];
            permutation(i);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (List<Integer> permutation : permutations) {
            int num = nums[0];
            for (int i = 1; i < nums.length; i++) {
                num = calculate(num, nums[i], permutation.get(i-1));
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
        br.close();
    }

    static int calculate(int a, int b, Integer flag) {
        if (flag == 0) { // +
            return a + b;
        } else if (flag == 1) { // -
            return a - b;
        } else if (flag == 2) { // *
            return a * b;
        }
        return a / b;
    }

    static void permutation(int index) {
        visited[index] = true;
        p.add(calculations.get(index));
        if (p.size() == calculations.size()) {
            permutations.add(new ArrayList<>(p));
        }
        for (int i = 0; i < calculations.size(); i++) {
            if (!visited[i]) {
                permutation(i);
            }
        }
        visited[index] = false;
        p.remove(p.size() - 1);
    }
}
