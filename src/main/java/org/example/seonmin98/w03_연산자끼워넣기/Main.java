package org.example.seonmin98.w03_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class Main {
	static int plusCnt, minusCnt, mulCnt, divCnt;
	static int[] num;
	static int n;
	static int maxRet = Integer.MIN_VALUE;
	static int minRet = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		num = new int[n]; //숫자를 담아둘 배열열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()); //각 연산자의 개수 저장
		plusCnt = Integer.parseInt(st.nextToken());
		minusCnt = Integer.parseInt(st.nextToken());
		mulCnt = Integer.parseInt(st.nextToken());
		divCnt = Integer.parseInt(st.nextToken());
		
		run(num[0], 0);
		
		System.out.println(maxRet);
		System.out.println(minRet);
	}
	
	public static void run(int ret, int lev) {
		if (lev == n - 1) {
			maxRet = Math.max(ret, maxRet);
			minRet = Math.min(ret, minRet);
			return ;
		}
		if (plusCnt > 0) {
			plusCnt--;
			run(ret + num[lev + 1], lev + 1);
			plusCnt++;
		}
		if (minusCnt > 0) {
			minusCnt--;
			run(ret - num[lev + 1], lev + 1);
			minusCnt++;
		}
		if (mulCnt > 0) {
			mulCnt--;
			run(ret * num[lev + 1], lev + 1);
			mulCnt++;
		}
		if (divCnt > 0) {
			divCnt--;
			run(ret / num[lev + 1], lev + 1);
			divCnt++;
		}
	}
}
