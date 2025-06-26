package org.example.yhs3237.w16_minecraft;

import java.util.*;
import java.io.*;

public class baekjoon18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int B = Integer.parseInt(st.nextToken()); // 인벤토리에 들어있는 블록의 수

        int[][] ground = new int[N][M];

        // 집터 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int resultTime = Integer.MAX_VALUE; // 필요한 최소 시간
        int resultHeight = 0; // 최대 높이

        // h(집터 높이)의 범위 : 0 ~ 256 (모든 경우의 수 탐색)
        for (int h = 0; h <= 256; h++) {
            int blocks = B;
            int time = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = ground[i][j] - h; // 이번 경우의 높이와 땅의 높이 차이
                    if (diff > 0) {
                        // 블록 제거
                        time += diff * 2;
                        blocks += diff;
                    } else if (diff < 0) {
                        // 블록 쌓기
                        time += (-diff);
                        blocks -= (-diff);
                    }
                }
            }

            // 블록 수가 부족한 경우 -> 이 높이는 만들 수 없음을 의미
            if (blocks < 0) continue;

            // 시간의 최소값, 높이의 최대값 갱신
            if (resultTime > time) {
                resultTime = time;
                resultHeight = h;
            } else if (resultTime == time) {
                if (resultHeight < h) {
                    resultTime = time;
                    resultHeight = h;
                }
            }
        }

        System.out.println(resultTime + " " + resultHeight);
    }
}
