package org.example.rt8632.w04_Lotto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_6603 {

    static int k;
    static int arr[];
    static int result[];
    static boolean isUsed[] = new boolean[13];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0)
                break;
            arr = new int[k];
            result = new int[6];

            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            recur(0, 0);
            System.out.println();
        }
    }

    public static void recur(int start, int count) {
        if (count == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int j = start; j < k; j++) {
            if (!isUsed[j]) {
                result[count] = arr[j];
                isUsed[j] = true;
                recur(j, count + 1);
                isUsed[j] = false;
            }
        }
    }
}
