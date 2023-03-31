package programmers.짝지어제거하기;

import java.util.*;

class Solution {

    public int solution(String s) {
        Stack<Character> st = new Stack<>();
        for (int i=0;i<s.length();i++) {
            if (st.isEmpty()) {
                st.add(s.charAt(i));
            } else {
                if (st.peek() == s.charAt(i)) {
                    st.pop();
                } else {
                    st.add(s.charAt(i));
                }
            }
        }

        if (st.isEmpty()) {
            return 1;
        }
        return 0;
    }

//     public int solution(String s) {
//         while(true) {
//             boolean isFound = false;
//             for(int i=0;i<s.length() - 1;i++) {
//                 if (s.charAt(i) == s.charAt(i+1)) {
//                     if (i == 0) {
//                         s = s.substring(i+2, s.length());
//                     } else {
//                         s = s.substring(0,i) + s.substring(i+2, s.length());
//                     }
//                     isFound = true;
//                     break;
//                 }
//             }
//             if (!isFound) {
//                 break;
//             }
//         }

//         if (s.equals("")) {
//             return 1;
//         }
//         return 0;
//     }
}

