package kakao2023.p4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        List<String> binary = Arrays.stream(numbers)
                .mapToObj(Long::toBinaryString)
                .map(it -> {
                    for (long i = 0; i < 6; i++) {
                        int nodeCount = (int) (Math.pow(2, i + 1) - 1);
                        if (it.length() == nodeCount) {
                            return it;
                        }
                        if (it.length() < nodeCount) {
                            return "0".repeat(nodeCount - it.length()) + it;
                        }
                    }
                    return it;
                })
                .collect(Collectors.toList());

        System.out.println(binary);
        for (int i = 0; i < binary.size(); i++) {
            String binaryString = binary.get(i);
            answer[i] = bfs(binaryString);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    int bfs(String string) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[string.length()];
        int count = string.length();
        double height = log2(count + 1) - 1;
        int gap = (int) (height - 1);
        int root = count / 2;
        char number = string.charAt(root);
        q.add(new Node(root, number));
        if (number == '0') {
            return 0;
        }
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Node node = q.poll();
                double pow = Math.pow(2, gap);
                int first = (int) (node.index - pow);
                int second = (int) (node.index + pow);
                if (len != Math.pow(2,height) && node.number == '0' && (string.charAt(first) == '1' || string.charAt(second) == '1')) {
                    return 0;
                }
                if (!visited[first]) {
                    q.add(new Node(first, string.charAt(first)));
                    visited[first] = true;
                }
                if (!visited[second]) {
                    q.add(new Node(second, string.charAt(second)));
                    visited[second] = true;
                }
            }
            gap--;
        }
        return 1;
    }

    public static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new long[]{0});
    }
}

class Node {
    int index;
    char number;

    public Node(int index, char number) {
        this.index = index;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", number=" + number +
                '}';
    }
}
