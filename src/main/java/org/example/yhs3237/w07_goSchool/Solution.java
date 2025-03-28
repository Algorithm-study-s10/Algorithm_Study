package org.example.yhs3237.w07_goSchool;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // m : 가로길이, n : 세로길이
        int[][] map = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            int y = puddle[1];
            int x = puddle[0];
            map[y][x] = -1; // 물에 잠긴 칸 표시
        }

        // 시작점
        map[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0; // 이후 연산을 위해 0으로 복구
                    continue;
                }
                if (i > 1) map[i][j] = (map[i][j] + map[i - 1][j]) %  1000000007;
                if (j > 1) map[i][j] = (map[i][j] + map[i][j - 1]) %  1000000007;
            }
        }

        return map[n][m];
    }
}