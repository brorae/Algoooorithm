package programmers.셔틀버스;

import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {

        System.out.println(Solution.solution(10, 25, 1,
                new String[]{"09:00", "09:10", "09:20", "09:30", "09:40", "09:50", "10:00", "10:10", "10:20", "10:30",
                        "10:40", "10:50"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Time> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.h == o2.h) {
                return o1.ms - o2.ms;
            }
            return o1.h - o2.h;
        });

        Time start = new Time(9, 0);
        Time end = start.plus((n - 1) * t);
        for (String time : timetable) {
            int h = Integer.parseInt(time.split(":")[0]);
            int ms = Integer.parseInt(time.split(":")[1]);
            Time next = new Time(h, ms);
            if (next.isFasterThan(end)) {
                pq.add(new Time(h, ms));
            }
        }

        Time last = null;
        int go = 0;
        int count = 0;
        while (!pq.isEmpty() && go < n) {
            Time next = pq.peek();
            if (next.isFasterThan(start)) { //빠르면
                last = pq.poll();
                count++;
            } else { // 느리면
                go++;
                start = start.plus(t);
                count = 0;
            }

            if (count == m) {
                go++;
                if (go == n) {
                    break;
                }
                start = start.plus(t);
                count = 0;
            }
        }

        if (count == m) {
            return last.plus(-1).getTime();
        }
        return end.getTime();
    }
}

class Time {
    int h;
    int ms;

    public Time(int h, int ms) {
        this.h = h;
        this.ms = ms;
    }

    public boolean isFasterThan(Time other) {
        return h < other.h || (h == other.h && ms <= other.ms);
    }

    public Time plus(int minute) {
        int nh = h;
        int nms = ms + minute;
        if (nms >= 60) {
            nh += nms / 60;
            nms = nms % 60;
        }
        if (nms < 0) {
            nh--;
            nms += 60;
            if (nh < 0) {
                nh += 24;
            }
        }
        return new Time(nh, nms);
    }

    public String getTime() {
        StringBuilder sb = new StringBuilder();
        if (h < 10) {
            sb.append("0" + h + ":");
        } else {
            sb.append(h + ":");
        }
        if (ms < 10) {
            sb.append("0" + ms);
        } else {
            sb.append(ms);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Time{" +
                "h=" + h +
                ", ms=" + ms +
                '}';
    }
}

