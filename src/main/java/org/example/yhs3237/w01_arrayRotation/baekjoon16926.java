package org.example.yhs3237.w01_arrayRotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon16926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 행
        int M = Integer.parseInt(st.nextToken()); // 열
        int R = Integer.parseInt(st.nextToken()); // 회전 횟수
        int count = Math.min(N, M) / 2;  // min(N, M) mod 2 = 0 이라고 했으므로 회전할 수 있는 고리의 개수는 count개

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 회전 (고리별)
        for (int i = 0; i < R; i++) { // 전체 회전 횟수만큼 반복
            for (int j = 0; j < count; j++) { // 고리별 회전
                int save = arr[j][j]; // 고리별로 따로 빼놓을 값

                // 오른쪽에서 왼쪽으로(←)
                for (int k = j + 1; k < M - j; k++) {
                    arr[j][k - 1] = arr[j][k];
                }

                // 아래에서 위로 (↑)
                for (int k = j + 1; k < N - j; k++) {
                    arr[k - 1][M - 1 - j] = arr[k][M - 1 - j];
                }

                // 왼쪽에서 오른쪽으로 (→)
                for (int k = M - 2 - j; k >= j; k--) {
                    arr[N - 1 - j][k + 1] = arr[N - 1 - j][k];
                }

                // 위에서 아래로 (↓)
                for (int k = N - 2 - j; k >= j; k--) {
                    arr[k + 1][j] = arr[k][j];
                }


                arr[j + 1][j] = save;
            }
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }
}

