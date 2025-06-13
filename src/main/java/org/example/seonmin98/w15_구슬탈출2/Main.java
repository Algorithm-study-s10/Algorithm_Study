package org.example.seonmin98.w15_구슬탈출2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] arr;
	static boolean[][][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	static class State {
		int rx, ry, bx, by, depth;
		State(int rx, int ry, int bx, int by, int depth) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		int rx = 0, ry = 0, bx = 0, by = 0;

		for (int y = 0; y < N; y++) {
			String input = br.readLine();
			for (int x = 0; x < M; x++) {
				arr[y][x] = input.charAt(x);
				if (arr[y][x] == 'R') {
					rx = x;
					ry = y;
				} else if (arr[y][x] == 'B') {
					bx = x;
					by = y;
				}
			}
		}

		visited = new boolean[N][M][N][M];
		System.out.println(bfs(rx, ry, bx, by));
	}

	static int bfs(int rx, int ry, int bx, int by) {
		Queue<State> q = new LinkedList<>();
		q.add(new State(rx, ry, bx, by, 1));
		visited[ry][rx][by][bx] = true;

		while (!q.isEmpty()) {
			State cur = q.poll();

			if (cur.depth > 10) return -1;

			for (int d = 0; d < 4; d++) {
				int[] r = move(cur.rx, cur.ry, dx[d], dy[d]);
				int[] b = move(cur.bx, cur.by, dx[d], dy[d]);

				int nrx = r[0], nry = r[1], rc = r[2];
				int nbx = b[0], nby = b[1], bc = b[2];

				// 파란 공이 빠지면 실패
				if (arr[nby][nbx] == 'O') continue;
				// 빨간 공만 빠지면 성공
				if (arr[nry][nrx] == 'O') return cur.depth;

				// 두 공이 같은 위치에 있을 경우 이동 수가 더 큰 쪽을 한 칸 뒤로
				if (nrx == nbx && nry == nby) {
					if (rc > bc) {
						nrx -= dx[d];
						nry -= dy[d];
					} else {
						nbx -= dx[d];
						nby -= dy[d];
					}
				}

				if (!visited[nry][nrx][nby][nbx]) {
					visited[nry][nrx][nby][nbx] = true;
					q.add(new State(nrx, nry, nbx, nby, cur.depth + 1));
				}
			}
		}
		return -1;
	}

	// 공을 벽(#)이나 구멍(O)에 닿을 때까지 이동시키는 함수
	static int[] move(int x, int y, int dx, int dy) {
		int count = 0;
		while (true) {
			if (arr[y + dy][x + dx] == '#' || arr[y][x] == 'O') break;
			x += dx;
			y += dy;
			count++;
		}
		return new int[]{x, y, count}; // 최종 위치 + 이동 거리
	}
}