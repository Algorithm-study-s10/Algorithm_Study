package org.example.iroo2001.w04_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, r; // nCr에서의 n과 r
	static int[] pool, selected; // pool : 입력받은 숫자 집합, selected : comb메서드에서 선택한 조합을 임시저장
	static StringBuilder out; // 출력 버퍼

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		out = new StringBuilder();

		r = 6; // 이 문제에서는 뽑는 수가 6으로 고정
		selected = new int[r]; // r개의 수를 뽑은 조합을 임시저장할 배열이므로 r로 초기화
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0) // 0이 입력되면 종료
				break;

			pool = new int[n]; // 입력받을 n개의 수를 저장할 배열
			for (int i = 0; i < n; i++)
				pool[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(pool); // 문제에서 요구한대로 사전 순으로 출력하기 위해 정렬 수행

			comb(0, 0); // 모든 조합 탐색

			out.append("\n"); // 각 테스트케이스 사이에 빈 줄 출력
		}
		System.out.print(out);

		br.close();
	}

	static void comb(int idx, int sidx) {
		if (sidx == r) { // r개의 수를 선택했다면
			for (int i : selected)
				out.append(i).append(" "); // 선택된 수를 출력 버퍼에 저장
			out.append("\n");
			return; // r개의 수를 선택했으므로 종료
		}
		if (idx == n)
			return; // r개의 수를 선택하기 전에, 선택할 수가 더이상 없으므로 종료
		selected[sidx] = pool[idx]; // 수 선택
		comb(idx + 1, sidx + 1); // 현재 선택한 수를 가지고, 다음 수 선택
		comb(idx + 1, sidx); // 현재 선택한 수는 버리고, 다시 수 선택
	}
}