package boj.p20055;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,K;
	static Block[] blocks;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/boj/p20055/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		blocks = new Block[2*N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<2*N;i++) {
			blocks[i] = new Block(Integer.parseInt(st.nextToken()), false);
		}
		
		int stage = 0;
		while (true) {
			stage++;
			rotate(); // 1
			move(); // 2
			put(); // 3 
			if (isStopped()) { // 4
				break;
			}
		}
		bw.write(stage + "");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void rotate() {
		Block prev = blocks[0];
		for (int i=0;i<2*N-1;i++) {
			Block tmp = blocks[i+1];
			blocks[i+1] = prev;
			prev = tmp;
		}
		blocks[0] = prev;
		
		if (blocks[N-1].hasRobot) {
			blocks[N-1].hasRobot = false;
		}
	}
	
	static void move() {
		for (int i=N-2;i>=0;i--) {
			if (blocks[i].hasRobot) {
				if (blocks[i+1].dur != 0 && !blocks[i+1].hasRobot) {
					blocks[i+1].dur -=1;
					blocks[i+1].hasRobot = true;
					blocks[i].hasRobot = false;
				}
			}
		}
		if (blocks[N-1].hasRobot) {
			blocks[N-1].hasRobot = false;
		}
	}
	
	static void put() {
		if (blocks[0].dur != 0) {
			blocks[0].hasRobot = true;
			blocks[0].dur -= 1;
		}
	}
	
	static boolean isStopped() {
		int count = 0;
		for (int i=0;i<blocks.length;i++) {
			if (blocks[i].dur == 0) {
				count++;
			}
		}
		return count >= K;
	}
}

class Block {
	int dur;
	boolean hasRobot;
	
	public Block (int dur, boolean hasRobot) {
		this.dur = dur;
		this.hasRobot = hasRobot;
	}
}
