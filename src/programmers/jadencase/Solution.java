package programmers.jadencase;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean prevWordIsSpace = true;
        for(int i=0;i<s.length();i++) {
            if (prevWordIsSpace) {
                sb.append(String.valueOf(s.charAt(i)).toUpperCase());
                prevWordIsSpace = false;
            } else {
                sb.append(String.valueOf(s.charAt(i)).toLowerCase());
            }
            if (s.charAt(i) == ' ') {
                prevWordIsSpace = true;
            }
        }
        return sb.toString();
    }
}
