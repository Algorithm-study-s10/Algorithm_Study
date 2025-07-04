package org.example.seonmin98.w17_123더하기;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	
    	int[] dp = new int[11];
    	dp[0] = 1;
    	for (int i = 1; i < 11; i++) {
    	    for (int j = 1; j <= 3; j++) {
    	        if (i - j >= 0) {
    	            dp[i] += dp[i - j];
    	        }
    	    }
    	}
    	
    	for (int tc = 1; tc <= T; tc++) {
    		int N = Integer.parseInt(br.readLine());
    		System.out.println(dp[N]);
    	}
    }
}
