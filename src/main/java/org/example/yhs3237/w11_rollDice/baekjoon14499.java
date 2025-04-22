package org.example.yhs3237.w11_rollDice;

import java.io.*;
import java.util.*;

public class baekjoon14499 {
    static int N, M;
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        int x = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 세로 좌표
        int y = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 가로 좌표
        int K = Integer.parseInt(st.nextToken()); // 명령의 개수

        map = new int[N][M]; // 지도
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 델타 배열 (1:동, 2:서, 3:북, 4:남)
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        /*
         * 주사위 전개도 상 서로 마주보는 면 (두 면의 합이 7)
         * 6 <-> 1
         * 5 <-> 2
         * 4 <-> 3
         */

        // 1:윗면, 6:밑면, 2:북, 5:남, 4:서, 3:동
        dice = new int[7];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken()); // 이동 방향

            // 지도에서 이동할 다음 좌표
            int nx = x + dx[command];
            int ny = y + dy[command];

            if (OnMap(nx, ny)) {
                // 주사위 굴리기 (밑면 변화)
                rollDice(command);

                if (map[nx][ny] == 0) { // 이동한 곳의 값이 0이면 맵에 주사위 밑면 값 복사
                    map[nx][ny] = dice[6];
                } else { // 이동한 곳의 값이 0이 아니면 주사위 밑면에 해당 위치의 값 복사 & 해당 위치 값은 0이 됨
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }

                // 윗면의 값
                sb.append(dice[1]).append("\n");

                // 주사위의 현재 위치 변경
                x = nx;
                y = ny;
            }
        }

        System.out.println(sb);
    }

    // 주사위 굴리기
    static void rollDice(int command) {
        int[] temp = dice.clone();

        switch (command) {
            case 1: // 동쪽
                dice[1] = temp[4];
                dice[3] = temp[1];
                dice[6] = temp[3];
                dice[4] = temp[6];
                break;
            case 2: // 서쪽
                dice[1] = temp[3];
                dice[4] = temp[1];
                dice[6] = temp[4];
                dice[3] = temp[6];
                break;
            case 3: // 북쪽
                dice[1] = temp[5];
                dice[2] = temp[1];
                dice[6] = temp[2];
                dice[5] = temp[6];
                break;
            case 4: // 남쪽
                dice[1] = temp[2];
                dice[5] = temp[1];
                dice[6] = temp[5];
                dice[2] = temp[6];
                break;
        }
    }

//    static int roll(int bottom, int command) {
//		int newBottom = 0;
//
//		if (bottom == 6 || bottom == 1) {
//			if (command == 1) newBottom = 3;
//			else if (command == 2) newBottom = 4;
//			else if (command == 3) {
//				if (bottom == 6) newBottom = 5;
//				else newBottom = 2;
//			}
//			else {
//				if (bottom == 6) newBottom = 2;
//				else newBottom = 5;
//			}
//		}
//
//		else if (bottom == 5 || bottom == 2) {
//			if (command == 1) newBottom = 3;
//			else if (command == 2) newBottom = 4;
//			else if (command == 3) {
//				if (bottom == 5) newBottom = 1;
//				else newBottom = 6;
//			}
//			else {
//				if (bottom == 5) newBottom = 6;
//				else newBottom = 1;
//			}
//		}
//
//		else if (bottom == 4 || bottom == 3){
//			if (command == 1) {
//				if (bottom == 4) newBottom = 1;
//				else newBottom = 6;
//			}
//			else if (command == 2) {
//				if (bottom == 4) newBottom = 6;
//				else newBottom = 1;
//			}
//			else if (command == 3) newBottom = 2;
//			else newBottom = 5;
//		}
//
//		return newBottom;
//	}

    static boolean OnMap(int nx, int ny) {
        return (nx >= 0 && nx < N && ny >= 0 && ny < M);
    }
}