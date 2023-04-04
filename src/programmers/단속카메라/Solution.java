package programmers.단속카메라;

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        List<Path> paths = new ArrayList<>();
        for (int[] route : routes) {
            int start = route[0];
            int end = route[1];
            paths.add(new Path(start, end));
        }

        Collections.sort(paths, (o1,o2) -> o1.end - o2.end);

        int std = paths.get(0).end;
        int count = 1;
        for (int i=1;i<paths.size();i++) {
            if (std >= paths.get(i).start) continue;
            else {
                std  = paths.get(i).end;
                count++;
            }
        }
        return count;
    }
}

class Path {
    int start;
    int end;

    public Path (int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "start : " + start + " end : " + end;
    }
}
