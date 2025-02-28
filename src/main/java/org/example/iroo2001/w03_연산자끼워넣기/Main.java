package org.example.iroo2001.w03_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int max, min;
	static int[] numList;

	static void run(int ops, int calculated, int idx, int op) {
		int result = calculated;
		int nextops;

		switch (op) {
		case 3:
			result += numList[idx];
			break;
		case 2:
			result -= numList[idx];
			break;
		case 1:
			result *= numList[idx];
			break;
		case 0:
			result /= numList[idx];
			break;
		}

		// 마지막 수까지 계산 완료 -> max, min에 저장
		if (idx == N - 1) {
			max = Math.max(result, max);
			min = Math.min(result, min);
		}

		for (int o = 0; o < 4; o++) {
			if ((ops % Math.pow(100, o + 1)) / (Math.pow(100, o)) >=0.5) {
				nextops = ops - (int) Math.pow(100, o);
				run(nextops, result, idx + 1, o);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		numList = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자는 + - * / 로 4개 -> 연산자는 최대 10개 -> 각 연산자별 2자리씩 차지하도록 인코딩 (00 00 00 00)
		int[] opList = new int[4];
		int ops = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			opList[i] = Integer.parseInt(st.nextToken());
		}
		ops = (((opList[0] * 100 + opList[1]) * 100) + opList[2]) * 100 + opList[3];

		for (int o = 0; o < 4; o++) {
			if ((ops % Math.pow(100, o + 1)) / (Math.pow(100, o)) >=0.5) {
				ops -= Math.pow(100, o);
				run(ops, numList[0], 1, o);
				ops += Math.pow(100, o);
			}
		}

		System.out.println(max);
		System.out.println(min);

		br.close();
	}
}