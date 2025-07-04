package org.example.yhs3237.w17_oneTwoThree;

import java.io.*;
import java.util.*;

public class baekjoon9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {

            int[] dp = new int[12]; // dp 결과를 저장할 배열
            int num = Integer.parseInt(br.readLine());

            dp[1] = 1;  // 1,2,3으로 1을 만드는 방법 수
            dp[2] = 2;  // 1,2,3으로 2를 만드는 방법 수
            dp[3] = 4;  // 1,2,3으로 3을 만드는 방법 수

            for (int i = 4; i <= num; i++) {
                // 점화식
                dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
            }

            System.out.println(dp[num]);
        }

    }
}
