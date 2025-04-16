package org.example.yhs3237.w10_escape;

import java.io.*;
import java.util.*;

public class baekjon3055 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    // 델타 배열 (상, 우, 하, 좌)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<int[]> water = new LinkedList<>();
    static Queue<int[]> dochi = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S') {
                    dochi.offer(new int[]{i, j, 0}); // 고슴도치 위치와 시간
                    visited[i][j] = true;
                } else if (map[i][j] == '*') {
                    water.offer(new int[]{i, j}); // 물 위치
                }
            }
        }

        int result = bfs();
        if (result == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    static int bfs() {
        while (!dochi.isEmpty()) {
            // 1. 물 먼저 퍼짐
            int wSize = water.size();
            for (int i = 0; i < wSize; i++) {
                int[] waterLoc = water.poll();
                int wx = waterLoc[0];
                int wy = waterLoc[1];

                for (int d = 0; d < 4; d++) {
                    int nx = wx + dx[d];
                    int ny = wy + dy[d];

                    if (onMap(nx, ny) && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        water.offer(new int[]{nx, ny});
                    }
                }
            }

            // 2. 고슴도치 이동
            int dSize = dochi.size();
            for (int i = 0; i < dSize; i++) {
                int[] now = dochi.poll();
                int x = now[0];
                int y = now[1];
                int time = now[2];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (!onMap(nx, ny) || visited[nx][ny]) continue;

                    // 목표 지점에 도착
                    if (map[nx][ny] == 'D') {
                        return time + 1;
                    }

                    // .인 곳만 이동 가능
                    if (map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        dochi.offer(new int[]{nx, ny, time + 1});
                    }
                }
            }
        }
        return -1; // 도달 못한 경우
    }

    // 범위 체크
    static boolean onMap(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }
}
