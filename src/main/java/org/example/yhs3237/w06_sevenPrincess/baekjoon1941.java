package org.example.yhs3237.w06_sevenPrincess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baekjoon1941 {
    static char[] seats;
    static int count;
    static int[] result;
    static boolean[] visited;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        seats = new char[25]; // 5x5 좌표를 1차원 배열로 저장
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                seats[i * 5 + j] = input.charAt(j);
            }
        }

        result = new int[7];  // 선택된 7개 칸의 인덱스 저장
        visited = new boolean[25]; // 조합을 만들 때 방문 여부 체크
        count = 0;

        pickSeven(0, 0, 0, 0); // 조합 생성 시작

        br.close();
        System.out.println(count);
    }

    // 7개의 칸을 선택하는 백트래킹 함수
    public static void pickSeven(int depth, int index, int dasom, int doyun) {
        // 'Y' 개수가 3개를 초과하면 가지치기 (불가능한 조합)
        if (doyun > 3) return;

        // 7개를 모두 선택했을 때
        if (depth == 7) {
            // 선택된 칸들의 연결 여부 확인
            isUsed = new boolean[25]; // 연결 검사용 방문 배열 초기화
            if (isConnected(result[0])) {
                count++;
            }
            return;
        }

        // 조합을 생성하는 과정 (오름차순 선택, 중복 방지)
        for (int i = index; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = i;

                // 'S'와 'Y' 개수 업데이트
                int newDasom = dasom + (seats[i] == 'S' ? 1 : 0);
                int newDoyun = doyun + (seats[i] == 'Y' ? 1 : 0);

                pickSeven(depth + 1, i + 1, newDasom, newDoyun);

                // 방문 취소 (백트래킹)
                visited[i] = false;
            }
        }
    }

    // BFS를 이용한 연결 확인
    public static boolean isConnected(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        isUsed[start] = true;
        int connectedCount = 1;

        // 상하좌우 이동 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / 5;
            int y = cur % 5;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newLoc = nx * 5 + ny;

                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) { // 유효한 범위인지 확인
                    for (int j = 0; j < 7; j++) {
                        if (result[j] == newLoc && !isUsed[newLoc]) { // 선택된 7개 칸 중 하나이며 방문하지 않은 경우
                            isUsed[newLoc] = true;
                            queue.add(newLoc);
                            connectedCount++;
                        }
                    }
                }
            }
        }

        return connectedCount == 7; // 7개가 모두 연결되어야 함
    }
}
