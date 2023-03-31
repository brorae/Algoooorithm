package programmers.영어끝말잇기;

import java.util.*;

class Solution {

    public int[] solution(int n, String[] words) {
        Stack<String> st = new Stack<>();
        int num = 0;
        int turn = 0;
        st.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            String prevWord = st.peek();
            String nowWord = words[i];
            if (st.contains(nowWord)) {
                num = i % n + 1;
                turn = i / n + 1;
                break;
            }
            if (prevWord.charAt(prevWord.length() - 1) == words[i].charAt(0)) {
                st.add(nowWord);
            } else {
                num = i % n + 1;
                turn = i / n + 1;
                break;
            }
        }
        return new int[]{num, turn};
    }
}
