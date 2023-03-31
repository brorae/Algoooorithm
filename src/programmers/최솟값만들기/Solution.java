package programmers.최솟값만들기;

import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;
        int pointerA = 0;
        int pointerB = B.length-1;
        while(pointerA < A.length) {
            sum += (A[pointerA] * B[pointerB]);
            pointerA++;
            pointerB--;
        }

        return sum;
    }
}
