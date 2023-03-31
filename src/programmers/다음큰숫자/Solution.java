package programmers.다음큰숫자;

class Solution {
    public int solution(int n) {
        int oneCount = 0;
        String num = Integer.toBinaryString(n);
        for(int i=0;i<num.length();i++) {
            if (num.charAt(i) == '1') {
                oneCount++;
            }
        }

        while(true) {
            int newOneCount = 0;
            String newNum = Integer.toBinaryString(++n);
            for(int i=0;i<newNum.length();i++) {
                if (newNum.charAt(i) == '1') {
                    newOneCount++;
                }
            }
            if (oneCount == newOneCount) break;
        }

        return n;
    }
}
