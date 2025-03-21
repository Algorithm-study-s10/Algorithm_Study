package org.example.yhs3237.w06_kevinB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1389 {
    static int N,M;
    static int[][] relationship;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 유저의 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        relationship = new int[N][N]; // 관계들을 담은 2차원 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    relationship[i][j] = 0; // 자기 자신 (0단계이므로)
                } else {
                    relationship[i][j] = 99999; // 직접적인 관계가 없는 노드 (아주 큰 수로 초기화)
                }
            }
        }

        // 직접적인 관계가 있는 노드들은 1단계이므로 1로 갱신
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            relationship[p1-1][p2-1] = 1;
            relationship[p2-1][p1-1] = 1;
        }

        // 플로이드-워셜 알고리즘 실행
        floydWarshall();

        int answer = 0; // 케빈 베이컨의 수가 가장 작은 사람
        for (int i = 0; i < N; i++) {
            int sum = 0; // 유저의 케빈 베이컨 수 (=단계들의 합)
            for (int j = 0; j< N; j++) {
                sum += relationship[i][j];
            }

            // 최소값 찾기
            if (sum < min) {
                min = sum;
                answer = i;
            }
        }

        System.out.println(answer+1);
        br.close();

    }

    public static void floydWarshall() {
        for (int k = 0; k < N; k++) { // 거쳐갈 노드
            for (int i = 0; i < N; i++) { // 출발 노드
                for (int j = 0; j < N; j++) { // 도착 노드
                    // relationship[i][j] : 몇단계만에 알 수 있는지 확인할 사람(j 유저)
                    // relationship[i][k] : i유저(출발 유저)와 k유저가 직접적으로 관계가 있는지
                    // relationship[k][i] : k유저(거쳐갈 유저)와 j유저가 직접적으로 관계가 있는지
                    if (relationship[i][j] > relationship[i][k] + relationship[k][j]) {
                        relationship[i][j] = relationship[i][k] + relationship[k][j];
                    }
                }
            }
        }
    }
}
