package boj.p2143;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static long T;
    static int n;
    static long[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        B = new long[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> buA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                buA.add(sum);
            }
        }

        List<Long> buB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                buB.add(sum);
            }
        }

        Collections.sort(buA);
        Collections.sort(buB, Comparator.reverseOrder());

        long result = 0;
        int aPointer = 0;
        int bPointer = 0;

        while (true) {
            long valueA = buA.get(aPointer);
            long valueB = buB.get(bPointer);
            long sum = valueA + valueB;
            if (sum > T) {
                bPointer++;
            } else if (sum == T) {
                long countA = 0;
                long countB = 0;
                while (aPointer < buA.size() && buA.get(aPointer) == valueA) {
                    aPointer++;
                    countA++;
                }
                while (bPointer < buB.size() && buB.get(bPointer) == valueB) {
                    bPointer++;
                    countB++;
                }
                result += (countA * countB);
            } else { // sum < T
                aPointer++;
            }
            if (aPointer >= buA.size() || bPointer >= buB.size()) {
                break;
            }
        }
        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}
