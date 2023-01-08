package kakao2023.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    static int[] percent = {40, 30, 20, 10};
    static int[] result;
    static List<int[]> combination = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        int count = emoticons.length;
        result = new int[count];
        dfs(0, count);
        List<User> userList = Arrays.stream(users)
                .map(it -> new User(it[0], it[1]))
                .collect(Collectors.toList());
        List<Result> results = new ArrayList<>();

        for (int[] c : combination) {
            int buy = 0;
            int price = 0;
            for (User user : userList) {
                int sum = 0;
                for (int i = 0; i < count; i++) {
                    if (c[i] >= user.percent) {
                        sum += (emoticons[i] * (100 - c[i]) / 100);
                    }
                }
                if (sum >= user.price) {
                    buy++;
                } else {
                    price += sum;
                }
            }
            results.add(new Result(buy, price));
        }

        results.sort((a, b) -> {
            if (a.count == b.count) {
                return b.price - a.price;
            }
            return b.count - a.count;
        });

        return new int[]{results.get(0).count, results.get(0).price};
    }

    void dfs(int a, int count) {
        if (a == count) {
            combination.add(result.clone());
            return;
        }
        for (int i = 0; i < 4; i++) {
            result[a] = percent[i];
            dfs(a + 1, count);
        }
    }

    public static void main(String[] args) {
        float a = 1000.1F;
        System.out.println(a);
    }
}

class User {
    int percent;
    int price;

    public User(int percent, int price) {
        this.percent = percent;
        this.price = price;
    }
}

class Result {
    int count;
    int price;

    public Result(int count, int price) {
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Result{" +
                "count=" + count +
                ", price=" + price +
                '}';
    }
}
