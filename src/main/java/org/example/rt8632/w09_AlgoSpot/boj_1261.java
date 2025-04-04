package org.example.rt8632.w09_AlgoSpot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1261 {
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		int ans = bfs(0, 0);
		System.out.println(ans);

	}

	static int bfs(int x, int y) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		pq.add(new int[] { x, y, 0 });
		visited[x][y] = true;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cnt = cur[2];
			
			if (cx == N - 1 && cy == M-1)
				return cnt;
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					if (map[nx][ny] == 0) {
						pq.add(new int[] { nx, ny, cnt });
					}
					else {
						pq.add(new int[] { nx, ny, cnt+1 });
					}
				}
			}
			
		}
		return -1;
	}
}
