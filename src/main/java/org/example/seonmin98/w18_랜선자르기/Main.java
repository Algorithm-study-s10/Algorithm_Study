package org.example.seonmin98.w18_랜선자르기;

import java.io.*;
import java.util.*;

public class Main {
    static List<Long> arr = new ArrayList<>();
    static int K;
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Long.parseLong(st.nextToken());

        for (int i = 0; i < K; i++) {
            arr.add(Long.parseLong(br.readLine()));
        }

        long start = 1;
        long end = Collections.max(arr);
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (isOk(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean isOk(long len) {
        long sum = 0;
        for (long i : arr) {
            sum += i / len;
            if (sum >= N) return true;
        }
        return sum >= N;
    }
}