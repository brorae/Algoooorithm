package programmers.징검다리건너기;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
    }

    public static int solution(int[] stones, int k) {

        PriorityQueue<Bridge> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        for (int i = 0; i < k; i++) {
            pq.add(new Bridge(i, stones[i]));
        }

        // 2, 4, 5, 3, 2, 1, 4, 2, 5, 1

        int min = pq.peek().value;
        for (int i = 0; i < stones.length - k; i++) {
            pq.add(new Bridge(k + i, stones[k + i]));
            while (!pq.isEmpty() && i >= pq.peek().index) {
                pq.poll();
            }
            min = Math.min(min, pq.peek().value);
        }

        return min;
    }

//    public int solution(int[] stones, int k) {
//        int answer = 0;
//
//        int max = 0;
//        for (int i=0;i<stones.length;i++) {
//            max = Math.max(stones[i], max);
//        }
//
//        int l = 1;
//        int r = max;
//        while(l <= r) {
//            boolean canGo = true;
//            int zeroCount = 0;
//            int mid = (l+r)/2;
//            for (int i=0;i<stones.length;i++) {
//                if (stones[i] - mid < 0) zeroCount++;
//                else zeroCount = 0;
//                if (zeroCount == k) {
//                    canGo = false;
//                    break;
//                }
//            }
//
//            if (canGo) {
//                l = mid+1;
//                answer = Math.max(answer, mid);
//            } else {
//                r = mid-1;
//            }
//        }
//
//        return answer;
//    }
}

class Bridge {
    int index;
    int value;

    public Bridge(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Bridge{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }
}
