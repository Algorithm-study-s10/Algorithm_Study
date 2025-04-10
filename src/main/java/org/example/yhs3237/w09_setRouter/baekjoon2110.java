package org.example.yhs3237.w09_setRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수

        int[] houses = new int[N]; // 집의 좌표 정보 (house[i] 값 : 좌표)
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses); // 오름차순 정렬

        int max = 0; // 최소 거리들 중 최대 거리
        int start = 1;
        int end = houses[N-1]-houses[0];

        while (start<=end) {
            int mid = (start+end)/2;

            int lastInstalled = houses[0]; // 첫번째 집에 설치하는 것부터 시작
            int count = 1;

            for (int i = 1; i < houses.length; i++) {
                // 인접한 집들의 거리가 mid 이상이 되어야 함
                // mid 이하로 설치하면 그 값이 최소값이 되므로 안됨!
                if (houses[i] - lastInstalled >= mid) {
                    count++;
                    lastInstalled = houses[i]; // 마지막에 공유기를 설치한 집의 좌표
                }
            }

            // mid를 최소 거리로 해서 설치했을 때 공유기 개수가 C개 이상이면 mid보다 늘려도 된다는 의미
            // start, end 범위 조정
            if (count >= C) {
                max = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        System.out.println(max);
    }
}
