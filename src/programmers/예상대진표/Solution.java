package programmers.예상대진표;

class Solution
{
    public int solution(int n, int a, int b)
    {
        return solve(n, a, b);
    }

    public int solve(int n, int a, int b) {
        int half = n/2;
        int min = Math.min(a,b);
        int max = Math.max(a,b);

        if (min <= half && max > half) {
            int count = 1;
            int k = 2;
            while(true) {
                if (n / k == 1) break;
                k *= 2;
                count++;
            }
            return count;
        } else if (min <= half && max <= half) {
            return solve(half, a, b);
        } else {
            return solve(half, min-half, max-half);
        }
    }
}
