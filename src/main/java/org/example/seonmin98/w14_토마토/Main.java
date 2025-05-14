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
			
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					arr[z][y][x] = Integer.parseInt(st.nextToken());
					if (arr[z][y][x] == 0) notyet++;
					else if (arr[z][y][x] == 1) {
						queue.add(new int[] {z, y, x});
					}
				}
			}
		} // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토가 들어있지 않은 칸
		
		
		if (notyet == 0) {
			System.out.println(0);
			return;
		} //저장될 때부터 모든 토마토가 익어있는 상
		
		int date = 0;
		while (!queue.isEmpty()) {
			date++;
			List<int[]> list = new ArrayList<>();
			while (!queue.isEmpty()) {
				list.add(queue.poll());
			}
			
			for (int[] cur : list) {
				for (int i = 0; i < 6; i++) {
					int nz = cur[0] + dz[i];
					int ny = cur[1] + dy[i];
					int nx = cur[2] + dx[i];
					
					if (!isInArr(nz, ny, nx) || arr[nz][ny][nx] != 0) continue;
					arr[nz][ny][nx] = 1;
					notyet--;
					queue.add(new int[] {nz, ny, nx});
				}
			}
		}
		
		if (notyet != 0) System.out.println(-1);
		else System.out.println(date - 1);
	}

	private static boolean isInArr(int nz, int ny, int nx) {
		if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M) return false;
		return true;
	}
}
