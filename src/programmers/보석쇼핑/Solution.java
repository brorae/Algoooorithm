package programmers.보석쇼핑;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String string : gems) {
            set.add(string);
        }

        Section section = new Section(0, 100001);
        Map<String, Integer> map = new HashMap<>();
        int pointerA = 0;
        int pointerB = 0;
        while (pointerB < gems.length + 1) {
            if (pointerB == gems.length) {
                if (set.size() != map.keySet().size()) {
                    break;
                } else {
                    Section news = new Section(pointerA + 1, pointerB);
                    if (section.getGap() > news.getGap()) {
                        section = news;
                    }
                    map.put(gems[pointerA], map.get(gems[pointerA]) - 1);
                    if (map.get(gems[pointerA]) == 0) {
                        map.remove(gems[pointerA]);
                    }
                    pointerA++;
                }
            } else {
                if (set.size() != map.keySet().size()) {
                    map.put(gems[pointerB], map.getOrDefault(gems[pointerB], 0) + 1);
                    pointerB++;
                } else {
                    Section news = new Section(pointerA + 1, pointerB);
                    if (section.getGap() > news.getGap()) {
                        section = news;
                    }
                    map.put(gems[pointerA], map.get(gems[pointerA]) - 1);
                    if (map.get(gems[pointerA]) == 0) {
                        map.remove(gems[pointerA]);
                    }
                    pointerA++;
                }
            }

        }

        return new int[]{section.from, section.to};
    }
}

class Section {
    int from;
    int to;

    public Section(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getGap() {
        return to - from;
    }
}
