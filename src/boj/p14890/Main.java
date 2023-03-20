package boj.p14890;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N,L;
	static int[][] board;
	static int result;
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p14890/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0;i<N;i++) {
			if (canGo(board[i])) result++;
		}
		
		int[] tmp = new int[N];
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				tmp[j] = board[j][i];
			}
			if (canGo(tmp)) result++;
		}
		
		bw.write(result+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean canGo(int[] arr) {
		boolean[] visited = new boolean[N];
		int height = arr[0];
		int continuousCount = 1;
		int index = 1;
		while(index < N) {
			if (arr[index] == height) {
				continuousCount++;
				index++;
			} else if(Math.abs(arr[index] - height) > 1) {
				return false;
			} else if (arr[index] - height == -1){ // 낮아지는 
				int count = 0;
				int lastIndex = 0;
				for (int j=index;j<N;j++) {
					if (arr[j] == height - 1) {
						count++;
						lastIndex = j;
					} else {
						break;
					}
				}
				if (count >= L) {
					for (int j=index;j<index+L;j++) {
						if (visited[j]) return false;
						visited[j] = true;
					}
					height -= 1;
					index = lastIndex + 1;
					continuousCount = count;
				} else {
					return false;
				}
			} else if (arr[index] - height == 1) { // 높아지는
				// L만큼 가능한지 확인 
				if (continuousCount >= L) {
					for (int j=index - L;j<index;j++) {
						if (visited[j]) return false;
						visited[j] = true;
					}
					height += 1;
					index++;
					continuousCount = 1;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
