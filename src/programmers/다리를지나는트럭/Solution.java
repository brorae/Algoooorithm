package programmers.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int nextTruckIndex = 0;
        int capacity = 0;
        int time = 0;
        Queue<Truck> q = new LinkedList<>();
        while (true) {
            if (nextTruckIndex == truck_weights.length && q.isEmpty()) {
                break;
            }
            time++;
            while (!q.isEmpty()) {
                Truck now = q.peek();
                if (now.start + bridge_length == time) {
                    capacity -= now.weight;
                    q.poll();
                } else {
                    break;
                }
            }
            if (nextTruckIndex < truck_weights.length && capacity + truck_weights[nextTruckIndex] <= weight) {
                capacity += truck_weights[nextTruckIndex];
                q.add(new Truck(truck_weights[nextTruckIndex], time));
                nextTruckIndex++;
            }
        }

        return time;
    }
}

class Truck {
    int weight;
    int start;

    public Truck(int weight, int start) {
        this.weight = weight;
        this.start = start;
    }
}
