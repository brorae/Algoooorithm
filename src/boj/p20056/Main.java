package boj.p20056;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N,M,K;
	static List<Ball>[][] board;
	static List<Ball> balls;
	
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p20056/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new List[N+1][N+1];
		balls = new ArrayList<>();
		
		for (int i=1;i<N+1;i++) {
			for (int j=1;j<N+1;j++) {
				board[i][j] = new ArrayList<>();
			}
		}
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			balls.add(new Ball(x,y,m,s,d));
		}
		
		int answer = 0;
		for (int i=0;i<K;i++) {
			move();
			cal();
			answer = resetAndAnswer();
		}
		
		bw.write(answer + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void move() {
		for (Ball ball : balls) {
			int nx = ball.x + dx[ball.d] * ball.s;
			int ny = ball.y + dy[ball.d] * ball.s;
			nx %= N;
			ny %= N;
			if (nx == 0) {
				nx = N;
			} else if (nx < 0) {
				nx = nx + N;
			}
			if (ny == 0) {
				ny = N;
			} else if (ny < 0) {
				ny = ny + N;
			}
			ball.x = nx;
			ball.y = ny;
			
			board[nx][ny].add(ball.clone());
		}
	}
	
	public static void cal() {
		for (int i=1;i<N+1;i++) {
			for (int j=1;j<N+1;j++) {
				if (board[i][j].size() >= 2) {
					List<Ball> bs = board[i][j];
					int mSum = 0;
					int sSum = 0;
					int direction = bs.get(0).d % 2;
					boolean hasSameDirection = true;
					for (Ball ball : bs) {
						if (direction != ball.d % 2) hasSameDirection = false;
						mSum += ball.m;
						sSum += ball.s;
					}
					int nm = mSum/5;
					int ns = sSum/bs.size();
					bs.clear();
					if (nm != 0) {
						for (int k=0;k<=6;k+=2) {
							if (hasSameDirection) {
								bs.add(new Ball(i,j,nm,ns,k));
							} else {
								bs.add(new Ball(i,j,nm,ns,k+1));
							}
						}
					}
				}
			}
		}
	}
	
	public static int resetAndAnswer() {
		balls.clear();
	
		int amount = 0;
		for (int i=1;i<N+1;i++) {
			for (int j=1;j<N+1;j++) {
				if (board[i][j].size() != 0) {
					List<Ball> bs = board[i][j];
					for (Ball ball : bs) {
						balls.add(ball.clone());
						amount += ball.m;
					}
				}
				board[i][j].clear();
			}
		}
		return amount;
	}
}

class Ball {
		
	int x;
	int y;
	int m;
	int s;
	int d;
	
	public Ball(int x, int y, int m, int s, int d) {
		super();
		this.x = x;
		this.y = y;
		this.m = m;
		this.s = s;
		this.d = d;
	}
	
	public Ball clone() {
		return new Ball(x,y,m,s,d);
	}
	
	@Override
	public String toString() {
		return "Ball [x=" + x + ", y=" + y + ", m=" + m + ", s=" + s + ", d=" + d + "]";
	}
}