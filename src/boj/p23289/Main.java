package boj.p23289;

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
	
	static int R,C,K;
	static int[][] board;
	static boolean[][][][] walls;
	static List<Fan> fans;
	static List<Position> positions; // 감시할 위치 
	
	// 우, 좌, 상, 하 
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	
	static int[] opposite = {0,2,1,4,3};
	
	static int[][] sx = {{}, {-1,0,1}, {-1,0,1}, {-1,-1,-1}, {1,1,1}};
	static int[][] sy = {{}, {1,1,1}, {-1,-1,-1}, {-1,0,1}, {-1,0,1}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p23289/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[R+1][C+1];
		fans = new ArrayList<>();
		positions = new ArrayList<>();
		walls = new boolean[R+1][C+1][R+1][C+1];
		
		for (int i=1;i<=R;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=C;j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 5) {
					positions.add(new Position(i,j));
				} else if (num != 0) {
					fans.add(new Fan(i,j,num));
				}
			}
		}
		
		
		// 우(1), 좌(2), 상(3),  하(4)
		int W = Integer.parseInt(br.readLine());
		for (int i=0;i<W;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if (t == 0) {
				walls[x][y][x-1][y] = true;
				walls[x-1][y][x][y] = true;
			} else if (t == 1) {
				walls[x][y][x][y+1] = true;
				walls[x][y+1][x][y] = true;
			}
		}
		
		
		int count = 0;
		boolean work = true;
		while(count <= 100 && work) {
			move();
			manipulate();
			reduce();
			count++;
			work = check();
		}
		
		if (count == 101) {
			bw.write("101");
		} else {
			bw.write(count + "");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void move() {
		for (Fan fan : fans) {
			boolean[][] visited = new boolean[R+1][C+1];
			int[][] tmp = new int[R+1][C+1];
			int direction = fan.direction;
			int nx = fan.x + dx[direction];
			int ny = fan.y + dy[direction];
			
			if (nx <= 0 || ny <= 0 || nx > R || ny > C) continue;
			if (!walls[fan.x][fan.y][nx][ny]) {
				tmp[nx][ny] = 5;
				
				Queue<Position> q = new LinkedList<>();
				q.add(new Position(nx,ny));
				
				int wind = 4;
				while(!q.isEmpty() && wind > 0) {
					int len = q.size();
					for (int k=0;k<len;k++) {
						Position now = q.poll();
						for (int i=0;i<3;i++) {
							int nextX = now.x + sx[direction][i];
							int nextY = now.y + sy[direction][i];
							
							if (nextX <= 0 || nextY <= 0 || nextX > R || nextY > C) continue;
							if (visited[nextX][nextY]) continue;
							if (isBlocked(now.x, now.y, nextX, nextY, direction)) continue;
							
							visited[nextX][nextY] = true;
							tmp[nextX][nextY] = wind;
							q.add(new Position(nextX, nextY));
						}
					}
					wind--;
				}
			}
			for (int i=1;i<=R;i++) {
				for (int j=1;j<=C;j++) {
					board[i][j] += tmp[i][j];
				}
			}
		}
	}
	
	static boolean isBlocked(int x, int y, int nx, int ny, int dir) {
		if (y==ny || x==nx) return walls[x][y][nx][ny];
		else {
			if(dir==1 || dir==2){
                return walls[x][y][nx][y] || walls[nx][y][nx][ny];
            }
            // 온풍기 바람 방향이 상하
            else{
                return walls[x][y][x][ny] || walls[x][ny][nx][ny];
            }
		}
	}
	
	static void manipulate() {
		int[][] tmp = new int[R+1][C+1];
		for (int i=1;i<=R;i++) {
			for (int j=1;j<=C;j++) {
				int temp = board[i][j];
				for (int k=1;k<=4;k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx <= 0 || ny <= 0 || nx > R || ny > C) continue;
					if (temp > board[nx][ny] && !walls[i][j][nx][ny]) {
						int value = (int) ((temp - board[nx][ny])/4);
						tmp[nx][ny] += value;
						tmp[i][j] -= value;
					}
				}
			}
		}
		for (int i=1;i<=R;i++) {
			for (int j=1;j<=C;j++) {
				board[i][j] += tmp[i][j];
			}
		}
	}
	
	static void reduce() {
		for (int i=1;i<=C;i++) {
			if (board[1][i] != 0) {
				board[1][i]--;
			}
		}
		for (int i=2;i<=R;i++) {
			if (board[i][C] != 0) {
				board[i][C]--;
			}
		}
		for (int i=C-1;i>=1;i--) {
			if (board[R][i] != 0) {
				board[R][i]--;
			}
		}
		for (int i=R-1;i>1;i--) {
			if (board[i][1] != 0) {
				board[i][1]--;
			}
		}
	}
	
	static boolean check() {
		for (Position position : positions) {
			if (board[position.x][position.y] < K) {
				return true;
			}
		}
		return false;
	}
}

class Fan {
	int x;
	int y;
	int direction;
	
	public Fan (int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Fan [x=" + x + ", y=" + y + ", direction=" + direction + "]";
	}
	
	
}

class Position {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
}
