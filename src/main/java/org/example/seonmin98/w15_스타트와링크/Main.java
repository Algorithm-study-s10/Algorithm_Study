package org.example.seonmin98.w15_스타트와링크;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[] pick;
	static int minRet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		pick = new boolean[N];

		for (int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		minRet = Integer.MAX_VALUE;
		run(0, 0);
		System.out.println(minRet);
	}

	static void run(int idx, int lev) {
		if (lev == N / 2) {
			calculate();
			return;
		}
		for (int i = idx; i < N; i++) {
			if (!pick[i]) {
				pick[i] = true;
				run(i + 1, lev + 1);
				pick[i] = false;
			}
		}
	}

	static void calculate() {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (pick[i] && pick[j]) {
					sumA += arr[i][j];
				} else if (!pick[i] && !pick[j]) {
					sumB += arr[i][j];
				}
			}
		}
		minRet = Math.min(minRet, Math.abs(sumA - sumB));
	}
}
