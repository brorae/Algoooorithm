package boj.p2891;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/boj/p2891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[] cantPlay = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            cantPlay[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int has = Integer.parseInt(st.nextToken());
            if (cantPlay[has]) { // 본인배가 플레이 할 수 없으면
                cantPlay[has] = false;
            } else { //본인 배가 플레이할 수 있으면
                if (has == 1) {
                    if (cantPlay[has+1]) {
                        cantPlay[has+1] = false;
                    }
                } else if (has == N) {
                    if (cantPlay[has-1]) {
                        cantPlay[has-1] = false;
                    }
                } else {
                    if (cantPlay[has-1]) {
                        cantPlay[has-1] = false;
                        continue;
                    }
                    if (cantPlay[has+1]) {
                        cantPlay[has+1] = false;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N ; i++) {
            if (cantPlay[i]) count++;
        }

        bw.write(count+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
