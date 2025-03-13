package org.example.yhs3237.w05_baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17281 {
    static int inning; // 이닝 수
    static int[][] players; // 각 선수가 각 이닝에서 얻는 결과
    static boolean[] visited; // 방문배열
    static int[] turn; // 선수들 타순을 담을 배열 (value : 선수 번호 0 ~ 8)
    static int max = Integer.MIN_VALUE; // 점수의 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inning = Integer.parseInt(br.readLine());

        players = new int[inning][9];
        for (int i = 0; i < inning; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turn = new int[9];
        turn[3] = 0; // 4번 타자는 1번 선수로 고정
        visited = new boolean[9];
        visited[0] = true;

        playing(0);
        System.out.println(max);
    }

    static void playing(int dept) {
        if (dept == 9) {
            int score = 0; // 게임 스코어
            int base1; // 1루
            int base2; // 2루
            int base3; // 3루
            int idx = 0; // 타자 순서

            for (int i = 0; i < inning; i++) {
                int out = 0; // 아웃
                base1 = base2 = base3 = 0; // 초기화

                while (out < 3) {
                    int playerNo = turn[idx]; // 타자 번호
                    int hit = players[i][playerNo]; // 해당 선수가 얻은 결과

                    // 아웃인 경우
                    if (hit == 0) {
                        out++;
                    }
                    // 안타인 경우 -> 각 주자 1루씩 진출
                    else if (hit == 1) {
                        score += base3; // 3루에 주자가 있었다면 점수 획득
                        // 각 1루씩 진출
                        base3 = base2;
                        base2 = base1;
                        base1 = 1;
                    }
                    // 2루타인 경우 -> 각 주자 2루씩 진출
                    else if (hit == 2) {
                        score += base3 + base2; // 2,3루에 주자만큼 점수 획득
                        base3 = base1; // 1루에 사람이 있었다면 3루로 진출
                        base1 = 0; // 1루는 사람 존재 X
                        base2 = 1; // 2루에 타자 존재
                    }
                    // 3루타인 경우 -> 각 주자 3루씩 진출
                    else if (hit == 3) {
                        score += base1 + base2 + base3; // 1,2,3루 주자만큼 점수
                        base1 = base2 = 0; // 1,2루에는 주자가 없어야한다.
                        base3 = 1; //3루에 타자 존재
                    }
                    // 홈런인 경우 -> 모든 주자 및 타자 진출
                    else if (hit == 4) {
                        score += base1 + base2 + base3 + 1;
                        base1 = base2 = base3 = 0;
                    }
                    // 다음 선수로 인덱스 변경
                    idx = (idx + 1) % 9;
                }
            }

            max = Math.max(max, score);
            return;
        }

        // 선수들의 타순 정하는 경우의 수
        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                turn[dept] = i;
                if (dept==2) {
                    playing(dept+2);
                } else {
                    playing(dept + 1);
                }
                visited[i] = false;
            }
        }
    }
}



