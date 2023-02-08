package boj.p1837;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static String P;
    static int K;
    static boolean[] isUsed = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean findAnswer = false;
        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        for (int i = 2; i < K; i++) {
            if (!isUsed[i]){
                if (divide(P, i) != 0) {
                    int num = i;
                    int su = 2;
                    while(num < K) {
                        isUsed[num] = true;
                        num *= su;
                        su++;
                    }
                } else {
                    findAnswer = true;
                    bw.write("BAD " + i);
                    break;
                }
            }
        }
        if (!findAnswer) {
            bw.write("GOOD");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int divide(String str, int n) {
        int index = 0;
        int num = 0;
        int m = 0;
        while (index < str.length()) {
            num += str.charAt(index) - '0';
            m = num % n;
            num = m * 10;
            index++;
        }
        return m;
    }
}
