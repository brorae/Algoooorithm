package programmers.여행경로;

import java.util.ArrayList;
import java.util.List;

class Solution {

    static String[][] Tickets;
    static boolean[] visited;
    static List<String> result;

    public String[] solution(String[][] tickets) {
        Tickets = tickets;
        visited = new boolean[tickets.length];
        result = new ArrayList<>();

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                visited = new boolean[tickets.length];
                dfs(i, 0, new ArrayList<>());
            }
        }

        System.out.println(result);
        return result.toArray(String[]::new);
    }

    void dfs(int edgeIndex, int visitCount, List<String> path) {
        String[] nowTicket = Tickets[edgeIndex];
        path.add(nowTicket[0]);
        visited[edgeIndex] = true;
        visitCount++;
        if (visitCount == Tickets.length) {
            path.add(nowTicket[1]);
            if (!result.isEmpty()) {
                result = compare(result, new ArrayList<>(path));
            } else {
                result = new ArrayList<>(path);
            }
            path.remove(path.size()-1);
        } else {
            for (int i = 0; i < Tickets.length; i++) {
                String[] nextTicket = Tickets[i];
                if (!visited[i] && nowTicket[1].equals(nextTicket[0])) {
                    dfs(i, visitCount, path);
                }
            }
        }
        path.remove(path.size() - 1);
        visited[edgeIndex] = false;
        visitCount--;
    }

    List<String> compare(List<String> a, List<String> b) {
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i).compareTo(b.get(i)) > 0) {
                return b;
            } else if (a.get(i).compareTo(b.get(i)) < 0){
                return a;
            }
        }
        return a;
    }
}
