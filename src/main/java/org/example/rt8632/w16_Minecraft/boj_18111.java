package org.example.rt8632.w16_Minecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int B = Integer.parseInt(st.nextToken()); // 인벤토리
        int[][] land = new int[N][M];
        int min = 256;
        int max = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                land[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min,land[i][j]);
                max = Math.max(max,land[i][j]);
            }
        }

        int ansTime = Integer.MAX_VALUE;
        int ansHeight = 0;

        // 최대 높이부터 최소 높이까지 시도
        for(int h=max; h>=min;h--){
            int remove = 0;
            int add = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    int diff = land[i][j] - h;
                    if(diff > 0){
                        remove += diff;
                    } else if(diff < 0){
                        add -= diff;
                    }
                }
            }

            if(remove + B >= add) {
                int time = remove * 2 + add;
                if(time < ansTime || time == ansTime && h > ansHeight){
                    ansTime = time;
                    ansHeight = h;
                }
            }
        }
        System.out.println(ansTime + " " + ansHeight);
    }
}
