package org.example.yhs3237.w02_topStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class beakjoon2493 {
    // 탑의 정보
    static class Top {
        int num; //탑의 번호
        int height; // 탑의 높이

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 탑의 수

        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Top> stack = new Stack<>(); // 탑을 보관할 스택

        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken()); // 압력받은 탑의 높이
            Top t = new Top(i, h);

            if (stack.isEmpty()) { // 스택이 비어있을 땐 무조건 push
                stack.push(t);
                sb.append(0).append(" "); // 왼쪽에 탑이 하나도 없으므로 무조건 0 출력
            } else {
                // 스택이 비어있지 않은 경우
                // 바로 옆에 있는 탑의 높이가 더 작다면 큰 값이 나올때까지 pop
                // 어차피 작은 탑은 신호 보내도 못 받기 때문에 이제 쓸모가 없음
                while (!stack.isEmpty() && stack.peek().height < t.height) {
                    stack.pop();
                }

                if (stack.isEmpty()) { // pop하다가 배열이 비었다면 0 출력
                    sb.append(0).append(" ");
                } else { // 더 큰 탑을 만났다면 해당 탑의 변호를 출력
                    sb.append(stack.peek().num).append(" ");
                }
                stack.push(t); // 새로 입력받은 탑의 정보 push
            }
        }

        System.out.println(sb);
    }
}