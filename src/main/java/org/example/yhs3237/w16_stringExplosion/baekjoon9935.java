package org.example.yhs3237.w16_stringExplosion;

import java.io.*;
import java.util.*;

public class baekjoon9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sentence = br.readLine(); // 문자열
        String explosion = br.readLine(); // 폭발 문자열

        // 문자열을 쪼개서 담을 리스트
        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < sentence.length(); i++) {
            // 문자열 쪼개서 리스트에 넣기
            charList.add(sentence.charAt(i));

            // 리스트의 길이가 폭발 문자열의 길이 이상이 되는 순간부터 확인
            if (charList.size() >= explosion.length()) {
                // (현재 위치-폭발 문자열의 길이) 시점부터 현재 위치까지 확인
                boolean isSame = true; // 폭발 문자열과 일치하는지 여부
                for (int j = 0; j < explosion.length(); j++) {
                    if (charList.get(charList.size() - explosion.length() + j) != explosion.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }

                // 폭발 문자열과 일치하는 경우, list에서 역순으로 폭발 문자열 제거
                if (isSame) {
                    for (int k = 0; k < explosion.length(); k++) {
                        charList.remove(charList.size() - 1);
                    }
                }
            }
        }

        // 결과 출력
        // list가 비어있지 않다면 남은 문자열 출력
        // 비어있다면 남은 문자열이 없는 것이므로 FRULA 출력
        if (!charList.isEmpty()) {
            StringBuilder result = new StringBuilder();

            for (char c : charList) {
                result.append(c);
            }

            System.out.println(result.toString());
        } else {
            System.out.println("FRULA");
        }

    }
}
