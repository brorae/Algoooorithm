package programmers.가장긴펠린드롬;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        char[] c = s.toCharArray();

        int stIndex = 0;
        int index = 1;
        int len = 1;
        while(stIndex < c.length) {
            int left = stIndex - index; // 0
            int right = stIndex + index; // 2
            if (left < 0 || right > c.length - 1 || (c[left] != c[right])) {
                answer = Math.max(answer, len);
                stIndex++;
                index = 1;
                len = 1;
            } else if (c[left] == c[right]) {
                len += 2;
                index++;
            }
        }

        stIndex = 1;
        index = 0;
        len = 0;
        while(stIndex < c.length) {
            int left = stIndex - 1 - index;
            int right = stIndex + index;
            if (left < 0 || right > c.length - 1 || (c[left] != c[right])) {
                answer = Math.max(answer, len);
                stIndex++;
                index = 0;
                len = 0;
            } else if (c[left] == c[right]) {
                len += 2;
                index++;
            }
        }
        return answer;
    }
}
