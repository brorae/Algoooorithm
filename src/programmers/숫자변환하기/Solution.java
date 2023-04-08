package programmers.숫자변환하기;

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;
        int[] arr = new int[y+1];
        Arrays.fill(arr, 1000001);
        if (x*2 <= y) {
            arr[x*2] = 1;
        }
        if (x*3 <= y) {
            arr[x*3] = 1;
        }
        if (x+n <= y) {
            arr[x+n] = 1;
        }
        for (int i=x+1;i<=y;i++) {
            if (i/2 > x && i%2==0) {
                arr[i] = Math.min(arr[i], arr[i/2]+1);
            }
            if (i/3 > x && i%3 == 0) {
                arr[i] = Math.min(arr[i], arr[i/3]+1);
            }
            if (i-n > x) {
                arr[i] = Math.min(arr[i], arr[i-n]+1);
            }
        }
        if (arr[y] == 1000001) {
            return -1;
        }
        return arr[y];
    }
}
