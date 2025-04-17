package org.example.yhs3237.w10_palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine(); // 입력 문자열

        int[] alpha = new int[26]; // A(0)~Z(25) 알파벳 개수
        for (char c : input.toCharArray()) {
            alpha[c - 'A']++; // 입력된 문자열에 있는 알파벳이면 해당 값++ (*A의 아스키코드 : 65)
        }

        /*
        ★펠린드롬 구성 조건
        짝수 개의 문자: 모든 문자의 개수가 짝수여야 함
        홀수 개의 문자: 딱 하나만 홀수 개, 나머지는 짝수여야 함
        */

        int oddCount = 0; // 홀수 개수 알파벳 수 확인 (펠린드롬 가능 조건 확인)
        char mid = 0; // 가운데 들어갈 홀수 문자
        StringBuilder half = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (alpha[i] % 2 == 1) {
                oddCount++;
                mid = (char) (i + 'A');
            }

            // 절반 문자열 구성
            for (int j = 0; j < alpha[i] / 2; j++) {
                half.append((char)(i + 'A'));
            }
        }

        if (oddCount > 1) {
            System.out.println("I'm Sorry Hansoo");
        } else {
            String front = half.toString();
            String back = half.reverse().toString();
            String answer = (oddCount == 1) ? front + mid + back : front + back;
            System.out.println(answer);
        }
    }
}
