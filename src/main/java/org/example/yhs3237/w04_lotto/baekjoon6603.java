package org.example.yhs3237.w04_lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon6603 {
    static int[] numbers; // 입력된 숫자들
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();

        while (true) {
            String input = br.readLine(); // 한 줄 입력
            StringTokenizer st = new StringTokenizer(input);
            numbers = new int[Integer.parseInt(st.nextToken())];

            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer> lotto = new ArrayList<>(); // 뽑은 숫자들을 담을 리스트
            pickNum(lotto, 0);
            sb.append("\n");

            if (input.equals("0")) break; // 0 입력되면 멈춤
        }
        System.out.println(sb);
    }

    // 숫자들 뽑기
    static void pickNum(ArrayList<Integer> lotto, int index) {
        // 기저조건 (숫자 6개 뽑으면 멈춤)
        if (lotto.size() == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(lotto.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 인덱스가 숫자들의 배열 길이 넘어가면 멈춤
        if (index >= numbers.length) return;

        lotto.add(numbers[index]);
        pickNum(lotto, index+1); // 재귀
        lotto.remove(lotto.size()-1); // 백트래킹
        pickNum(lotto, index+1);
    }
}