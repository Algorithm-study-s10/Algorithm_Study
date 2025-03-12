package org.example.iroo2001.w05_설탕배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().strip());
		br.close();

		// dp[i] : i를 만들기 위해 필요한 봉지 개수
		int[] dp = new int[N + 1];

		dp[3] = 1;
		if (N >= 5) {
			// 3<=N<=5000이므로, dp[5]는 에러 발생 가능
			dp[5] = 1;
		}

		// 만들 수 없는 kg라면, 100000 이상의 값을 갖도록 만들어버리기 (Math.min을 사용하므로)
		// 이를 위해 dp[0,1,2,4]는 100000으로 초기화해야함 (dp[3]=dp[5]=1)
		dp[0] = dp[1] = dp[2] = 100000;
		if (N >= 4) {
			dp[4] = dp[0];
		}

		for (int i = 6; i <= N; i++) {
			// (i-3 혹은 i-5를 만드는 개수 + 1 = i를 만들기 위해 필요한 개수)
			// 단, i-3 혹은 i-5가 불가능한 경우는 제외하고 계산
			dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
		}

		System.out.println((dp[N] < 100000 ? dp[N] : -1)); // dp[N]이 100000보다 작으면 만들 수 있음
	}
}