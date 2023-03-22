package boj.p15685;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] board = new int[101][101];
	static int[] dy = {0,-1,0,1};
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/boj/p15685/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			calculate(x,y,d,g);
		}
		
		int sum = 0;
		for (int i=0;i<101;i++) {
			for (int j=0;j<101;j++) {
				if (board[i][j] == 1) {
					sum += calculateCount(i, j);
				}
			}
		}
		bw.write(sum + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int calculateCount(int x, int y) {
		int count = 0;
		int nx = x + 1;
		int ny = y + 1;
		if (nx < 0 || ny < 0 || nx > 100 || ny > 100) return 0;
		if (board[nx][y] == 1 && board[x][ny] == 1 && board[nx][ny] == 1) {
			count++;
		}
		return count;
	}
	
	static void calculate(int x, int y, int d, int g) {
		Set<Point> points = new HashSet<>();
		points.add(new Point(x,y));
		board[y][x] = 1;
		int nx = x + dx[d];
		int ny = y + dy[d];
		Point standard = new Point(nx,ny);
		points.add(standard);
		board[ny][nx] = 1;
		
		int plusX = nx * -1;
		int plusY = ny * -1;
		while(g > 0) {
			Set<Point> adding = new HashSet<>();
			for (Point point : points) {
				if (standard.equals(point)) continue;
				int nextX = point.x + plusX;
				int nextY = point.y + plusY;
				Point standardPoint = new Point(-1 * nextY + -1 * plusX , nextX + -1 * plusY);
				adding.add(standardPoint);
				board[standardPoint.y][standardPoint.x] = 1;
				if (point.x == x && point.y == y) {
					standard = standardPoint;
				}
			}
			points.addAll(adding);
			plusX = standard.x * -1;
			plusY = standard.y * -1;
			g--;
		}
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}
}
