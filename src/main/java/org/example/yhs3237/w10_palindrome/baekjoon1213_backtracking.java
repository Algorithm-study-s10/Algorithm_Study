package org.example.yhs3237.w10_palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baekjoon1213_backtracking {
    static char[] characters;
    static boolean[] visited;
    static char[] result;
    static int limit;
    static ArrayList<String> answerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputString = br.readLine(); // 입력받은 문자열
        characters = inputString.toCharArray(); // 문자열 하나씩 잘라서 배열에 저장 (사용할 수 있는 문자 후보)

        limit = inputString.length(); // 문자열의 길이 (백트래킹 시 정지조건)
        visited = new boolean[limit]; // 문자 사용 여부 체크 (방문배열)
        result = new char[limit]; // 경우의 수 저장할 배열
        answerList = new ArrayList<>(); // 정답 저장할 배열

        makingString(0);

        Collections.sort(answerList); // 정답 후보들 정렬

        if (answerList.size() == 0) { // 정답 후보가 없음 (=팰린드롬 불가능)
            System.out.println("I'm Sorry Hansoo");
        } else { // 정답 후보가 여러개 일 때, 정렬한 list의 첫번째 값 출력
            System.out.println(answerList.get(0));
        }
    }

    static void makingString(int depth) {
        // 정지조건
        if (depth == limit) {
            // 디버깅용
            // System.out.println(Arrays.toString(result));
            if (checkPalindrome(result)) {
                String answer = "";
                for (int i = 0; i < limit; i++) {
                    answer += result[i];
                }
                // 디버깅용
                // System.out.println(answer);
                answerList.add(answer);
            }
        }

        // 문자들의 순열 구하기
        for (int i = 0; i < limit; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = characters[i];
                makingString(depth+1); // 백트래킹
                visited[i] = false;
            }
        }
    }

    // 구성한 문자열이 펠린드롬인지 확인
    static boolean checkPalindrome(char[] result) {
        int first = 0; // 첫번째 인덱스
        int last = result.length - 1; // 마지막번째 인덱스
        while (first < last) {
            if (result[first] != result[last]) {
                return false; // 달라지는 순간 false 반환
            } else {
                first++;
                last--;
            }
        }
        return true;
    }
}
