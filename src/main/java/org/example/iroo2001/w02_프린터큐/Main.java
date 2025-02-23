package org.example.iroo2001.w02_프린터큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[] priority;
	static ArrayDeque<Integer> q;

	static int printer() {
		int doc;

		while (!q.isEmpty()) {
			doc = q.poll();
			for (int i = 9; i > doc % 10; i--) {
				if (priority[i] > 0) {
					q.offer(doc);
					doc = 0;
					break;
				}
			}
			if (doc > 10) {
				return q.size();
			}
			priority[doc % 10]--;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		int t, n, m, doc;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			priority = new int[10];
			q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				doc = Integer.parseInt(st.nextToken()) + ((i == m) ? 10 : 0);
				priority[doc % 10]++;
				q.offer(doc);
			}
			System.out.println(n - printer());
		}

		br.close();
	}
}