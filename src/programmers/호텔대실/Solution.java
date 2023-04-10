package programmers.호텔대실;

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[][] booking = new int[24][60];

        for (String[] time : book_time) {
            String[] from = time[0].split(":");
            String[] to = time[1].split(":");
            int fromH = Integer.parseInt(from[0]);
            int fromM = Integer.parseInt(from[1]);
            int toH = Integer.parseInt(to[0]);
            int toM = Integer.parseInt(to[1]);

            if (fromH != toH) {
                for (int i=fromM;i<60;i++) {
                    booking[fromH][i] += 1;
                }

                for (int i=fromH+1;i<toH;i++) {
                    for (int j=0;j<60;j++) {
                        booking[i][j] += 1;
                    }
                }

                for (int i=0;i<toM + 10;i++) {
                    if (i >= 60) {
                        if (toH+1 < 24) {
                            booking[toH+1][i-60] += 1;
                        }
                    } else {
                        booking[toH][i] += 1;
                    }
                }
            } else {
                for (int i=fromM;i<toM + 10;i++) {
                    if (i >= 60) {
                        if (toH+1 < 24) {
                            booking[toH+1][i-60] += 1;
                        }
                    } else {
                        booking[fromH][i] += 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i=0;i<24;i++) {
            for (int j=0;j<60;j++) {
                answer = Math.max(answer, booking[i][j]);
            }
        }

        return answer;
    }
}
