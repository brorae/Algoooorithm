package kakaotechintern2022.p2;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int maxCount = queue1.length * 2 * 2;
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long queue1sum = 0;
        long queue2sum = 0;
        for (int i : queue1) {
            q1.add(i);
            queue1sum += i;
        }
        for (int i : queue2) {
            q2.add(i);
            queue2sum += i;
        }
        while (queue1sum != queue2sum) {
            if (queue1sum == 0 || queue2sum == 0 || answer > maxCount) {
                return -1;
            }
            if (queue1sum > queue2sum) {
                Integer first = q1.poll();
                q2.add(first);
                queue1sum -= first;
                queue2sum += first;
                answer++;
            }
            if (queue1sum < queue2sum) {
                Integer first = q2.poll();
                q1.add(first);
                queue2sum -= first;
                queue1sum += first;
                answer++;
            }
        }
        return answer;
    }
}
