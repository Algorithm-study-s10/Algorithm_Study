package org.example.rt8632.w09_Coin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9084 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] coin = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int[] dp = new int[M+1];
			//동전
			for(int i=1;i<=N;i++) {
				//dp 테이블
				for(int j=1;j<=M;j++) {
					if(j - coin[i] > 0) {
						dp[j] += dp[j - coin[i]];
					} else if(j - coin[i] == 0) {
						dp[j] += 1;
					}
				}
			}
			System.out.println(dp[M]);
		}
	}
	
}
