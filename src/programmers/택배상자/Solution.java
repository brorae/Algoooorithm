package programmers.택배상자;

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        int len = order.length;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= len; i++) {
            q.add(i);
        }

        boolean isMovedSt = false;
        int index = 0;
        Stack<Integer> st = new Stack<>();
        while (true) {
            if (!st.isEmpty() && st.peek() == order[index]) {
                answer++;
                index++;
                st.pop();
                isMovedSt = true;
            } else {
                isMovedSt = false;
            }
            if (!q.isEmpty()) {
                if (q.peek() == order[index]) {
                    answer++;
                    index++;
                    q.poll();
                } else {
                    st.add(q.poll());
                }
            } else {
                if (!isMovedSt) {
                    break;
                }
            }

            if (q.isEmpty() && st.isEmpty()) {
                break;
            }
        }

        return answer;
    }
}

