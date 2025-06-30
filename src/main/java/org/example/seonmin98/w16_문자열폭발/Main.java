package org.example.seonmin98.w16_문자열폭발;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();   // 원본 문자열
        String key   = br.readLine();   // 폭발 문자열

        int n = input.length();
        int k = key.length();
        char[] keyArr = key.toCharArray();

        // 결과 버퍼를 스택처럼 사용
        char[] stack = new char[n];
        int top = 0;                    // 버퍼에 현재 쌓인 문자 수

        for (int i = 0; i < n; i++) {
            stack[top] = input.charAt(i);
            top++;

            // 버퍼에 key 길이 이상 쌓였을 때만 비교
            if (top >= k) {
                boolean match = true;
                for (int j = 0; j < k; j++) {
                    if (stack[top - k + j] != keyArr[j]) {
                        match = false;
                        break;
                    }
                }
                // key 발견 → pop
                if (match) {
                    top -= k;
                }
            }
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(new String(stack, 0, top));
        }
    }
}
