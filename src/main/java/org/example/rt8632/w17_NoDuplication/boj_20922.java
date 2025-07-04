package org.example.rt8632.w17_NoDuplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int ans = 0;
        int[] count = new int[100001];

        while (end < N) {
            count[arr[end]]++;

            //K 초과하는동안 범위 줄이기 (안넘을때까지)
            while(count[arr[end]] > K) {
                count[arr[start]]--;
                start++; //start 이동하면서 범위 줄임
            }
            ans = Math.max(ans, end - start + 1);
            end++; //end를 하나씩 늘림
        }
        System.out.println(ans);
    }

}
