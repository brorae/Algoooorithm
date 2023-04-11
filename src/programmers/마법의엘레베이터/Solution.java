package programmers.마법의엘레베이터;

class Solution {

    public int solution(int storey) {
        int answer = 0;

        while(storey > 0) {
            int mod = storey % 10;
            storey /= 10;
            if (mod > 5 || (mod == 5 && storey % 10 >= 5)) {
                storey += 1;
                answer += 10 - mod;
            } else {
                answer += mod;
            }
        }
        return answer;
    }
}
