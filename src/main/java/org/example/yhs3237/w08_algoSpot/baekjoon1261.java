package org.example.yhs3237.w08_algoSpot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class baekjoon1261 {
    static class Room {
        int x, y, cost;

        public Room(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    static int N, M;
    static int[][] maze;
    static int[][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        maze = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = input.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Deque<Room> dq = new ArrayDeque<>();
        dq.add(new Room (0,0,0));
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            Room current = dq.poll();
            int cx = current.x;
            int cy = current.y;
            int cost = current.cost;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (onMaze(nx, ny)) {
                    int newCost = cost + maze[nx][ny];

                    if (dist[nx][ny] > newCost) {
                        dist[nx][ny] = newCost;

                        if (maze[nx][ny] == 0) {
                            dq.addFirst(new Room(nx, ny, newCost));
                        } else {
                            dq.addLast(new Room(nx, ny, newCost));
                        }
                    }
                }
            }
        }

        System.out.println(dist[N-1][M-1]);
    }

    static boolean onMaze(int nx, int ny) {
        return (nx >= 0 && nx < N && ny >= 0 && ny < M);
    }

}
