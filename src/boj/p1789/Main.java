package boj.p1789;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long S = Long.parseLong(br.readLine());

        int num = 1;
        long sum = 0;
        long result = 0;
        while(true) {
            sum += num;
            if (sum > S) {
                result = num-1;
                break;
            }
            num++;
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
