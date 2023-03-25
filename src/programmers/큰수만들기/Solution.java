package programmers.큰수만들기;

class Solution {

//    static PriorityQueue<Num> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
    static StringBuilder sb = new StringBuilder();

    public static String solution(String number, int k) {
        int len = number.length();
        int index = 0;
        int max;
        for (int i = 0; i < len - k; i++) {
            max = 0;
            for (int j = index; j <= i + k; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    index = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }

//    public static String solution(String number, int k) {
//        int len = number.length();
//
//        char[] c = number.toCharArray();
//
//        for (int i = 0; i < k + 1; i++) {
//            pq.add(new Num(i, c[i] - '0'));
//        }
//
//        int start = 0;
//        int end = k;
//        while (end < len) {
//            Num poll = pq.poll();
//            while (!pq.isEmpty() && (poll.index < start || poll.index > end)) {
//                poll = pq.poll();
//            }
//            sb.append(poll.value);
//            start = poll.index + 1;
//            end = end + 1;
//            if (end < len) {
//                pq.add(new Num(end, c[end] - '0'));
//            }
//        }
//        return sb.toString();
//    }
}

//class Num {
//    int index;
//    int value;
//
//    public Num(int index, int value) {
//        this.index = index;
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return "Num{" +
//                "index=" + index +
//                ", value=" + value +
//                '}';
//    }
//}
