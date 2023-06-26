package programmers.할인행사;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
//        System.out.println(Solution.solution(new String[]{"banana", "apple", "rice", "pork", "pot"}, new int[]{3,2,2,2,1}, new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}));
        System.out.println(Solution.solution(
                new String[]{"apple", "banana"},
                new int[]{9,1},
                new String[]{"banana", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "banana", "chicken", "apple"}));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String s : want) {
            map.put(s, 0);
        }

        for (int i = 0; i < 10; i++) {
            String product = discount[i];
            map.put(product, map.getOrDefault(product, 0) + 1);
        }

        int index = -1;
        while (index + 10 < discount.length) {
            if (index >= 0) {
                String minusProduct = discount[index];
                String plusProduct = discount[index + 10];
                map.put(minusProduct, map.get(minusProduct) - 1);
                map.put(plusProduct, map.getOrDefault(plusProduct,0) + 1);
            }

            boolean isMatched = true;
            for (int i = 0; i < want.length; i++) {
                String product = want[i];
                int count = number[i];
                if (map.get(product) != count) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched) {
                answer++;
            }
            index++;
        }

        return answer;
    }
}
