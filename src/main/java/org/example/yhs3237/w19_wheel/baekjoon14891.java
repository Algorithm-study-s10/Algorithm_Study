package org.example.yhs3237.w19_wheel;

import java.io.*;
import java.util.*;

public class baekjoon14891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴의 배열 생성 및 입력 받기
        int[][] wheels = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine()); // 회전 횟수

        int sum = 0; // 회전 후 점수의 합계

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int wheelNum = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
            int direction = Integer.parseInt(st.nextToken()); // 회전 방향(1 또는 -1)

            int[] rotation = new int[4];
            rotation[wheelNum] = direction; // 바퀴의 회전 방향 저장 (인덱스 == 바퀴 번호, 0 : 회전 X, 1 : 시계방향, -1 : 반시계방향)

            // 오른쪽 바퀴 체크 (j : 오른쪽 바퀴 번호)
            // 연쇄적으로 회전하므로 반복문을 통해 톱니바퀴의 맞닿은 극이 같은지 확인해야 함
            for (int j = wheelNum + 1; j < 4; j++) {
                if (wheels[j - 1][2] == wheels[j][6]) { // 자성이 같은 경우
                    break; // 회전하지 않음
                } else { // 자성이 다른 경우
                    rotation[j] = -rotation[j - 1]; // 오른쪽 바퀴가 반대방향으로 회전
                }
            }

            // 왼쪽 바퀴 체크 (j : 왼쪽 바퀴 번호)
            for (int j = wheelNum - 1; j >= 0; j--) {
                if (wheels[j][2] == wheels[j + 1][6]) { // 자성이 같은 경우
                    break; // 회전하지 않음
                } else { // 자성이 다른 경우
                    rotation[j] = -rotation[j + 1]; // 왼쪽 바퀴가 반대방향으로 회전
                }
            }

            // 회전
            for (int j = 0; j < 4; j++) {
                if (rotation[j] == 0)
                    continue;
                else {
                    rotateWheel(wheels[j], rotation[j]);
                }
            }

        }

        // 회전 후 점수 합계 구하기
        for (int p = 0; p < 4; p++) {
            if (wheels[p][0] == 1) {
                sum += (1 << p);
            }
        }

        System.out.println(sum);
    }

    // 톱니바퀴 회전
    public static void rotateWheel(int[] wheel, int direction) {
        if (direction == 1) { // 시계방향 회전
            int lastValue = wheel[7];
            for (int i = 7; i > 0; i--) {
                wheel[i] = wheel[i - 1];
            }

            wheel[0] = lastValue;
        } else {
            int firstValue = wheel[0];
            for (int i = 0; i < 7; i++) {
                wheel[i] = wheel[i + 1];
            }

            wheel[7] = firstValue;
        }
    }
}

