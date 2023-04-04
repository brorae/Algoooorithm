package boj.p17837;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,K;
	static int[][] board;
	static List<Integer>[][] piecesBoard;
	static Piece[] pieces;
	static int[] dx = {0,0,0,-1,1}; // 1~4까지 사용 
	static int[] dy = {0,1,-1,0,0};
	static int[] opposite = {0,2,1,4,3};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p17837/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		piecesBoard = new List[N+1][N+1];
		pieces = new Piece[K+1];
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				piecesBoard[i][j] = new ArrayList<>();
			}
		}
		
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {
				board[i][j] =  Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			pieces[i] = new Piece(i,x,y,direction);
			piecesBoard[x][y].add(i);
		}
		
		
		int result = move();
		bw.write(result+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int move() {
		int turn = 0;
		while(true) {
			turn++;
			
			Queue<Piece> q = new LinkedList<>();
			for (int i=1;i<=K;i++) {
				q.add(pieces[i]);
			}
			
			while(!q.isEmpty()) {
				Piece now = q.poll();
				int nowX = now.x;
				int nowY = now.y;
				int nx = now.x + dx[now.direction];
				int ny = now.y + dy[now.direction];
				
				if (nx <= 0 || ny <= 0 || nx > N || ny > N) {
					nx = now.x + dx[opposite[now.direction]];
					ny = now.y + dy[opposite[now.direction]];
					now.direction = opposite[now.direction];
					if (board[nx][ny] == 2) continue;
				}
				if (board[nx][ny] == 2) {
					nx = now.x + dx[opposite[now.direction]];
					ny = now.y + dy[opposite[now.direction]];
					now.direction = opposite[now.direction];
					if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
				}
				
				if (board[nx][ny] == 0) {
					List<Integer> list = piecesBoard[now.x][now.y];
					for (int i=0;i<list.size();i++) {
						if (list.get(i) == now.num) {
							List<Integer> subList = list.subList(i, list.size());
							for (int j=0;j<subList.size();j++ ) {
								piecesBoard[nx][ny].add(subList.get(j));
								Piece piece = pieces[subList.get(j)];
								piece.x = nx;
								piece.y = ny;
							}
							if (i == 0) {
								piecesBoard[nowX][nowY] = new ArrayList<>();
							} else {
								piecesBoard[nowX][nowY] = list.subList(0, i);
							}
							break;
						}
					} 
				} else if (board[nx][ny] == 1) {
					List<Integer> list = piecesBoard[now.x][now.y];
					for (int i=0;i<list.size();i++) {
						if (list.get(i) == now.num) {
							List<Integer> subList = list.subList(i, list.size());
							for (int j=subList.size()-1;j>=0;j--) {
								piecesBoard[nx][ny].add(subList.get(j));
								Piece piece = pieces[subList.get(j)];
								piece.x = nx;
								piece.y = ny;
							}
							if (i == 0) {
								piecesBoard[nowX][nowY] = new ArrayList<>();
							} else {
								piecesBoard[nowX][nowY] = list.subList(0, i);
							}
							break;
						}
					} 
				}
				if (piecesBoard[nx][ny].size() >= 4) {
					return turn;
				}
			}
			
			if (turn == 1000) {
				return -1;
			}
		}
	}
}

class Piece {
	int num;
	int x;
	int y;
	int direction;
	
	public Piece (int num, int x, int y, int direction) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}	
}
