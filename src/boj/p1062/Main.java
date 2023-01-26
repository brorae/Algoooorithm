package boj.p1062;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] visited;
    static String[] vocas;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[26];
        visited[0] = true;
        visited[2] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;

        vocas = new String[N];

        for (int i = 0; i < N; i++) {
            vocas[i] = br.readLine();
        }

        if (K < 5) {
            bw.write("0");
        } else if (K == 5) {
            dfs(0, 0);
            bw.write(result + "\n");
        } else {
            for (int i = 1; i < 26; i++) {
                if (!visited[i]) {
                    dfs(i, 1);
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private static void dfs(int n, int depth) {
        // 1. 체크인
        visited[n] = true;
        // 2. 목적지인가?
        if (depth == K - 5) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                String voca = vocas[i];
                String substring = voca.substring(4, voca.length() - 4);
                int matchCount = 0;
                for (int j = 0; j < voca.length() - 8; j++) {
                    char c = substring.charAt(j);
                    if (!visited[(int) c - 'a']) {
                        break;
                    } else {
                        matchCount++;
                    }
                }
                if (matchCount == substring.length()) {
                    count++;
                }
            }
            result = Math.max(result, count);
        } else {
            // 3. 연결된 곳을 순회
            for (int i = n + 1; i < 26; i++) {
                // 4. 갈 수 있는가 ?
                if (!visited[i]) {
                    // 5. 간다.
                    dfs(i, depth + 1);
                }
            }
        }
        // 6. 체크아웃
        visited[n] = false;
    }
}
