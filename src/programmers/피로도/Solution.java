package programmers.피로도;

class Solution {

    static int[][] DUNGEONS;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
    }

    public int solution(int k, int[][] dungeons) {
        DUNGEONS = dungeons;
        visited = new boolean[dungeons.length];
        for (int i = 0; i < dungeons.length; i++) {
            int[] dungeon = DUNGEONS[i];
            int in = dungeon[0];
            if (k >= in) {
                dfs(k, i, 0);
            }
        }

        return answer;
    }

    void dfs(int k, int index, int count) {
        visited[index] = true;
        count++;
        answer = Math.max(answer, count);
        int[] dungeon = DUNGEONS[index];
        int out = dungeon[1];
        k -= out;
        for (int i = 0; i < DUNGEONS.length; i++) {
            int[] nextDungeon = DUNGEONS[i];
            int nextIn = nextDungeon[0];
            if (!visited[i] && k >= nextIn) {
                dfs(k, i, count);
            }
        }
        count--;
        visited[index] = false;
    }
}
