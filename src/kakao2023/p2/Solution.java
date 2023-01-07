package kakao2023.p2;

import java.util.Stack;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> D = new Stack<>();
        Stack<Integer> P = new Stack<>();
        for (int delivery : deliveries) {
            D.push(delivery);
        }
        for (int pickup : pickups) {
            P.push(pickup);
        }

        while (!D.isEmpty() && D.peek() == 0) {
            D.pop();
        }

        while (!P.isEmpty() && P.peek() == 0) {
            P.pop();
        }

        while (!(D.isEmpty() && P.isEmpty())) {
            int distance = Math.max(D.size(), P.size());
            answer += distance * 2L;

            int box = 0;
            while (!D.isEmpty() && box <= cap) {
                Integer toDeliverBox = D.pop();
                if (toDeliverBox + box <= cap) {
                    box += toDeliverBox;
                } else {
                    D.push(box + toDeliverBox - cap);
                    break;
                }
            }

            box = 0;
            while (!P.isEmpty() && box <= cap) {
                Integer toDeliverBox = P.pop();
                if (toDeliverBox + box <= cap) {
                    box += toDeliverBox;
                } else {
                    P.push(box + toDeliverBox - cap);
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4, 5, new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}));
    }
}
