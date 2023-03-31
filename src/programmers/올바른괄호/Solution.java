package programmers.올바른괄호;

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> st = new Stack<>();

        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.add(s.charAt(i));
            } else if (c == ')') {
                if (st.isEmpty() || st.pop() != '(') {
                    answer = false;
                    break;
                }
            }
        }
        if (!st.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}

