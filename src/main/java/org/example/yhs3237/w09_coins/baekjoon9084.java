package org.example.yhs3237.w09_coins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon9084 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            int N = Integer.parseInt(br.readLine()); // 동전의 가지 수

            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken()); // N가지 동전의 금액 저장
            }

            int money = Integer.parseInt(br.readLine()); // 동전들로 만들어야 할 금액
            int[] dp = new int[money+1];
            dp[0] = 1; // 0원을 만드는 경우의 수 : 1가지 (아무 동전도 선택 안 하는 경우)
            for (int coin : coins) {
                for (int i = coin; i <= money; i++) {
                    dp[i] += dp[i-coin]; // 이전 경우에 현재 동전을 더함
                }
            }
            System.out.println(dp[money]);
        }
    }
}
