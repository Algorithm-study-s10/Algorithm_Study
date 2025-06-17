package org.example.yhs3237.w15_startLink;

import java.io.*;
import java.util.*;

public class baekjoon14889 {
    static int N;
    static int[][] ability;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ability = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[N];
        backTracking(0, 0);
        System.out.println(answer);
    }

    static void backTracking(int start, int depth) {
        if (depth == N/2) {
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (visited[i]) teamA.add(i+1);
                else teamB.add(i+1);
            }

            int different = diffAB(teamA, teamB);
            answer = Math.min(different, answer);
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            backTracking(i+1, depth+1);
            visited[i] = false;
        }
    }

    static int diffAB(List<Integer> teamA, List<Integer> teamB) {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < teamA.size(); i++) {
            for (int j = i+1; j < teamA.size(); j++) {
                int a1 = teamA.get(i) - 1;
                int a2 = teamA.get(j) - 1;

                sumA += ability[a1][a2] + ability[a2][a1];
            }
        }

        for (int i = 0; i < teamB.size(); i++) {
            for (int j = i+1; j < teamB.size(); j++) {
                int b1 = teamB.get(i) - 1;
                int b2 = teamB.get(j) - 1;

                sumB += ability[b1][b2] + ability[b2][b1];
            }
        }

        return Math.abs(sumA-sumB);
    }
}
