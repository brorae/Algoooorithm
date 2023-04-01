package programmers.괄호회전하기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            q.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (canMake(new LinkedList<>(q))) {
                answer++;
            }
            q.add(q.poll());
        }
        return answer;
    }

    public boolean canMake(Queue<Character> q) {
        Stack<Character> st = new Stack<>();
        while (!q.isEmpty()) {
            Character now = q.poll();
            if (now == '(' || now == '[' || now == '{') {
                st.add(now);
            } else {
                if (!st.isEmpty()) {
                    if (now == ')' && st.peek() == '(') {
                        st.pop();
                    } else if (now == ']' && st.peek() == '[') {
                        st.pop();
                    } else if (now == '}' && st.peek() == '{') {
                        st.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (st.isEmpty()) {
            return true;
        }
        return false;
    }
}
