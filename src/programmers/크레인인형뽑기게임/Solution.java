package programmers.크레인인형뽑기게임;

import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> basket = new Stack<>();

        int answer = 0;

        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int column = moves[i] - 1;
                int item = board[j][column];
                if (item != 0) {
                    if (basket.empty()) {
                        basket.push(item);
                        board[j][column] = 0;
                        break;
                    }
                    if (basket.peek() == item) {
                        answer+=2;
                        basket.pop();
                    }
                    else {
                        basket.push(item);
                    }
                    board[j][column] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}