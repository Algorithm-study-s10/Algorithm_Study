package org.example.rt8632.w05_SugarDelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int count = 0;
        while (N > 2) {
            if(N % 5 == 0){
                count += N/5;
                N -= 5*(N/5);
            }
            if(N>=3){
                N -= 3;
                count++;
            }
        }
        if(N != 0){
            count = -1;
        }
        System.out.println(count);
    }
}
