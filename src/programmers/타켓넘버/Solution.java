package programmers.타켓넘버;

public class Solution {

    static int result;
    static int[] d = {-1, 1};

    public int solution(int[] numbers, int target) {
        dfs(0, 0, target, numbers);
        return result;
    }

    public void dfs(int index, int sum, int target, int[] numbers) {
        if (index == numbers.length && sum == target) {
            result++;
            return;
        }
        if (index < numbers.length) {
            for (int i = 0; i < 2; i++) {
                sum += numbers[index] * d[i];
                index++;
                dfs(index, sum, target, numbers);
                index--;
                sum -= numbers[index] * d[i];
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{4, 1, 2, 1}, 4);
    }
}
