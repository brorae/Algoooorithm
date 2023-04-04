package boj.p17140;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int r,c,k;
	static int row = 3;
	static int col = 3;
	static int[][] board;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p17140/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		board = new int[101][101];
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (int i=1;i<=3;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=3;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time=0;
		while(true) {
			if (board[r][c] == k) break;
			if (time == 101) break;
			
			
			if (isRowTurn()) {
				int maxCol = 0;
				for(int i=1;i<=row;i++) {
					Map<Integer, Integer> map = new HashMap<>();
					for (int j=1;j<=col;j++) {
						if (board[i][j] != 0) {
							map.put(board[i][j], map.getOrDefault(board[i][j], 0) + 1);
						}
					}
					PriorityQueue<Num> pq = new PriorityQueue<>((o1,o2) -> {
						if (o1.count == o2.count) {
							return o1.value - o2.value;
						}
						return o1.count - o2.count;
					});
					
					for (Integer key : map.keySet()) {
						int value = map.get(key);
						pq.add(new Num(key, value));
					}
					int qSize = pq.size();
					maxCol = Math.max(maxCol, qSize * 2);
					if (qSize * 2 < col) {
						for (int j=qSize * 2 + 1;j<=col;j++) {
							board[i][j] = 0;
						}
					}
					int index = 1;
					while(!pq.isEmpty()) {
						if (index > 99) break;
						Num now = pq.poll();
						board[i][index] = now.value;
						board[i][index+1] = now.count;
						index += 2;
					}
				}
				if (maxCol > 100) {
					col = 100;
				} else {
					col = maxCol;
				}
			} else {
				int maxRow = 0;
				for(int i=1;i<=col;i++) {
					Map<Integer, Integer> map = new HashMap<>();
					for (int j=1;j<=row;j++) {
						if (board[j][i] != 0) {
							map.put(board[j][i], map.getOrDefault(board[j][i], 0) + 1);
						}
					}
					PriorityQueue<Num> pq = new PriorityQueue<>((o1,o2) -> {
						if (o1.count == o2.count) {
							return o1.value - o2.value;
						}
						return o1.count - o2.count;
					});
					
					for (Integer key : map.keySet()) {
						int value = map.get(key);
						pq.add(new Num(key, value));
					}
					int qSize = pq.size();
					maxRow = Math.max(maxRow, qSize * 2);
					if (qSize * 2 < row) {
						for (int j=qSize * 2 + 1;j<=row;j++) {
							board[j][i] = 0;
						}
					}
					int index = 1;
					while(!pq.isEmpty()) {
						if (index > 99) break;
						Num now = pq.poll();
						board[index][i] = now.value;
						board[index+1][i] = now.count;
						index += 2;
					}
				}
				if (maxRow > 100) {
					row = 100;
				} else {
					row = maxRow;
				}
			}
			time++;
		}
		if (time == 101) {
			bw.write("-1");
		} else {
			bw.write(time + "");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean isRowTurn() {
		return row >= col;
	}
}

class Num {
	int value;
	int count;
	
	public Num(int value, int count) {
		this.value = value;
		this.count = count;
	}
}
