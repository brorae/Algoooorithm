package boj.p2885;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p2885/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());

        int min = 0;
        int num = 1;
        int count = 0;
        for (int i=0;i<=20;i++) {
            if (K <= num) {
                min = num;
                break;
            }
            count++;
            num *= 2;
        }

        String binary = Integer.toBinaryString(K);
        int index = binary.length() - 1; //110
        while(binary.charAt(index--) != '1') {
            count--;
        }
        bw.write(min + " " + count);
        bw.flush();
        bw.close();
        br.close();
    }
}
