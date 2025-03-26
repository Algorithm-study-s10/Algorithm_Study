package org.example.yhs3237.w07_hidAndSeek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1697 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        K = Integer.parseInt(st.nextToken()); // 동생의 위치

        boolean[] visited = new boolean[100001]; // 방문여부 (수빈이는 K보다 큰 위치로도 이동할 수 있으므로 100001로 초기화)

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {N, 0}); // 시작 위치 (수빈이의 위치)

        int answer = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int current = now[0]; // 현재 위치
            int time = now[1]; // 몇 초?

            // 수빈이의 이동 델타 배열
            int[] move = new int[] {current+1, current-1, current*2};

            // 동생을 찾은 경우
            if (current == K) {
                answer = time;
                break;
            }

            for (int i = 0; i < 3; i++) {
                int next = move[i];
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, time+1});
                }
            }
        }

        System.out.println(answer);
    }
}
