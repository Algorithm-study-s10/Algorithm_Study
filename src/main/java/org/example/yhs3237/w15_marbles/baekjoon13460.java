package org.example.yhs3237.w15_marbles;

import java.io.*;
import java.util.*;

public class baekjoon13460 {
    static int N, M;
    static char[][] board;
    // 델타 배열 (상우하좌)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // 구슬들의 상태
    static class State {
        int rr, rc, br, bc, count;

        public State(int rr, int rc, int br, int bc, int count) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        board = new char[N][M]; // 입력 받은 보드

        int Rr = 0, Rc = 0, Br = 0, Bc = 0;

        for (int i = 0 ; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);

                // 빨간 구슬과 파란 구슬의 위치 저장
                if (board[i][j] == 'R') {
                    Rr = i;
                    Rc = j;
                    board[i][j] = '.';
                }

                if (board[i][j] == 'B') {
                    Br = i;
                    Bc = j;
                    board[i][j] = '.';
                }
            }
        }

        /*
         * R과 B는 반대로 움직임
         * R이 위로 가면 B는 아래로 가는 식
         * 목표는 R이 구멍으로 빠지도록 하는 것
        */
        int result = moveMarbles(Rr, Rc, Br, Bc);
        System.out.println(result);
    }

    static int moveMarbles(int rr, int rc, int br, int bc) {
        Queue<State> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        queue.offer(new State(rr, rc, br, bc, 0));
        visited[rr][rc][br][bc] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.count >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                int nrr = cur.rr, nrc = cur.rc;
                int nbr = cur.br, nbc = cur.bc;
                boolean redInHole = false, blueInHole = false;

                // 빨간 구슬 이동
                while (board[nrr + dr[d]][nrc + dc[d]] != '#' && board[nrr][nrc] != 'O') {
                    nrr += dr[d];
                    nrc += dc[d];
                    if (board[nrr][nrc] == 'O') redInHole = true;
                }

                // 파란 구슬 이동
                while (board[nbr + dr[d]][nbc + dc[d]] != '#' && board[nbr][nbc] != 'O') {
                    nbr += dr[d];
                    nbc += dc[d];
                    if (board[nbr][nbc] == 'O') blueInHole = true;
                }

                if (blueInHole) continue; // 실패
                if (redInHole) return cur.count + 1; // 성공

                // 겹치는 경우 조정
                if (nrr == nbr && nrc == nbc) {
                    int redDist = Math.abs(cur.rr - nrr) + Math.abs(cur.rc - nrc);
                    int blueDist = Math.abs(cur.br - nbr) + Math.abs(cur.bc - nbc);
                    if (redDist > blueDist) {
                        nrr -= dr[d];
                        nrc -= dc[d];
                    } else {
                        nbr -= dr[d];
                        nbc -= dc[d];
                    }
                }

                if (!visited[nrr][nrc][nbr][nbc]) {
                    visited[nrr][nrc][nbr][nbc] = true;
                    queue.offer(new State(nrr, nrc, nbr, nbc, cur.count + 1));
                }
            }
        }

        return -1;
    }
}
