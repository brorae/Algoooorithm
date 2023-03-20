package boj.p14891;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K;
	static LinkedList<Integer>[] arr;
	static int[] directions;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p14891/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		arr = new LinkedList[4];
		
		for (int i=0;i<4;i++) {
			String line = br.readLine();
			LinkedList<Integer> list = new LinkedList<>();
			for (int j=0;j<8;j++) {
				list.add(line.charAt(j) - '0');
			}
			arr[i] = list;
		}
		
		K = Integer.parseInt(br.readLine());
		for (int i=0;i<K;i++) {
			directions = new int[4];
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken()); // 1 : 시계 -1 : 반시
			directions[num-1] = direction;
			
			bfs(num-1);
			
			for (int j=0;j<4;j++) {
				int dir = directions[j];
				LinkedList<Integer> list = arr[j];
				if (dir == -1) { // 시계 
					int first = list.removeFirst();
					list.addLast(first);
				} else if (dir == 1) { // 반시계 
					int last = list.removeLast();
					list.addFirst(last);
				}
			}
		}
		
		int score = 0;
		
		for (int i=0;i<4;i++) {
			LinkedList<Integer> list = arr[i];
			int target = list.get(0);
			if (target == 1) {
				score += Math.pow(2, i);
			}
		}
		
		bw.write(score + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int[] d = {1,-1};
	
	static void bfs(int index) {
		boolean[] visited = new boolean[4];
		Queue<Integer> q = new LinkedList<>();
		visited[index] = true;
		q.add(index);
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int i=0;i<2;i++) {
				int next = now + d[i];
				if (next < 4 && next >= 0 && !visited[next]) {
					int nowValue = 0, nextValue = 0;
					if (i == 0) {
						// 오른쪽 비교
						nowValue = arr[now].get(2);
						nextValue = arr[next].get(6);
					} else {
						// 왼쪽 비교 
						nowValue = arr[now].get(6);
						nextValue = arr[next].get(2);
						
					}
					if (nowValue == nextValue) {
						directions[next] = 0;
					} else {
						directions[next] = directions[now] * -1;
					}
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}
}
