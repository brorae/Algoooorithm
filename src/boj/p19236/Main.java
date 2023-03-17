package boj.p19236;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static Fish[][] fishes;
	static boolean[][] isShark;
	
	static int[][] positions;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static int result = Integer.MIN_VALUE;
	static int prevSharkX = -1, prevSharkY= -1;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/boj/p19236/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        fishes = new Fish[4][4];
        isShark = new boolean[4][4];
        positions = new int[17][2];
        
        for(int i=0;i<4;i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0;j<4;j++) {
        		int num = Integer.parseInt(st.nextToken());
        		positions[num][0] = i;
        		positions[num][1] = j;
                int direction = Integer.parseInt(st.nextToken()) - 1; 
                fishes[i][j] = new Fish(num, direction, false);
        	}
        }
        
        int sharkDirection = fishes[0][0].direction;
        dfs(0, 0, sharkDirection, 0);
        bw.write(result+"");
        bw.flush();
        bw.close();
        br.close();
    }
	
	
	
	static void dfs(int sharkX, int sharkY, int sharkDirection, int sum) {
		Fish[][] tmpFishes = Arrays.stream(fishes).map(it-> Fish.copy(it)).toArray(Fish[][]::new);
		int[][] tmpPositions = Arrays.stream(positions).map(int[]::clone).toArray(int[][]::new);
		
		Fish fish = fishes[sharkX][sharkY];
		sum += fish.num;
		if (!(prevSharkX == -1 && prevSharkY == -1)) {
			isShark[prevSharkX][prevSharkY] = false;
		}
		isShark[sharkX][sharkY] = true;
		fish.isDied = true;
		move();
		
		
		for (int i=1;i<=3;i++) {
			int nx = sharkX + i*dx[sharkDirection];
			int ny = sharkY + i*dy[sharkDirection];
			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
				result = Math.max(result, sum);
				break;
			} else {
				Fish nextFish = fishes[nx][ny];
				if (!nextFish.isDied) {
					int tmpX = prevSharkX;
					int tmpY = prevSharkY;
					prevSharkX = sharkX; 
					prevSharkY = sharkY;
					dfs(nx, ny, fishes[nx][ny].direction, sum);
					prevSharkX = tmpX;
					prevSharkY = tmpY;
				}
			}
		}
		
		sum -= fish.num;
		if (!(prevSharkX == -1 && prevSharkY == -1)) {
			isShark[prevSharkX][prevSharkY] = true;
		}
		isShark[sharkX][sharkY] = false;
		fish.isDied = false;
		fishes = tmpFishes;
		positions = tmpPositions;
	}
	
	static void move() {	
		for(int i=1;i<=16;i++) {
			int[] position = positions[i];
        	int x = position[0];
        	int y = position[1];
        	Fish nowFish = fishes[x][y];
        	if (nowFish.isDied || isShark[x][y]) continue;
        	int nowDirection = nowFish.direction;
        	int nx = x + dx[nowDirection];
        	int ny = y + dy[nowDirection];
        	int count = 0;
        	int nextDirection = nowDirection;
        	while(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || isShark[nx][ny]) {
        		nextDirection = ++nowDirection % 8;
        		nx = x + dx[nextDirection];
        		ny = y + dy[nextDirection];
        		count++;
        		if (count == 8) break;
        	}
        	nowFish.direction = nextDirection;
    		Fish nextFish = fishes[nx][ny];
    		fishes[x][y] = nextFish;
    		fishes[nx][ny] = nowFish;
    		swapPosition(nowFish.num, nextFish.num);
        }
	}
        
	
	static void swapPosition(int index1, int index2) {
		int[] position1 = positions[index1];
		int[] position2 = positions[index2];
		
		int tmp = position1[0];
		position1[0] = position2[0];
		position2[0] = tmp;
		
		tmp = position1[1];
		position1[1] = position2[1];
		position2[1] = tmp;
	}
}

class Fish {
	int num;
	int direction;
	boolean isDied;
	
	public Fish(int num, int direction, boolean isDied) {
		this.num = num;
		this.direction = direction;
		this.isDied = isDied;
	}
	
	public static Fish copy(Fish fish) {
		return new Fish(fish.num, fish.direction, fish.isDied);
	}
	
	public static Fish[] copy(Fish[] fishes) {
		Fish[] newFishes = new Fish[fishes.length];
		for (int i=0;i<fishes.length;i++) {
			newFishes[i] = copy(fishes[i]);
		}
		return newFishes;
	}
}