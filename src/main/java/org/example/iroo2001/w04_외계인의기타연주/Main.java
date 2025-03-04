package org.example.iroo2001.w04_외계인의기타연주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, P;
	static Stack<Integer>[] lines;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		lines = new Stack[N];

		int line, fret, result;
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			line = Integer.parseInt(st.nextToken());
			fret = Integer.parseInt(st.nextToken());
			if (lines[line] == null) {
				lines[line] = new Stack<>();
			}

			// 현재 줄에서 가장 높은 프렛이 현재 프렛보다 높은 경우
			// 현재 줄에서 가장 높은 프렛을 pop
			while (!lines[line].isEmpty() && (lines[line].peek() > fret)) {
				lines[line].pop();
				result++;
			}

			// 현재 줄에서 가장 높은 프렛이 현재 프렛보다 작은 경우(혹은 없는 경우)에만 push, result++
			// (현재 프렛과 같은 경우에는 push 필요x, result++ 필요x
			if (lines[line].isEmpty() || lines[line].peek() < fret) {
				lines[line].push(fret);
				result++;
			}
		}
		
		System.out.println(result);

		br.close();
	}
}