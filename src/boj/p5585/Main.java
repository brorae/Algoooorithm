package boj.p5585;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] coin = {500, 100, 50, 10, 5, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int pay = Integer.parseInt(br.readLine());
        int change = 1000 - pay;
        int result = 0;
        while (change != 0) {
            for (int i = 0; i < coin.length; i++) {
                if (change / coin[i] != 0) {
                    result++;
                    change -= coin[i];
                    break;
                }
            }
        }
        bw.write(result+"");
        bw.flush();

        bw.close();
        br.close();
    }
}
