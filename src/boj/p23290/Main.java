package boj.p23290;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int M,S;
	static List<Fish>[][] board;
	static List<Fish> fishes;
	static Fish shark;
	static int[][] isSmelled;
	
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	
	static int[] sx = {0,-1,0,1,0};
	static int[] sy = {0,0,-1,0,1};
	
	static int turn;
	static int result;
	
	static PriorityQueue<Path> pq;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p23290/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		board = new List[5][5];
		for (int i=1;i<5;i++) {
			for (int j=1;j<5;j++) {
				board[i][j] = new ArrayList<>();
			}
		}
		fishes = new ArrayList<>();
		isSmelled = new int[5][5];
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			fishes.add(new Fish(x,y,direction));
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		shark = new Fish(x, y, 0);
		
		while(turn++ < S) {
			simul();
		} 
		
		bw.write(result + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void simul() {
		List<Fish> tmp = cloneFishes();
		moveFish();
		moveShark();
		checkSmell();
		setBoard(tmp);
		reset();
	}
	
	static List<Fish> cloneFishes() {
		List<Fish> tmp = new ArrayList<>();
		for (Fish fish : fishes) {
			tmp.add(fish.clone());
		}
		return tmp;
	}
	
	static void moveFish() {
		for (Fish fish : fishes) {
			int nx = fish.x + dx[fish.direction];
			int ny = fish.y + dy[fish.direction];
			
			boolean findNext = true;
			int rotateCount = 0;
			while ((nx <= 0 || ny <= 0 || nx > 4 || ny > 4) || (nx == shark.x && ny == shark.y) || isSmelled[nx][ny] != 0) {
				fish.direction--;
				fish.direction %= 8;
				if (fish.direction == 0) fish.direction = 8;
				nx = fish.x + dx[fish.direction];
				ny = fish.y + dy[fish.direction];
				rotateCount++;
				if (rotateCount == 8) {
					findNext = false;
					break;
				}
			}
			
			if (findNext) {
				fish.x = nx;
				fish.y = ny;
			}
		}
		
		for (Fish fish : fishes) {
			board[fish.x][fish.y].add(fish.clone());
		}
	}
	
	static void moveShark() {
		pq = new PriorityQueue<>((o1,o2) -> {
			if (o1.killCount == o2.killCount) {
				return o1.getNumber() - o2.getNumber();
			}
			return o2.killCount - o1.killCount;
		});
		visited = new boolean[5][5];
		for (int i=1;i<=4;i++) {
			List<Integer> directions = new ArrayList<>();
			dfs(i, directions, 0);
		}
		Path path = pq.poll();	
		for (Integer direction : path.paths) {
			shark.x += sx[direction];
			shark.y += sy[direction];
			if (board[shark.x][shark.y].size() != 0) {
				board[shark.x][shark.y].clear();
				isSmelled[shark.x][shark.y] = turn;
			}
		}
	}
	
	static void dfs(int direction, List<Integer> directions, int count) {
		directions.add(direction);
		shark.x += sx[direction];
		shark.y += sy[direction];
		if (shark.x > 0 && shark.y > 0 && shark.x <= 4 && shark.y <= 4) {
			if (!visited[shark.x][shark.y]) {
				visited[shark.x][shark.y] = true;
				if (board[shark.x][shark.y].size() != 0) {
					count+=board[shark.x][shark.y].size();
				}
				if (directions.size() == 3) {
					List<Integer> paths = new ArrayList<>(directions);
					pq.add(new Path(paths, count));
				} else {
					for (int i=1;i<=4;i++) {
						dfs(i,directions, count);
					}
				}
				if (board[shark.x][shark.y].size() != 0) {
					count-=board[shark.x][shark.y].size();
				}
				visited[shark.x][shark.y] = false;
			} else {
				if (directions.size() == 3) {
					List<Integer> paths = new ArrayList<>(directions);
					pq.add(new Path(paths, count));
				} else {
					for (int i=1;i<=4;i++) {
						dfs(i,directions, count);
					}
				}
			}
		}
		directions.remove(directions.size() - 1);
		shark.x -= sx[direction];
		shark.y -= sy[direction];
	}
	
	static void checkSmell() {
		for (int i=1;i<5;i++) {
			for (int j=1;j<5;j++) {
				if (isSmelled[i][j] + 2 == turn) {
					isSmelled[i][j] = 0;
				}
			}
		}
	}
	
	static void setBoard(List<Fish> tmp) {
		for (Fish fish : tmp) {
			board[fish.x][fish.y].add(fish.clone());
		}
	}
	
	static void reset() {
		result = 0;
		fishes.clear();
		for (int i=1;i<5;i++) {
			for (int j=1;j<5;j++) {
				List<Fish> f = board[i][j];
				result += f.size();
				for (int k=0;k<f.size();k++) {
					fishes.add(f.get(k));
				}
				board[i][j].clear();
			}
		}
	}
}

class Fish {
	int x;
	int y;
	int direction;
	
	public Fish(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Fish clone() {
		return new Fish(this.x, this.y, this.direction);
	}

	@Override
	public String toString() {
		return "Fish [x=" + x + ", y=" + y + ", direction=" + direction + "]";
	}	
}

class Path {
	List<Integer> paths;
	int killCount;
	
	public Path(List<Integer> paths, int killCount) {
		this.paths = paths;
		this.killCount = killCount;
	}

	@Override
	public String toString() {
		return "Path [paths=" + paths + ", killCount=" + killCount + "]";
	}
	
	public int getNumber() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<3;i++) {
			sb.append(i);
		}
		return Integer.parseInt(sb.toString());
	}
}
