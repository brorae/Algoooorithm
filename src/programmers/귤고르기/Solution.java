package programmers.귤고르기;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        System.out.println(Solution.solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3}));
    }

    public static int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Fruit> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);

        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }

        for (Integer key : map.keySet()) {
            pq.add(new Fruit(key, map.get(key)));
        }


        int result = 0;
        while (!pq.isEmpty()) {
            Fruit now = pq.poll();
            if (now.count >= k) {
                result++;
                break;
            }
            k -= now.count;
            result++;
        }

        return result;
    }
}

class Fruit {
    int size;
    int count;

    public Fruit(int size, int count) {
        this.size = size;
        this.count = count;
    }

    @Override
    public String toString() {
        return "{ " + size +", " + count + " }";
    }
}
