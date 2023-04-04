package boj.p17779;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p17779/input.txt"));
		
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
		
		int result = Integer.MAX_VALUE;
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				for (int k=1;k<=N;k++) {
					for (int l=1;l<=N;l++) {
						if (i+k+l <= N && j - k >= 1 && j + l <= N) {
							result = Math.min(result, divide(i,j,k,l));
						}
					}
				}
			}
		}
		
		bw.write(result + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int divide(int x, int y, int d1, int d2) {
		int[][] area = new int[N+1][N+1];
		for (int i=0;i<=d1;i++) {
			if (y-i < 0 || x+i > N) break;
			area[x+i][y-i] = 5;
		}
		
		for (int i=0;i<=d2;i++) {
			if (y+i > N || x+i > N) break;
			area[x+i][y+i] = 5;
		}
		
		for (int i=0;i<=d2;i++) {
			if (x+d1+i > N || y-d1+i > N || y-d1+i < 0) break;
			area[x+d1+i][y-d1+i] = 5;
		}
		
		for (int i=0;i<=d1;i++) {
			if (x+d2+i > N || y+d2-i > N || y+d2-i < 0) break;
			area[x+d2+i][y+d2-i] = 5;
		}
		
		for (int i = x+1;i<x+d1+d2;i++) {
			int count = 0;
			for (int j=1;j<=N;j++) {
				if (area[i][j] == 5) {
					count++;
				}
				if (count == 1) {
					area[i][j] = 5;
				} else if (count == 2) break;
			}
		}
		
		
		for (int i=1;i<x+d1;i++) {
			for (int j=1;j<=y;j++) {
				if (area[i][j] != 5) {
					area[i][j] = 1;
				}
			}
		}
		
		for (int i=1;i<=x+d2;i++) {
			for (int j=y+1;j<=N;j++) {
				if (area[i][j] != 5) {
					area[i][j] = 2;
				}
			}
		}
		
		for (int i=x+d1;i<=N;i++) {
			for (int j=1;j<y-d1+d2;j++) {
				if (area[i][j] != 5) {
					area[i][j] = 3;
				}
			}
		}
		
		for (int i=x+d2+1;i<=N;i++) {
			for (int j=y-d1+d2;j<=N;j++) {
				if (area[i][j] != 5) {
					area[i][j] = 4;
				}
			}
		}
		
		int[] populations = new int[5];
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				populations[area[i][j]-1] += board[i][j];
			}
		}
		
		Arrays.sort(populations);
		int value = populations[4] - populations[0]; 
		
		return value;
	}
}
