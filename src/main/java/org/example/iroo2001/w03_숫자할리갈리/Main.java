package org.example.iroo2001.w03_숫자할리갈리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, M, turn;
		String winner;
		ArrayDeque<Integer> doQ, suQ, doGr, suGr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		doQ = new ArrayDeque<>();
		suQ = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			doQ.offerFirst(Integer.parseInt(st.nextToken()));
			suQ.offerFirst(Integer.parseInt(st.nextToken()));
		}

		turn = 0;
		doGr = new ArrayDeque<>();
		suGr = new ArrayDeque<>();
		while (turn < M) {
			turn++;
			if (turn % 2 == 1) {
				doGr.offer(doQ.poll());
			} else {
				suGr.offer(suQ.poll());
			}

			if (doQ.isEmpty() || suQ.isEmpty()) {
				break;
			}

			if (!doGr.isEmpty() && !suGr.isEmpty() && (doGr.peekLast() + suGr.peekLast() == 5)) {
				while (!doGr.isEmpty()) {
					suQ.offer(doGr.poll());
				}
				while (!suGr.isEmpty()) {
					suQ.offer(suGr.poll());
				}
			} else if (!doGr.isEmpty() && doGr.peekLast() == 5 || !suGr.isEmpty() && suGr.peekLast() == 5) {
				while (!suGr.isEmpty()) {
					doQ.offer(suGr.poll());
				}
				while (!doGr.isEmpty()) {
					doQ.offer(doGr.poll());
				}
			}

		}
		
		if (doQ.size() > suQ.size()) {
			winner = "do";
		} else if (doQ.size() < suQ.size()) {
			winner = "su";
		} else {
			winner = "dosu";
		}
		System.out.println(winner);

		br.close();
	}
}
