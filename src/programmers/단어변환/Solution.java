package programmers.단어변환;

public class Solution {
    
    static String Begin;
    static String Target;
    static String[] Words;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        Begin = begin;
        Target = target;
        Words = words;
        visited = new boolean[words.length];
        
        dfs(Begin, 0);
        if (answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }

    void dfs(String nowWord, int count) {
        if (nowWord.equals(Target)) {
            answer = Math.min(answer, count);
        } else {
            for (int i = 0; i < Words.length; i++) {
                String nextWord = Words[i];
                int difCount = 0;
                for (int j = 0; j < nextWord.length(); j++) {
                    if (nowWord.charAt(j) != nextWord.charAt(j)) {
                        difCount++;
                    }
                    if (difCount >= 2) break;
                }
                if (difCount == 1 && !visited[i]) {
                    visited[i] = true;
                    count++;
                    dfs(nextWord, count);
                    visited[i] = false;
                    count--;
                }
            }
        }
    }
}
