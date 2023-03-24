package programmers.이중우선순위큐;

import java.util.PriorityQueue;

class Solution {

    static PriorityQueue<Num> min = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
    static PriorityQueue<Num> max = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
    static boolean[] visited;

    public int[] solution(String[] operations) {
        min.remove(1);
        visited = new boolean[operations.length];
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            String[] split = operation.split(" ");
            String cmd = split[0];
            int num = Integer.parseInt(split[1]);
            if (cmd.equals("I")) {
                min.add(new Num(i, num));
                max.add(new Num(i, num));
            } else if (cmd.equals("D")) {
                if (num == -1) {
                    while (true) {
                        if (min.isEmpty()) {
                            break;
                        }
                        Num poll = min.poll();
                        if (!visited[poll.index]) {
                            visited[poll.index] = true;
                            break;
                        }
                    }
                } else if (num == 1) {
                    while (true) {
                        if (max.isEmpty()) {
                            break;
                        }
                        Num poll = max.poll();
                        if (!visited[poll.index]) {
                            visited[poll.index] = true;
                            break;
                        }
                    }
                }
            }
        }

        int maxResult = Integer.MIN_VALUE;
        int minResult = Integer.MAX_VALUE;

        if (min.size() < max.size()) {
            while (!min.isEmpty()) {
                Num poll = min.poll();
                if (!visited[poll.index]) {
                    maxResult = Math.max(maxResult, poll.value);
                    minResult = Math.min(minResult, poll.value);
                }
            }
        } else {
            while (!max.isEmpty()) {
                Num poll = max.poll();
                if (!visited[poll.index]) {
                    maxResult = Math.max(maxResult, poll.value);
                    minResult = Math.min(minResult, poll.value);
                }
            }
        }
        if (maxResult == Integer.MIN_VALUE) {
            maxResult = 0;
        }
        if (minResult == Integer.MAX_VALUE) {
            minResult = 0;
        }
        return new int[]{maxResult, minResult};
    }
}

class Num {
    int index;
    int value;

    public Num(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
