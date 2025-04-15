package org.example.rt8632.w10_Escape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_3055 {
	static char[][] map;
	static int[][] water, gosumdochi;
	static boolean[][] visited;
	static int R, C;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		water = new int[R][C];
		gosumdochi = new int[R][C];

		int waterX = 0, waterY = 0;
		int gosumdochiX = 0, gosumdochiY = 0;
		int goalX = 0, goalY = 0;
		boolean isWater = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					gosumdochiX = i;
					gosumdochiY = j;
				}
				if (map[i][j] == '*') {
					isWater = true;
					waterX = i;
					waterY = j;
				}
				if (map[i][j] == 'D') {
					goalX = i;
					goalY = j;
				}
			}
		}

		waterBfs();
		visited = new boolean[R][C];
		gosumdochiBfs(gosumdochiX, gosumdochiY);

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(water[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(gosumdochi[i][j] + " ");
			}
			System.out.println();
		}
		boolean flag = false;
		int ans = 100;
		if (isWater) { // water 있을 때
			for (int i = 0; i < 4; i++) {
				int nx = goalX + dx[i];
				int ny = goalY + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if(map[nx][ny] == '*' || map[nx][ny] == 'X')
					continue;
				if(gosumdochi[goalX][goalY] == 0 || gosumdochi[nx][ny] >= gosumdochi[goalX][goalY])
					continue;
				if (water[nx][ny] > gosumdochi[nx][ny]) {
					flag = true;
					if (ans > gosumdochi[nx][ny] + 1)
						ans = gosumdochi[nx][ny] + 1;
				}
				else if(water[nx][ny] == 0 && gosumdochi[nx][ny] != 0) {
					flag = true;
					if (ans > gosumdochi[nx][ny] + 1)
						ans = gosumdochi[nx][ny] + 1;
				}
				else if(gosumdochi[nx][ny] == 0 && map[nx][ny] == 'S') {
					flag = true;
					ans = 1;
				}
			}
		} else { //water 없을 때
			for (int i = 0; i < 4; i++) {
				int nx = goalX + dx[i];
				int ny = goalY + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if(gosumdochi[nx][ny] == 0 && map[nx][ny] != 'S')
					continue;
				if(gosumdochi[goalX][goalY] == 0 || gosumdochi[nx][ny] >= gosumdochi[goalX][goalY])
					continue;
				if(gosumdochi[nx][ny] == 0 && map[nx][ny] == 'S') {
					flag = true;
					if(ans >= gosumdochi[nx][ny] + 1)
						ans = gosumdochi[nx][ny] + 1;
				}
				else if(gosumdochi[nx][ny] != 0) {
					flag = true;
					if(ans >= gosumdochi[nx][ny] + 1)
						ans = gosumdochi[nx][ny] + 1;
				}
			}
		}

		if (flag)
			System.out.println(ans);
		else
			System.out.println("KAKTUS");
	}

	static void waterBfs() {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
	        for (int j = 0; j < C; j++) {
	            if (map[i][j] == '*') {
	                q.add(new int[]{i, j});
	                visited[i][j] = true;
	            }
	        }
	    }
		while (!q.isEmpty()) {
			int[] c = q.poll();
			int cx = c[0];
			int cy = c[1];
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if (visited[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == 'D')
					continue;
				visited[nx][ny] = true;
				water[nx][ny] = water[cx][cy] + 1;
				q.add(new int[] { nx, ny });
			}
		}
	}

	static void gosumdochiBfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] c = q.poll();
			int cx = c[0];
			int cy = c[1];
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if (visited[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == '*')
					continue;
				gosumdochi[nx][ny] = gosumdochi[cx][cy] + 1;
				visited[nx][ny] = true;
				q.add(new int[] { nx, ny });
			}
		}
	}
}
