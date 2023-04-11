package boj.p1543;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/boj/p1543/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        String find = br.readLine();

        int count = 0;
        while(!word.isEmpty()) {
            if (word.startsWith(find)) {
                count++;
                word = word.substring(find.length());
            } else {
                word = word.substring(1);
            }
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
