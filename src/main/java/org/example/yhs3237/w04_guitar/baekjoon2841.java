package org.example.yhs3237.w04_guitar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjoon2841 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer>[] melodies = new Stack[7]; // 1~6번 줄을 위한 스택 배열
        for (int i = 1; i <= 6; i++) {
            melodies[i] = new Stack<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 음의 수
        int P = Integer.parseInt(st.nextToken()); // 프랫의 수

        int fingers = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // [0] : 줄의 번호, [1] : 프랫의 번호
            int line = Integer.parseInt(st.nextToken());  // 줄 번호
            int fret = Integer.parseInt(st.nextToken()); // 프랫 번호

            while (!melodies[line].isEmpty() && melodies[line].peek() > fret) {
                // 해당 줄의 스택이 비어있지 않고, 스택의 top이 현재 프랫보다 크면 손가락을 떼야하므로 pop & ++
                melodies[line].pop();
                fingers++;
            }

            if (!melodies[line].isEmpty() && melodies[line].peek() == fret) {
                // 같은 프렛이 이미 눌려있다면 패스
                continue;
            }

            // 새로운 프랫을 눌러야 한다면 push
            melodies[line].push(fret);
            fingers++;
        }

        System.out.println(fingers);
    }
}
