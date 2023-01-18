package boj.p3425;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Main {

    public static final int MAX = 1000000000;
    static List<String> commands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/boj/p3425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            commands = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                commands.add(line);
                if (line.equals("QUIT")) {
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                if (line.equals("END")) {
                    break;
                }
            }
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                try {
                    long num = Long.parseLong(br.readLine());
                    Stack<Long> st = new Stack<>();
                    st.add(num);
                    for (int j = 0; j < commands.size(); j++) {
                        String command = commands.get(j);
                        if (command.startsWith("NUM")) {
                            Long value = Long.valueOf(command.split(" ")[1]);
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        } else if (command.equals("POP")) {
                            st.pop();
                        } else if (command.equals("INV")) {
                            Long pop = st.pop();
                            st.add(-1 * pop);
                        } else if (command.equals("DUP")) {
                            st.add(st.peek());
                        } else if (command.equals("SWP")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            st.add(first);
                            st.add(second);
                        } else if (command.equals("ADD")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            long value = second + first;
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        } else if (command.equals("SUB")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            long value = second - first;
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        } else if (command.equals("MUL")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            long value = second * first;
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        } else if (command.equals("DIV")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            long value = second / first;
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        } else if (command.equals("MOD")) {
                            Long first = st.pop();
                            Long second = st.pop();
                            long value = second % first;
                            if (Math.abs(value) > MAX) {
                                throw new IllegalArgumentException();
                            }
                            st.add(value);
                        }
                    }
                    if (st.size() != 1) {
                        bw.write("ERROR\n");
                    } else {
                        bw.write(st.peek() + "\n");
                    }
                } catch (EmptyStackException | ArithmeticException | IllegalArgumentException e) {
                    bw.write("ERROR\n");
                }
            }
            bw.write("\n");
            br.readLine();
        }
    }
}
