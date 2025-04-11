package org.example.rt8632.w10_Router;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //집의 개수
        int c = Integer.parseInt(st.nextToken()); //공유기의 개수

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int low = 1; //최소 거리
        int high = arr[n-1]; //최대 거리

        while(low <= high) {
            int mid = (low + high) / 2;
            int position = 0; //공유기 설치 위치
            int count = 1; //공유기 설치 개수
            for(int i = 0; i < n; i++) {
                if(arr[i] - arr[position] >=mid) {
                    position = i;
                    count++;
                }
            }

            if(count < c){ //공유기의 개수보다 적으면 거리 최대값을 줄임
                high = mid - 1;
                continue;
            }

            //반대의 경우 거리 최소값을 늘림
            low = mid + 1;
        }

        System.out.println(low-1);
    }

}
