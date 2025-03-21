package org.example.rt8632.w06_KevinBacon6Level;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1389 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int N, min, answer;
    static class info {
        int idx;
        int count;
        public info(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        answer = 0;
        min = 1000000;
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i=1;i<=N;i++) {
            bfs(i);
        }
        System.out.println(answer);
    }

    public static void bfs(int start) {
        Queue<info> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.add(new info(start,0));
        visited[start] = true;
        int result = 0;
        while(!q.isEmpty()) {
            info cur = q.poll();
            for(int next : graph.get(cur.idx)) {
                if(!visited[next]) {
                    result += cur.count + 1;
                    visited[next] = true;
                    q.add(new info(next, cur.count+1));
                }
            }
        }
        if(result < min) {
            min = result;
            answer = start;
        }
    }
}
