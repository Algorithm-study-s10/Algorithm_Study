package org.example.seonmin98.w09_동전문제;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] money;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); //동전의 가지 수
			
			money = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				money[i] = Integer.parseInt(st.nextToken());
			} //동전의 각 금액 입력받기
			
			M = Integer.parseInt(br.readLine()); //동전으로 만들어야 할 금액
			
			int[] dp = new int[M + 1];
			dp[0] = 1; //0원을 만드는 방법은 1가지(아무 동전도 사용하지 않음)
			for (int i = 0; i < N; i++) {
				int coin = money[i]; //고려할 동전
				for (int m = coin; m <= M; m++) {
					dp[m] += dp[m - coin];
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.println(sb);
	}
}