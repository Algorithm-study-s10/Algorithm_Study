package org.example.seonmin98.w07_숨바꼭질;

import java.util.*;

public class Main {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		if (n == k) {
			System.out.println(0);
			return ;
		}
		
		int[] vis = new int[100001];
		Arrays.fill(vis, -1);
		
		Queue<Integer> queue = new LinkedList<>();
		vis[n] = 0;
		queue.add(n);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 0; i < 3; i++) {
				int nxt = dat(cur, i);
				if (nxt < 0 || nxt > 100000 || vis[nxt] != -1) continue;
				if (nxt == k) {
					System.out.println(vis[cur] + 1);
					return ;
				}
				vis[nxt] = vis[cur] + 1;
				queue.add(nxt);
			}
		}
	}
	
	private static int dat(int n, int i) {
		if (i == 0) return n - 1;
		if (i == 1) return n + 1;
		return n * 2;
	}
}