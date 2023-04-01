package programmers.구명보트;

class Solution {
    public int solution(int[] people, int limit) {
        int[] weights = new int[241];

        for (int i=0;i<people.length;i++) {
            weights[people[i]] += 1;
        }


        int count = 0;
        for (int i=0;i<people.length;i++) {
            if (weights[people[i]] == 0) continue;
            weights[people[i]] -= 1;
            int tmp = limit - people[i];
            for (int j=tmp;j>=40;j--) {
                if (weights[j] != 0) {
                    weights[j] -= 1;
                    break;
                }
            }

            count++;
        }

        return count;
    }
}
