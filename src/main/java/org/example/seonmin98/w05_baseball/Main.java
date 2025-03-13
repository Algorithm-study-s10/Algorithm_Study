package org.example.seonmin98.w05_baseball;

import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int attacks[][];
	static boolean check[];
	static int maxScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		//선수별 이닝 타격 결과 입력받기 
		attacks = new int[10][n];
		StringTokenizer st;
		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= 9; y++) {
				attacks[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		//9명 순서 순열조합 (단, 4번은 1번으로 고정)
		//각 순열별 득점 계산 -> 최고점 구하기 
		check = new boolean[10];
		run(1, new int[10]);
		
		System.out.println(maxScore);
	}
	
	static void run(int lev, int[] path) {
		if (lev == 10) {
			calculate(path);
			return ;
		}
		
		if (lev == 4) { //4번 타자는 무조건 1
			path[lev] = 1;
			run(lev + 1, path);
			return ;
		}
		
		for (int i = 2; i <= 9; i++) {
			if (check[i] == true) continue;
			
			path[lev] = i;
			check[i] = true;
			run(lev + 1, path);
			check[i] = false;
		}
	}
	
	static void calculate(int[] player) {
	    
	    int score = 0;
	    int idx = 1; // 타순 인덱스 (이닝별로 초기화하면 안됨!!!)
	    
	    for (int i = 0; i < n; i++) { // 각 이닝에 대해
	        int outCnt = 0;
	        int[] base = new int[4]; // 홈 1루 2루 3
	        
	        while (outCnt < 3) {
	            int hitter = player[idx]; // 현재 타자
	            int result = attacks[hitter][i]; // 타격 결과
	            
	            if (result == 0) { // 아웃
	                outCnt++;
	            } else {
	                base[0] = 1; // 타자는 출루
	                
	                // 주자 진루 (뒤에서부터 계산해야 중복 진루가 없음)
	                for (int b = 3; b >= 0; b--) {
	                    if (base[b] == 1) { // 해당 베이에 주자가 있으면
	                        int newPos = b + result;
	                        if (newPos >= 4) { // 홈을 넘어가면 득점
	                            score++;
	                        } else { // 다른 루로 진루
	                            base[newPos] = 1;
	                        }
	                        base[b] = 0; // 원래 위치 비움
	                    }
	                }
	            }
	            
	            idx++;
	            if (idx > 9) idx = 1; // 다음 타자 (9번 타자 이후 1번 타자로)
	        }
	    }
	    
	    maxScore = Math.max(maxScore, score);
	}

}

////주자 출루
//for (Map.Entry<Integer, Integer> entry : base.entrySet()) {
//    base.put(entry.getKey(), entry.getValue() + attack);
//}
//base.put(idx, attack);
//
////득점 주자 정리 
//PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
//        (a, b) -> Integer.compare(a.getValue(), b.getValue()) // value 기준 오름차순 정렬
//    );
//pq.addAll(base.entrySet());
//
//while (!pq.isEmpty() && pq.peek().getValue() >= 4) {
//    base.remove(pq.peek().getKey());
//    pq.poll();
//    inningScore++;
//}

