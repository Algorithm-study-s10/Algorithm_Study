package org.example.rt8632.w18_ChickenDelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15686 {
    static int N,M;
    static int[][] map;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> check = new ArrayList<>();
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    house.add(new int[]{i,j});
                } else if(map[i][j]==2){
                    chicken.add(new int[]{i,j});
                }
            }
        }

        visited = new boolean[chicken.size()];

        recur(0,0);
        System.out.println(result);

    }

    static void recur(int depth, int start){
        if(depth == M){
            int sum = 0;
            for(int[] h : house){
                int min = Integer.MAX_VALUE;
                for(int[] c : check){
                    int abs = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min  = Math.min(min, abs);
                }
                sum += min;
            }
            result = Math.min(result,sum);
            return;
        }

        for(int i=start; i< chicken.size();i++){
            if(!visited[i]) {
                visited[i] = true;
                check.add(chicken.get(i));
                recur(depth+1,i+1);
                visited[i] = false;
                check.remove(chicken.get(i));
            }
        }

    }
}
