package programmers.신규아이디추천;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^a-z0-9-_\\.]", "");

        temp = temp.replaceAll("[\\.]{2,}", ".");

        temp = temp.replaceAll("^[\\.]|[\\.]$", "");

        if (temp.isBlank()) {
            temp += "a";
        }

        if (temp.length() >= 16) {
            temp = temp.substring(0, 15);
            temp = temp.replaceAll("[.]$", "");
        }

        if (temp.length() <= 2) {
            while (temp.length() < 3) {
                temp += temp.charAt(temp.length() - 1);
            }
        }

        answer = temp;
        System.out.println(answer);
        return answer;
    }
}
