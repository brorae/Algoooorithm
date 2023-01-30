package boj.p1713;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Student[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        students = new Student[101];

        for (int i = 0; i <= 100; i++) {
            students[i] = new Student(i, 0, 0, false);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            Student student = students[num];
            if (student.isIn) {
                students[num].count++;
            } else {
                long pictureCount = Arrays.stream(students)
                        .filter(it -> it.isIn)
                        .count();
                if (pictureCount == N) {
                    Student foundStudent = Arrays.stream(students, 1, students.length)
                            .filter(it -> it.isIn)
                            .sorted((o1, o2) -> {
                                if (o1.count == o2.count) {
                                    return o1.timeStamp - o2.timeStamp;
                                }
                                return o1.count - o2.count;
                            })
                            .findFirst()
                            .get();
                    Student remove = students[foundStudent.num];
                    remove.count = 0;
                    remove.timeStamp = 0;
                    remove.isIn = false;
                }
                students[num] = new Student(num, 1, i, true);
            }
        }

        Arrays.stream(students, 1, students.length)
                .filter(it -> it.isIn)
                .sorted((Comparator.comparingInt(o -> o.num)))
                .map(it -> it.num)
                .forEach(it -> {
                    try {
                        bw.write(it + " ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        bw.flush();

        bw.close();
        br.close();
    }

    static class Student {
        int num;
        int count;
        int timeStamp;
        boolean isIn;

        public Student(int num, int count, int timeStamp, boolean isIn) {
            this.num = num;
            this.count = count;
            this.timeStamp = timeStamp;
            this.isIn = isIn;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "index=" + num +
                    ", count=" + count +
                    ", timeStamp=" + timeStamp +
                    ", isIn=" + isIn +
                    '}';
        }
    }
}
