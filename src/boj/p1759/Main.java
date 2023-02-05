package boj.p1759;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static String[] alphabets;
    static boolean[] visited;
    static String[] MO = {"a", "e", "i", "o", "u"};
    static StringBuilder sb = new StringBuilder();

    static List<String> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabets = new String[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken();
        }

        Arrays.sort(alphabets);

        for (int i = 0; i < C; i++) {
            if (isMo(alphabets[i])) {
                dfs(i, 1, 0, 1);
            } else {
                dfs(i, 1, 1, 0);
            }
        }

        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    static void dfs(int index, int depth, int ja, int mo) {
        result.add(alphabets[index]);
        visited[index] = true;

        if (depth == L) {
            if (mo >= 1 && ja >= 2) {
                for (int i = 0; i < L; i++) {
                    sb.append(result.get(i));
                }
                sb.append("\n");
            }
        } else {
            for (int i = index + 1; i < C; i++) {
                if (!visited[i]) {
                    if (isMo(alphabets[i])) {
                        dfs(i, depth + 1, ja, mo + 1);
                    } else {
                        dfs(i, depth + 1, ja + 1, mo);
                    }
                }
            }
        }

        result.remove(result.size()-1);
        visited[index] = false;
    }

    static boolean isMo(String str) {
        for (int j = 0; j < 5; j++) {
            if (str.equals(MO[j])) {
                return true;
            }
        }
        return false;
    }
}
