package boj.p21921;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        List<Integer> visit =  new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < X; i++) {
            visit.add(Integer.parseInt(st.nextToken()));
        }

        int[] arr = new int[X-N+1];
        int first = visit.subList(0, N).stream()
                .mapToInt(Integer::valueOf)
                .sum();
        arr[0] = first;
        for (int i = 0; i < visit.size() - N; i++) {
            int newSum = arr[i] - visit.get(i) + visit.get(i + N);
            arr[i+1] = newSum;
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int count = (int) Arrays.stream(arr).filter(it -> it == max).count();

        if (max == 0) {
            sb.append("SAD");
        } else {
            sb.append(max);
            sb.append("\n" + count);
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }
}
