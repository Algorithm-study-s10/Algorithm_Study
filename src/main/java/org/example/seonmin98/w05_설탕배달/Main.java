package org.example.seonmin98.w05_설탕배달;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		if (n <= 5) {
			if (n == 3) System.out.println(1);
			else if (n == 4) System.out.println(-1);
			else if (n == 5) System.out.println(1);
			return ;
		}
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		dp[3] = 1;
		dp[5] = 1;
		for (int i = 6; i <= n; i++) {
			if (dp[i - 3] == -1 && dp[i - 5] == -1) dp[i] = -1;
			else {		
				if (dp[i - 3] > 0 && dp[i - 5] > 0) dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
				else if (dp[i - 3] > 0) dp[i] = dp[i - 3] + 1;
				else if (dp[i - 5] > 0) dp[i] = dp[i - 5] + 1;
			}
		}
		System.out.println(dp[n]);
		
	}
}
