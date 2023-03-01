package programmers.네트워크;

class Solution {

    static int n;
    static int[][] computers;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        Solution.n = n;
        Solution.computers = computers;
        visited = new boolean[n];
        for (int i = 0; i < Solution.n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }

        return answer;
    }

    void dfs(int index) {
        visited[index] = true;
        for (int i = 0; i < n; i++) {
            if (i != index && computers[index][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5,
                new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}));
    }
}
