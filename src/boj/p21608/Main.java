package boj.p21608;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
		
	static int N;
	static int[][] board;
	static Map<Integer, boolean[]> map;
	static Queue<Integer> studentNums;
	static PriorityQueue<Block> pq;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[] d = {0,1,10,100,1000};

	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p21608/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		map = new HashMap<>();
		studentNums = new LinkedList<>();
		
		for (int i=1;i<=N*N;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			boolean[] like = new boolean[N*N+1];
			for (int j=0;j<4;j++) {
				like[Integer.parseInt(st.nextToken())] = true;
				map.put(num, like);
			}
			studentNums.add(num);
		}
		
		
		while(!studentNums.isEmpty()) {
			initPQ();
			int now = studentNums.poll();
			for (int i=1;i<=N;i++) {
				for (int j=1;j<=N;j++) {
					if (board[i][j] == 0) {
						int[] adj = calAdj(now, i,j); // 0이 인접 좋아요, 1이 인접빈
						pq.add(new Block(i,j,adj[0], adj[1]));
					}
				}
			}
			
			Block first = pq.poll();
			board[first.x][first.y] = now;
		}
		
		int result = 0;
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				int count = 0;
				int num = board[i][j];
				for (int k=0;k<4;k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
					if (map.get(num)[board[nx][ny]]) {
						count ++;
					}
				}
				result += d[count];
			}
		}
		
		bw.write(result+"");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int[] calAdj(int num, int x, int y) { 
		int adjEmpty = 0;
		int adjLike = 0;
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
			if (board[nx][ny] == 0) {
				adjEmpty++;
			}
			if (map.get(num)[board[nx][ny]]) {
				adjLike++;
			}
		}
		return new int[] {adjLike, adjEmpty};
	}
	
	static void initPQ() {
		pq = new PriorityQueue<>((o1,o2) -> {
			if (o1.adjLikes == o2.adjLikes) {
				if (o1.adjEmpty == o2.adjEmpty) {
					if (o1.x == o2.x) {
						return o1.y - o2.y;
					}
					return o1.x - o2.x;
				}
				return o2.adjEmpty - o1.adjEmpty;
			}
			return o2.adjLikes - o1.adjLikes;
		});
	}
}

class Block {
	int x;
	int y;
	int adjLikes;
	int adjEmpty;
	
	public Block(int x, int y,int adjLikes, int adjEmpty) {
		this.x = x;
		this.y = y;
		this.adjLikes = adjLikes;
		this.adjEmpty = adjEmpty;
	}

	@Override
	public String toString() {
		return "Block [x=" + x + ", y=" + y + ", adjLikes=" + adjLikes + ", adjEmpty=" + adjEmpty + "]";
	}
}
