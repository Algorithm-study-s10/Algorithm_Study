package org.example.yhs3237.w18_cuttingLAN;

import java.util.*;
import java.io.*;

public class baekjoon1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선 수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 수

        int[] cables = new int[K];
        long max = 0;

        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            if (cables[i] > max) {
                max = cables[i]; // 가장 긴 랜선의 길이
            }
        }

        // 이분 탐색
        long left = 1;
        long right = max;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (int i = 0; i < K; i++) {
                count += cables[i] / mid;
            }

            if (count >= N) { // 충분히 만들 수 있음 -> 더 긴 길이 가능
                answer = mid; // 일단 저장 (갱신)
                left = mid + 1;
            } else { // 부족함 -> 길이 줄여야 함
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
