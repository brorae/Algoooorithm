package programmers.베스트앨범;

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Album> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            Album album = map.getOrDefault(genres[i], new Album());
            album.add(new Music(i, plays[i]));
            album.totalCount += plays[i];
            map.put(genres[i], album);
        }

        PriorityQueue<Album> apq = new PriorityQueue<>((o1, o2) -> {
            if (o1.totalCount == o2.totalCount) {
                return o2.pq.peek().count - o1.pq.peek().count;
            }
            return o2.totalCount - o1.totalCount;
        });

        for (String key : map.keySet()) {
            apq.add(map.get(key));
        }

        List<Integer> list = new ArrayList<>();
        while (!apq.isEmpty()) {
            Album now = apq.poll();
            int count = 0;
            while (!now.pq.isEmpty()) {
                list.add(now.pq.poll().num);
                count++;
                if (count == 2) {
                    break;
                }
            }
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}

class Album {
    PriorityQueue<Music> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.count == o2.count) {
            return o1.num - o2.num;
        }
        return o2.count - o1.count;
    });
    int totalCount;

    void add(Music music) {
        pq.add(music);
    }

    @Override
    public String toString() {
        return pq + "totalCount : " + totalCount;
    }
}

class Music {
    int num;
    int count;

    public Music(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public String toString() {
        return "[num : " + num + " count : " + count + "]";
    }
}
