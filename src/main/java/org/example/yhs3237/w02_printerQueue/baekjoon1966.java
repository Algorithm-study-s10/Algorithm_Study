package org.example.yhs3237.w02_printerQueue;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 문서의 개수
            int M = Integer.parseInt(st.nextToken()); // 인쇄 순서가 궁금한 문서


            Queue<int[]> queue = new LinkedList<>(); // 문서의 중요도와 위치 저장
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙 (중요도 정보 저장)

            // 큐에 문서 정보(중요도, 위치 / 중요도) 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken()); // 문서의 중요도 (입력받음)
                queue.add(new int[] {priority, i}); // 큐에 추가
                priorityQueue.add(priority); // 우선순위 큐에도 추가
            }

            int turn = 0; // 실행 순서

            while (!queue.isEmpty()) {
                int[] out = queue.poll(); // 큐에서 문서 꺼내기

                if (out[0] < priorityQueue.peek()) { // 최댓값이 아니라면 다시 큐에 추가
                    queue.add(out);
                } else {
                    turn++; // 실행됨
                    priorityQueue.poll(); // 우선순위 큐에서도 제거

                    if (out[1] == M) { // 목표 문서 프린트 완료
                        break;
                    }
                }
            }
            sb.append(turn).append("\n");
        }
        System.out.println(sb);
    }
}
