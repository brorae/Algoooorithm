package boj.p17144;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C,T;
	static int[][] board;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static List<Position> machines;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p17144/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		machines = new ArrayList<>();
		
		for (int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<C;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) {
					machines.add(new Position(i,j));
				}
			}
		}
		
		for (int i=0;i<T;i++) {
			spread();
			work();
		}
		
		int sum = 0;
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++) {
				if (board[i][j] != 0 && board[i][j] != -1) {
					sum += board[i][j];
				}
			}
		}
		
		bw.write(sum + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void spread() {
		int[][] calBoard = new int[R][C];
		Queue<Position> q = new LinkedList<>();
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++) {
				if (board[i][j] != 0 && board[i][j] != -1) {
					q.add(new Position(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Position now = q.poll();
			int amount = board[now.x][now.y];
			int willSpreaded = (int) (amount / 5.0);
			int spreadCount = 0;
			for (int i=0;i<4;i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				if (board[nx][ny] == -1) continue;
				spreadCount++;
				calBoard[nx][ny] += willSpreaded;
			}
			calBoard[now.x][now.y] -= willSpreaded * spreadCount;
		}
		
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++ ) {
				board[i][j] += calBoard[i][j];
			}
		}
	}
	
	static void work() {
		Position first = machines.get(0);
		Position second = machines.get(1);
		
		int prev = board[first.x][1];
		for (int i=2;i<C;i++) {
			int tmp = board[first.x][i];
			board[first.x][i] = prev;
			prev = tmp;
		}
		board[first.x][1] = 0;
		
		for (int i=first.x - 1;i>=0;i--) {
			int tmp = board[i][C-1];
			board[i][C-1] = prev;
			prev = tmp;
		}
		
		for (int i=C-2;i>=0;i--) {
			int tmp = board[0][i];
			board[0][i] = prev;
			prev = tmp;
		}
		
		for (int i=1;i<first.x;i++) {
			int tmp = board[i][0];
			board[i][0] = prev;
			prev = tmp;
		}
		
		prev = board[second.x][1];
		for (int i=2;i<C;i++) {
			int tmp = board[second.x][i];
			board[second.x][i] = prev;
			prev = tmp;
		}
		board[second.x][1] = 0;
		
		for (int i=second.x + 1;i<R;i++) {
			int tmp = board[i][C-1];
			board[i][C-1] = prev;
			prev = tmp;
		}
		
		for (int i=C-2;i>=0;i--) {
			int tmp = board[R-1][i];
			board[R-1][i] = prev;
			prev = tmp;
		}
		
		for (int i=R-2;i>second.x;i--) {
			int tmp = board[i][0];
			board[i][0] = prev;
			prev = tmp;
		}
	}
}

class Position {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
