package org.example.yhs3237.w18_chicken;

import java.io.*;
import java.util.*;

public class baekjoon15686 {
    static int N, M, answer;
    static int[][] city;
    static List<int[]> chickens;
    static boolean[] isPicked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 크기
        M = Integer.parseInt(st.nextToken()); // 고를 치킨집의 수

        // 도시 정보 입력 받기 (0 : 빈칸, 1: 집, 2: 치킨집)
        city = new int[N][N];
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());

                // 치킨집 위치 표시
                if (city[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        isPicked = new boolean[chickens.size()]; // 선택 여부 표시
        answer = Integer.MAX_VALUE; // 최소 치킨 거리

        // 백트래킹 (여러 개의 chicken 집들 중 M개를 고르는 경우)
        backTracking(0, 0);

        System.out.println(answer);
    }

    static void backTracking(int index, int depth) {
        // 정지 조건 (M개를 모두 고른 경우)
        if (depth == M) {
            int dist = chickenDistance(); // 도시의 치킨 거리 계산
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            isPicked[i] = true;
            backTracking(i + 1, depth + 1);
            isPicked[i] = false;
        }
    }

    // 치킨 거리 구하는 함수
    static int chickenDistance() {
        int sum = 0; // 도시의 치킨 거리


        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (city[x][y] == 1) {
                    int minDist = Integer.MAX_VALUE;

                    for (int i = 0; i < chickens.size(); i++) {
                        if (isPicked[i]) {
                            // 치킨집의 위치 (x, y 좌표)
                            int chickenX = chickens.get(i)[0];
                            int chickenY = chickens.get(i)[1];

                            // 치킨집과의 거리 : |r1-r2| + |c1-c2|
                            int dist = (Math.abs(chickenX - x) + Math.abs(chickenY - y));

                            // 최소 거리를 치킨 거리로 설정
                            minDist = Math.min(minDist, dist);
                        }
                    }
                    // 최소거리를 도시의 치킨 거리에 포함
                    sum += minDist;
                }
            }
        }

        return sum;
    }
}
