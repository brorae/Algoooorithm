package boj.p9093;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        for (int i = 0; i < Integer.parseInt(number); i++) {
            String text = scanner.nextLine();
            text += ' ';
            System.out.println(reverse(text));
        }
    }

    public static String reverse(String text) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                while(!stack.isEmpty()) {
                    sb.append(stack.peek());
                    stack.pop();
                }
                sb.append(' ');
            }
            else {
                stack.push(text.charAt(i));
            }
        }
        return sb.toString();
    }
}
