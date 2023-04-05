package boj.p21610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[][] board;
	static int[] d;
	static int[] s;
	static List<Position> cloud;
	
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p21610/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		d = new int[M+1];
		s = new int[M+1];
		cloud = new ArrayList<>();
		
		for (int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<N+1;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=1;i<M+1;i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		cloud.add(new Position(N,1));
		cloud.add(new Position(N,2));
		cloud.add(new Position(N-1,1));
		cloud.add(new Position(N-1,2));
		
		int turn = 0;
		while(turn < M) {
			move(++turn);
		}
		int sum = 0;
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				sum += board[i][j];
			}
		}
		bw.write(sum + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void move(int turn) {
		int direction = d[turn];
		int distance = s[turn];
		
		for (Position position : cloud) {
			int nx = position.x + distance*dx[direction];
			int ny = position.y + distance*dy[direction];
			if (nx <= 0) {
				while(nx <= 0) {
					nx += N;
				}
			} else if (nx > N) {
				if (nx % N == 0) {
					nx = N;
				} else {
					nx = nx % N;
				}
			}
			if (ny <= 0) {
				while(ny <= 0) {
					ny += N;
				}
			} else if (ny > N) {
				if (ny % N == 0) {
					ny = N;
				} else {
					ny = ny % N;
				}	
			}
			position.x = nx;
			position.y = ny;
			
			board[nx][ny] += 1;
		}
		
		int[][] magicBoard = new int[N+1][N+1];
		
		for (Position position : cloud) {
			int count = 0;
			for (int i=2;i<=8;i+=2) {
				int nx = position.x + dx[i];
				int ny = position.y + dy[i];
				
				if (nx <= 0 || ny <= 0 || nx > N || ny >  N) continue;
				if (board[nx][ny] != 0) {
					count++;
				}
			}
			magicBoard[position.x][position.y] = count;
		}
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				board[i][j] += magicBoard[i][j];
			}
		}
		
		List<Position> newCloud = new ArrayList<>();
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (board[i][j] >= 2 && !cloud.contains(new Position(i,j))) {
					newCloud.add(new Position(i,j));
					board[i][j] -= 2;
				}
			}
		}
		cloud = newCloud;
	}
}

class Position {
	int x;
	int y;
	
	public Position (int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return x == other.x && y == other.y;
	}
}
