package org.example.rt8632.w10_Escape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_3055_v2 {
    static char[][] map;
    static int[][] water, gosumdochi;
    static boolean[][] visited;
    static int R, C;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new int[R][C];
        gosumdochi = new int[R][C];

        int gosumdochiX = 0, gosumdochiY = 0;
        int goalX = 0, goalY = 0;

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    gosumdochiX = i;
                    gosumdochiY = j;
                }
                if (map[i][j] == 'D') {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        // 물 BFS
        waterBfs();

        // 고슴도치 BFS
        visited = new boolean[R][C];
        gosumdochiBfs(gosumdochiX, gosumdochiY);

        // 최종 도착 여부 판단
        if (gosumdochi[goalX][goalY] == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(gosumdochi[goalX][goalY]);
        }
    }

    static void waterBfs() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;
                if (visited[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == 'D')
                    continue;

                visited[nx][ny] = true;
                water[nx][ny] = water[cx][cy] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }

    static void gosumdochiBfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;
                if (visited[nx][ny] || map[nx][ny] == 'X')
                    continue;
                // 물보다 늦게 도착하면 이동 불가
                if (water[nx][ny] != 0 && gosumdochi[cx][cy] + 1 >= water[nx][ny])
                    continue;

                gosumdochi[nx][ny] = gosumdochi[cx][cy] + 1;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}

