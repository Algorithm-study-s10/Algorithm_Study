package org.example.yhs3237.w05_sugarDelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 반복문 사용
public class baekjoon2839_for {
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

       for (int i = 6; i <= N; i++) {
           sugar[i] = Math.min(sugar[i-3], sugar[i-5]) + 1; // 최소 봉지 개수 갱신
       }

       // sugar[N]값이 10001과 같거나 큰 경우 = 갱신 X = -1 출력, 그 외에는 sugar[N] 해당하는 값 출력
       if (sugar[N] >= 10001) {
           System.out.println(-1);
       } else {
           System.out.println(sugar[N]);
       }
    }
}
