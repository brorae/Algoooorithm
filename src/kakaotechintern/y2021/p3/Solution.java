package kakaotechintern.y2021.p3;

import java.util.LinkedList;
import java.util.Stack;

class Solution {

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(
//                solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
//    }

    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder("O".repeat(n));
        Stack<Node> st = new Stack<>();
        int[] pre = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;
        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].length() != 1) {
                String[] s = cmd[i].split(" ");
                String command = s[0];
                int num = Integer.parseInt(s[1]);
                if (command.equals("U")) {
                    while (num-- > 0) {
                        k = pre[k];
                    }
                } else if (command.equals("D")) {
                    while (num-- > 0) {
                        k = next[k];
                    }
                }
            } else if (cmd[i].length() == 1) {
                if (cmd[i].equals("C")) {
                    st.add(new Node(pre[k], k, next[k]));
                    if (next[k] != -1) {
                        pre[next[k]] = pre[k];
                    }
                    if (pre[k] != -1) {
                        next[pre[k]] = next[k];
                    }
                    sb.setCharAt(k, 'X');

                    if (next[k] != -1) {
                        k = next[k];
                    } else {
                        k = pre[k];
                    }
                } else if (cmd[i].equals("Z")) {
                    Node pop = st.pop();
                    if (pop.pre != -1) next[pop.pre] = pop.cur;
                    if (pop.next != -1) pre[pop.next] = pop.cur;
                    sb.setCharAt(pop.cur, 'O');
                }
            }
        }
        return sb.toString();
    }

    static class Node {
        int pre;
        int cur;
        int next;

        public Node(int pre, int cur, int next) {
            this.pre = pre;
            this.cur = cur;
            this.next = next;
        }
    }
}
