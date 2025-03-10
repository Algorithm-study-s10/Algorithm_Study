package org.example.yhs3237.w05_sugarDelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 재귀 사용
public class baekjoon2839_dp {
    static int[] sugar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  // 설탕의 무게

        sugar = new int[N+1]; // 설탕 무게에 따른 최소 봉지 개수
        Arrays.fill(sugar, 10001); // 배열을 큰 수로 초기화 (최소값 찾기 위해)

        // 0kg 일 때는 봉지 0개 필요, 3kg/5kg 일 때 각각 봉지 1개 필요
        sugar[0] = 0;
        if (N>=3) sugar[3] = 1;
        if (N>=5) sugar[5] = 1;

        int result = minimumBag(N);

        //result가 10001보다 큼 = 3/5kg으로 만들 수 없음 = -1 출력, 그 외에는 결과값(sugar[n]) 출력
        if (result >= 10001) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    // 최소 봉지 개수 구하기
    static int minimumBag(int n) {
        if (n < 0) return 10001; // n이 0보다 작으면 성립 X = 10001 반환
        if (sugar[n] != 10001)  return sugar[n]; // sugar[n]이 10001이 아님 = 갱신되었음 = 해당 값 반환

        sugar[n] = Math.min(minimumBag(n - 3), minimumBag(n - 5)) + 1; // 최소 봉지 개수 갱신

        return sugar[n];
    }
}
