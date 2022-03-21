package programmers.키패드누르기;

public class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int leftNumber = 10;
        int rightNumber = 12;
        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                answer += "L";
                leftNumber = number;
            } else if (number == 3 || number == 6 || number == 9) {
                answer += "R";
                rightNumber = number;
            } else {
                int leftDistance = getDistance(leftNumber,number);
                int rightDistance = getDistance(rightNumber,number);
                if (leftDistance < rightDistance) {
                    answer += "L";
                    leftNumber = number;
                }
                else if (leftDistance > rightDistance) {
                    answer += "R";
                    rightNumber = number;
                }
                else {
                    if (hand.equals("right")) {
                        answer += "R";
                        rightNumber = number;
                    }
                    else {
                        answer += "L";
                        leftNumber = number;
                    }
                }
            }
        }
        return answer;
    }
    public static int getDistance(int number1, int number2) {
        if (number1 == 0) {
            number1 = 11;
        }
        if (number2 == 0) {
            number2 = 11;
        }
        int x = (number1-1) / 3; //0,1  //
        int y = (number1-1) % 3;
        int numberX = number2/3; //2,1
        int numberY = 1;
        return Math.abs(x-numberX) + Math.abs(y-numberY);
    }
}
