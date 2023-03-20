package boj.p14899;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] board;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p14899/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		nums = new int[N+1];
		for (int i=0;i<=N;i++) {
			nums[i] = i;
		}
		
		for (int i=1;i<=N;i++ ) {
			combination(i);
		}
		
		for (List<Integer> com : combinations) {
			int startScore = 0;
			int linkScore = 0;
			List<Integer> other = new ArrayList<>();
			
			for (int i=1;i<=N;i++) {
				int count = 0;
				for (int j=0;j<com.size();j++) {
					if (i == com.get(j)) {
						count++;
					}
				}
				if (count != 1) {
					other.add(i);
				}
			}
			
			
			for (int i=0;i < com.size();i++) {
				int first = com.get(i);
				for (int j=0;j<com.size();j++) {
					if (i!= j) {
						int second = com.get(j);
						startScore += board[first][second];
					}
				}
			}
			
			for (int i=0;i < other.size();i++) {
				int first = other.get(i);
				for (int j=0;j<other.size();j++) {
					if (i!= j) {
						int second = other.get(j);
						linkScore += board[first][second];
					}
				}
			}
			result = Math.min(result, Math.abs(startScore - linkScore));
		}
		
		bw.write(result + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	static int result = Integer.MAX_VALUE;
	static List<Integer> c = new ArrayList<>();
	static List<List<Integer>> combinations = new ArrayList<>();
	
	public static void combination(int index) {
		c.add(index);
		if (c.size() == N/2) {
			combinations.add(new ArrayList<>(c));
		} else {
			for (int i=index+1;i<=N;i++) {
				combination(i);
			}
		}
		c.remove(c.size() - 1);
	}

}
