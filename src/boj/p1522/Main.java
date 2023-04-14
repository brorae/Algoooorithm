package boj.p1522;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p1522/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String word = br.readLine();
        char[] c = word.toCharArray();

        int aCount = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'a') {
                aCount++;
            }
        }

        int bCount = 0;
        for (int i = 0; i < aCount; i++) {
            if (c[i] == 'b') {
                bCount++;
            }
        }

        int result = bCount;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'b') {
                bCount--;
            }
            if (c[(aCount + i) % c.length] == 'b') {
                bCount++;
            }
            result = Math.min(result, bCount);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
