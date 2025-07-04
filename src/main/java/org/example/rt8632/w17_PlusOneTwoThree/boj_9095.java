package org.example.rt8632.w17_PlusOneTwoThree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9095 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4;i<11;i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        for(int i=0;i<T;i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(dp[n]);
        }
    }
}
