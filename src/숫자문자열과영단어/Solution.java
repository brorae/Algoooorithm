package 숫자문자열과영단어;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] alphabet  = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for (int i = 0; i < alphabet.length; i++) {
            s = s.replaceAll(alphabet[i],Integer.toString(i));
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}
