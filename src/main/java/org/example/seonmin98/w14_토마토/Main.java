package org.example.seonmin98.w14_토마토;

import java.util.*;
import java.io.*;

public class Main {
	static int M, N, H, notyet;
	static int[][][] arr;
	
	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, 0, 0, -1, 1};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //가로
		N = Integer.parseInt(st.nextToken()); //세로
		H = Integer.parseInt(st.nextToken()); //높이
		arr = new int[H][N][M];
		
		notyet = 0;
		Queue<int[]> queue = new LinkedList<>();
		for (int z = 0; z < H; z++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					arr[z][y][x] = Integer.parseInt(st.nextToken());
					if (arr[z][y][x] == 0) notyet++;
					else if (arr[z][y][x] == 1) {
						queue.add(new int[] {z, y, x});
					}
				}
			}
		} // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토가 들어있지 않은 칸
		
		while (!queue.isEmpty()) {
			
		}
		
		if (notyet != 0) System.out.println(-1);
	}
}
