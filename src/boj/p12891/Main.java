package boj.p12891;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[] minCount; // A C G T 순

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String word = br.readLine();

        minCount = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minCount[i] = Integer.parseInt(st.nextToken());
        }

        int pointerA = 0;
        int pointerB = P - 1;
        int[] realCount = new int[4];
        for (int i = pointerA; i <= pointerB; i++) {
            int cal = cal(word.charAt(i));
            realCount[cal]++;
        }

        int result = 0;

        while (true) {
            boolean possible = true;
            for (int i = 0; i < 4; i++) {
                if (realCount[i] < minCount[i]) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                result++;
            }
            int first = cal(word.charAt(pointerA));
            realCount[first]--;
            pointerA++;
            pointerB++;
            if (pointerB >= S) {
                break;
            }
            int last = cal(word.charAt(pointerB));
            realCount[last]++;
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();

    }

    static int cal(char c) {
        if (c == 'A') {
            return 0;
        } else if (c == 'C') {
            return 1;
        } else if (c == 'G') {
            return 2;
        } else {
            return 3;
        }
    }
}

