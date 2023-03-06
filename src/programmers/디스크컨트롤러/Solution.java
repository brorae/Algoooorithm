package programmers.디스크컨트롤러;

import java.util.PriorityQueue;

public class Solution {

    static PriorityQueue<Task> pqByTime;
    static PriorityQueue<Task> pq;

    public int solution(int[][] jobs) {
        pqByTime = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        pq = new PriorityQueue<>((o1, o2) -> o1.working - o2.working);

        for (int[] job : jobs) {
            pqByTime.add(new Task(job[0], job[1]));
        }

        int totalTime = 0;
        int count = 0;
        int finishedTime = 0;
        while (count < jobs.length) {

            while (!pqByTime.isEmpty() && pqByTime.peek().start <= finishedTime) {
                pq.add(pqByTime.poll());
            }

            if (!pq.isEmpty()) {
                Task now = pq.poll();
                totalTime += finishedTime + now.working - now.start;
                finishedTime += now.working;
                count++;
            } else {
                if (!pqByTime.isEmpty()) {
                    finishedTime = pqByTime.peek().start;
                }
            }
        }
        return totalTime / jobs.length;
    }
}

class Task {
    int start;
    int working;

    public Task(int start, int working) {
        this.start = start;
        this.working = working;
    }

    @Override
    public String toString() {
        return "Task{" +
                "start=" + start +
                ", working=" + working +
                '}';
    }
}
