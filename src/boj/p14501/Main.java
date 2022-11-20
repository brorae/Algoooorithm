package boj.p14501;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedules.add(new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < N; i++) {
            Schedule schedule = schedules.get(i);
            if (i + schedule.day > N) {
                continue;
            }
            if (i == 0) {
                dp[schedule.day + i] = Math.max(dp[schedule.day + i], schedule.pay);
            }
            if (i != 0) {
                int[] cutArray = Arrays.copyOfRange(dp, 1, i + 1);
                int max = Arrays.stream(cutArray).max().getAsInt();
                dp[schedule.day + i] = Math.max(dp[schedule.day + i], max + schedule.pay);
            }
        }

        int result = Arrays.stream(dp).max().getAsInt();
        bw.write(result + "");
        bw.flush();

        bw.close();
        br.close();
    }
}

class Schedule {
    int day;
    int pay;
    boolean possible;

    public Schedule(int day, int pay) {
        this.day = day;
        this.pay = pay;
        this.possible = true;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "day=" + day +
                ", pay=" + pay +
                '}';
    }
}
