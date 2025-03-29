package org.example.seonmin98.w07_등굣길;

class Solution {
    static boolean[][] puddle;
    static int[][] dp;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    
    public int solution(int m, int n, int[][] puddles) {
        puddle = new boolean[n + 1][m + 1];
        for (int y = 0; y < puddles.length; y++) {
            puddle[puddles[y][1]][puddles[y][0]] = true;
        }
        
        dp = new int[n + 1][m + 1];
        dp[1][1] = 1;
        
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (puddle[y][x]) {
                    dp[y][x] = 0;
                    continue;
                }
                if (x > 1) dp[y][x] += dp[y][x - 1];
                if (y > 1) dp[y][x] += dp[y - 1][x];
                dp[y][x] %= 1_000_000_007;
            }
        }

        return dp[n][m];
    }
}