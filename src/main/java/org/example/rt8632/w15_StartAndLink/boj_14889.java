package org.example.rt8632.w15_StartAndLink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889 {
    static int[][] s;
    static boolean[] visited;
    static int N, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        s = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N];
        recur(0,0);
        System.out.println(min);
    }

    static void recur(int idx, int count){
        if(count == N/2){
            diff();
            return;
        }

        for(int i=idx; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                recur(i+1, count+1);
                visited[i] = false;
            }
        }
    }

    static void diff(){
        int start = 0;
        int link = 0;

        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(visited[i] && visited[j]){
                    start += s[i][j];
                    start += s[j][i];
                } else if(!visited[i] && !visited[j]){
                    link += s[i][j];
                    link += s[j][i];
                }
            }
        }

        int val = Math.abs(start-link);

        min = Math.min(min, val);
    }
}

