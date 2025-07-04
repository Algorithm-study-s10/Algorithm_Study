package org.example.yhs3237.w17_noDuplication;

import java.io.*;
import java.util.*;

public class baekjoon20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int K = Integer.parseInt(st.nextToken()); // 정수 K

        // 주어진 수열 입력 받기
        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터 생성
        int start = 0;
        int end = 0;

        // 숫자의 등장 횟수 카운트할 배열
        int[] count = new int[100001];

        // 최장 수열의 길이
        int answer = 0;
        while (end < sequence.length) {
            // end 포인터 이동 (배열 길이 넘어가지 않을 때까지 && 숫자 등장 횟수가 K 이하일 때까지)
            while (end < sequence.length && count[sequence[end]] + 1 <= K) {
                count[sequence[end]]++; // 숫자 등장 횟수++
                end++; // 포인터 이동
            }

            // 수열의 최장 길이 갱신
            int length = end - start;
            answer = Math.max(answer, length);

            // start 포인터 이동 (숫자 등장 횟수--)
            count[sequence[start]]--;
            start++;
        }
        System.out.println(answer);
    }
}
