package org.example.seonmin98.w14_가장긴짝수연속한부분수열;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int oddCnt = 0;
		int maxLen = 0;
		
		while (end < N) { //end을 확인하면서 구간 늘려주기
			if (arr[end] % 2 == 0) {
				end++; //짝수인 경우 그냥 넘어가기 (end++)
			} else {
				if (oddCnt < K) { //홀수이고 제거할 cnt가 남아 있는 경우, (end++, cnt++)
					oddCnt++;
					end++;
				} else { //홀수인데 이미 K번 제거한 경우, (start++) 로 구간 갱신
					if (arr[start] % 2 == 1) { //이때, 갱신 전 start가 홀수인 경우, (cnt--)
						oddCnt--;
					}
					start++;
				}
			}
			//현재 구간에서 짝수의 개수 = (end - start - oddCnt)
			maxLen = Math.max(maxLen, end - start - oddCnt);
		}

		System.out.println(maxLen);
	}
}
