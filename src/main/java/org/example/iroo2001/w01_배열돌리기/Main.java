package org.example.iroo2001.w01_배열돌리기;

import java.util.ArrayDeque;
import java.util.Scanner;
// dR, dC 활용하여 반시계방향 순회
// 0을 만나거나 표 범위 넘어가면 방향 전환, dir<4까지만
// 반시계방향으로 돌린 값을 새 2차원 배열에 저장하고, 각 배열을 r칸씩 밀고 다시 표에 대입

public class Main {
	static int N, M, R;
	static int[] dR = new int[] { 1, 0, -1, 0 }; // 남동북서
	static int[] dC = new int[] { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayDeque<Integer>[] arr;
	static Scanner sc;

	@SuppressWarnings("unchecked")
	static void input() {
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][M];
		arr = new ArrayDeque[Math.min(N, M) / 2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}

	static void print() {
		for (int[] line : map) {
			for (int num : line) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	static void mapToArr() {
		int r, c, dir, nr, nc;
		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			r = i;
			c = i;
			dir = 0;
			arr[i] = new ArrayDeque<>();
			while (true) {
				arr[i].offer(map[r][c]);
				map[r][c] = 0;
				nr = r + dR[dir];
				nc = c + dC[dir];
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || map[nr][nc] == 0) {
					dir++;
					if (dir == 4) {
						break;
					}
					nr = r + dR[dir];
					nc = c + dC[dir];
					if (map[nr][nc] == 0) {
						break;
					}
				}
				r = nr;
				c = nc;
			}
		}
//		for (ArrayList<Integer> ar : arr) {
//			System.out.println(Arrays.toString(ar.toArray()));
//		}
	}

	static void arrToMap() {
		int r, c, dir, nr, nc;
		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			r = i;
			c = i;
			dir = 0;
			while (!arr[i].isEmpty()) {
				map[r][c] = arr[i].poll();
				nr = r + dR[dir];
				nc = c + dC[dir];
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || map[nr][nc] != 0) {
					dir++;
					if (dir == 4) {
						break;
					}
					nr = r + dR[dir];
					nc = c + dC[dir];
					if (map[nr][nc] != 0) {
						break;
					}
				}
				r = nr;
				c = nc;
			}
		}
	}

	static void simulate() {
		mapToArr();
		for (ArrayDeque<Integer> ar : arr) {
			for (int n = 0; n < R; n++) {
				ar.offerFirst(ar.pollLast());
			}
		}
		arrToMap();
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		input();
		simulate();
		print();
		sc.close();
	}
}