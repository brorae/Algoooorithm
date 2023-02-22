package boj.p10610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    static Integer[] nums;
    static String result = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine();

        char[] chars = N.toCharArray();
        nums = new Integer[chars.length];

        for (int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - '0';
        }

        Arrays.sort(nums, Comparator.reverseOrder());

        if (nums[nums.length - 1] == 0) {
            int sum = Arrays.stream(nums)
                    .mapToInt(it -> it)
                    .sum();
            if (sum % 3 == 0) {
                result = Arrays.stream(nums)
                        .map(String::valueOf)
                        .collect(Collectors.joining(""));
            }
        }
        bw.write(result);
        bw.flush();
        bw.close();
        br.close();
    }
}
