package boj.p1802;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/boj/p1802/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String line = br.readLine();
            boolean canMake = true;
            while(line.length() != 1) {
                int start = 0;
                int end = line.length() - 1;
                while (start < end) {
                    if (line.charAt(start) == line.charAt(end)) {
                        canMake = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if (!canMake) break;
                line = line.substring(0, line.length()/2);
            }

            if (canMake) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
