package org.example.seonmin98.w06_케빈베이컨의6단계;

import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] arr;
	static int minSum, minNum;
	static final int INF = 1000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1][n + 1];
		
		for (int y = 1; y <= n; y++) {
			Arrays.fill(arr[y], INF);
			arr[y][y] = 0;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		
		for (int i = 1; i <= n; i++) {
			for (int y = 1; y <= n; y++) {
				for (int x = 1; x <= n; x++) {
					if (arr[y][i] != INF && arr[i][x] != INF) {
						arr[y][x] = Math.min(arr[y][x], arr[y][i] + arr[i][x]);						
					}
				}
			}
		}
		
		minSum = Integer.MAX_VALUE;
		minNum = 0;
		for (int y = 1; y <= n; y++) {
			int sum = 0;
			for (int x = 1; x <= n; x++) {
				sum += arr[y][x];
			}
			if (minSum > sum) {
				minSum = sum;
				minNum = y;
			};
		}
		
		System.out.println(minNum);
	}
}
