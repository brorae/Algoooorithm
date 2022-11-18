package boj.p2251;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static int[] arr;
    static boolean[][] check = new boolean[201][201];

    static Queue<Bucket> q = new LinkedList<>();
    static Set<Integer> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        q.add(new Bucket(new int[]{0, 0, arr[2]}));
        check[0][0] = true;

        while (!q.isEmpty()) {
            Bucket bucket = q.poll();
            if (bucket.tmp[0] == 0) {
                result.add(bucket.tmp[2]);
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        Bucket next = bucket.move(i, j);
                        if (!check[next.tmp[0]][next.tmp[1]]) {
                            check[next.tmp[0]][next.tmp[1]] = true;
                            q.add(next);
                        }
                    }
                }
            }
        }

        String answer = result.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        bw.write(answer);
        bw.flush();

        bw.close();
        br.close();
    }

    static class Bucket {
        int[] tmp;

        public Bucket(int[] tmp) {
            this.tmp = new int[3];
            for (int i = 0; i < 3; i++) {
                this.tmp[i] = tmp[i];
            }
        }

        Bucket move(int from, int to) {
            int[] ttmp = new int[]{tmp[0], tmp[1], tmp[2]};
            if (tmp[to] + tmp[from] > arr[to]) {
                ttmp[to] = arr[to];
                ttmp[from] -= arr[to] - tmp[to];
            } else {
                ttmp[to] = tmp[from] + tmp[to];
                ttmp[from] = 0;
            }
            return new Bucket(ttmp);
        }
    }
}
