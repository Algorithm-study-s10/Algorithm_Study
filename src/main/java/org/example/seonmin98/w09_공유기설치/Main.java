package org.example.seonmin98.w09_공유기설치;

import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] home;
    static Set<Integer> set;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //집의 개수
        C = Integer.parseInt(st.nextToken()); //공유기의 개수
        
        home = new int[N];
        set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
            set.add(home[i]);
        } //집의 좌표 입력받기
        
        Arrays.sort(home);
        
        int left = home[0];
        int right = home[N - 1];
        while (left < right) {
            int mid = (right - left) / 2;
            // 가능하다면
            if (set.contains(home[0] + mid) && isOk(mid)) {
                left = mid + 1;
            } else {// 불가능하다면
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
    
    static boolean isOk(int mid) {
        if (C == 2) return true;
        
        int cnt = 2;
        while (cnt < C) {
            
        }
        
        return false;
    }
}

//public class Main {
//	static int N, C;
//	static int[] home;
//	static boolean[] vis;
//	static int maxCnt;
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken()); //집의 개수
//		C = Integer.parseInt(st.nextToken()); //공유기의 개수
//		
//		home = new int[N];
//		for (int i = 0; i < N; i++) {
//			home[i] = Integer.parseInt(br.readLine());
//		} //집의 좌표 입력받기
//		
//		Arrays.sort(home);
//		
//		maxCnt = Integer.MIN_VALUE;
//		vis = new boolean[N];
//		run(0, new int[C]);
//		
//		System.out.println(maxCnt);
//	}
//	
//	static void run(int lev, int[] ret) {
//		if (lev == C) {
//			calculate(ret);
//			return ;
//		}
//		
//		for (int i = 0; i < N; i++) {
//			if (vis[i]) continue;
//			if (lev > 0 && ret[lev - 1] > i) continue;
//			vis[i] = true;
//			ret[lev] = i;
//			run(lev + 1, ret);
//			vis[i] = false;
//		}
//	}
//	
//	static void calculate(int[] ret) {
//		int curMin = Integer.MAX_VALUE;
//		for (int i = 0; i < C - 1; i++) {
//			int cur = Math.abs(home[ret[i]] - home[ret[i + 1]]);
//			curMin = Math.min(curMin, cur);
//		}
//		maxCnt = Math.max(maxCnt, curMin);
//	}
//}