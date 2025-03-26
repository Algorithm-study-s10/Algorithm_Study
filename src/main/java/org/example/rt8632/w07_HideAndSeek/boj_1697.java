package org.example.rt8632.w07_HideAndSeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1697 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[100001];
		Arrays.fill(visited, false);

		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		int count = 0;
		if(N==K) {
			System.out.println(0);
			return;
		}
		while (!q.isEmpty()) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				int cur = q.poll();
				for (int i = 0; i < 3; i++) {
					int next = nxt(cur, i);
					if (next < 0 || next > 100000 || visited[next])
						continue;
					if (next == K) {
						System.out.println(count+1);
						return;
					}
					visited[next] = true;
					q.add(next);
				}
			}
			count++;
		}
	}

	static int nxt(int n, int i) {
		if (i == 0)
			return n - 1;
		else if (i == 1)
			return n + 1;
		return 2 * n;
	}
}

