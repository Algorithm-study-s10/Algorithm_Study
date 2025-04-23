package org.example.seonmin98.w11_주사위굴리기;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, cy, cx, K;
	static int[][] map;
	static int[] dice;
	static StringBuilder sb;
	
	static final int top = 0;
	static final int bottom = 1;
	static final int left = 2;
	static final int right = 3;
	static final int front = 4;
	static final int back = 5;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cy = Integer.parseInt(st.nextToken());
		cx = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new int[6];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) goEast();
			else if (cmd == 2) goWest();
			else if (cmd == 3) goNorth();
			else if (cmd == 4) goSouth();
		}
		
		System.out.println(sb);
	}

	static void goEast() {
		int ny = cy;
		int nx = cx + 1;
		if (outOfIndex(ny, nx)) return;
		cx++;
		
		int nextTop = dice[left];
		int nextBottom = dice[right];
		int nextLeft = dice[bottom];
		int nextRight = dice[top];
		
		dice[top] = nextTop;
		dice[bottom] = nextBottom;
		dice[left] = nextLeft;
		dice[right] = nextRight;
		
		map2dice();
	}

	static void goWest() {
		int ny = cy;
		int nx = cx - 1;
		if (outOfIndex(ny, nx)) return;
		cx--;
		
		int nextTop = dice[right];
		int nextBottom = dice[left];
		int nextLeft = dice[top];
		int nextRight = dice[bottom];
		
		dice[top] = nextTop;
		dice[bottom] = nextBottom;
		dice[left] = nextLeft;
		dice[right] = nextRight;
		
		map2dice();
	}
	
	static void goNorth() {
		int ny = cy - 1;
		int nx = cx;
		if (outOfIndex(ny, nx)) return;
		cy--;
		
		int nextTop = dice[front];
		int nextBottom = dice[back];
		int nextFront = dice[bottom];
		int nextBack = dice[top];
		
		dice[top] = nextTop;
		dice[bottom] = nextBottom;
		dice[front] = nextFront;
		dice[back] = nextBack;
		
		map2dice();
	}
	
	static void goSouth() {
		int ny = cy + 1;
		int nx = cx;
		if (outOfIndex(ny, nx)) return;
		cy++;
		
		int nextTop = dice[back];
		int nextBottom = dice[front];
		int nextFront = dice[top];
		int nextBack = dice[bottom];
		
		dice[top] = nextTop;
		dice[bottom] = nextBottom;
		dice[front] = nextFront;
		dice[back] = nextBack;
		
		map2dice();
	}

	static boolean outOfIndex(int ny, int nx) {
		if (ny < 0 || ny >= N || nx < 0 || nx >= M) return true;
		return false;
	}
	
	static void map2dice() {
		if (map[cy][cx] == 0) {
			map[cy][cx] = dice[bottom];
		} else {
			dice[bottom] = map[cy][cx];
			map[cy][cx] = 0;
		}
		
		sb.append(dice[top]).append("\n");
	}
}
