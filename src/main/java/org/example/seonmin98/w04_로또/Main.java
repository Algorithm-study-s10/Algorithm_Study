package org.example.seonmin98.w04_로또;

import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0) break ;
			
			arr = new int[k];
			for (int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer> list = new ArrayList<>();
			run(0, 0, list);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void run(int idx, int lev, List<Integer> list) {
		if (lev == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(list.get(i) + " ");
			}
			sb.append("\n");
			return ;
		}
		if (idx >= k) return ;

		list.add(arr[idx]);
		run(idx + 1, lev + 1, list);

		list.remove(list.size() - 1);
		run(idx + 1, lev, list);
	}
}
