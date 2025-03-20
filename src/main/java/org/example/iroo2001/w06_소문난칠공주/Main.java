package org.example.iroo2001.w06_소문난칠공주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int CNT; // 경우의 수를 저장
	static int[] SELECTED; // 현재 조합의 좌표 정보를 저장
	static boolean[] MAP; // MAP : 이다솜파는 false, 임도연파는 true

	public static void main(String[] args) throws IOException {
		input();
		SELECTED = new int[8];
		SELECTED[0] = -1;
		combination(1);
		System.out.println(CNT);
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;

		CNT = 0;
		MAP = new boolean[25];

		for (int r = 0; r < 5; r++) {
			str = new StringTokenizer(br.readLine()).nextToken();
			for (int c = 0; c < 5; c++) {
				// 2차원 5*5인 map 대신, 1차원 25짜리 map으로 사용
				MAP[r * 5 + c] = (str.charAt(c) == 'S' ? false : true);
			}
		}
		br.close();
	}

	static void combination(int dept) {
		if (dept == 8) {
			check();
			return;
		}

		for (int i = SELECTED[dept - 1] + 1; i < 25; i++) {
			SELECTED[dept] = i;
			combination(dept + 1);
		}
	}

	static void check() {
		int yCNT = 0;
		if (!checkConnected()) {
			return;
		}

		for (int i = 1; i < 8; i++) {
			// 임도연파라면, 카운트 증가 및 종료조건 검사
			if (MAP[SELECTED[i]]) {
				yCNT++;
				if (yCNT > 3) {
					return;
				}
			}
		}
//		System.out.println(Arrays.toString(SELECTED));
		CNT++;
	}

	static boolean checkConnected() {
		// 하나의 좌표로부터, 모든 지점까지의 도달이 가능한지 검사
		boolean[] visited = new boolean[8];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1); // 첫 번째 좌표를 입력
		visited[1] = true; // 첫 번째 좌표를 방문하므로 체크

		int current;
		boolean near;
		while (!q.isEmpty()) {
			current = q.poll();

			for (int i = 1; i < 8; i++) {
				if (visited[i]) {
					continue;
				}
				near = false;
				// 방문하지 않은 모든 좌표에 대해, 상하좌우 탐색하여 다른 선택된 좌표와 인접한지 검사
				// 1. 위쪽 탐색
				if (SELECTED[current] - 5 == SELECTED[i]) {
					near = true;
				}
				// 2. 왼쪽 탐색 (5로 나눈 나머지가 0 초과인 경우에만 왼쪽이 존재)
				else if (SELECTED[current] % 5 > 0 && SELECTED[current] - 1 == SELECTED[i]) {
					near = true;
				}
				// 3. 아래쪽 탐색
				else if (SELECTED[current] + 5 == SELECTED[i]) {
					near = true;
				}
				// 4. 오른쪽 탐색 (5로 나눈 나머지가 4 미만인 경우에만 오른쪽이 존재)
				else if (SELECTED[current] % 5 < 4 && SELECTED[current] + 1 == SELECTED[i]) {
					near = true;
				}

				// i번째 좌표가 현재 좌표 근처라면, q에 추가 및 방문 처리
				if (near) {
					q.offer(i);
					visited[i] = true;
				}
			}
		} // while문 종료 (q에 있는 모든 값 탐색 완료)

		// 전부 탐색했지만, visited false인 값이 존재한다면, false return
		for (int i = 1; i < 8; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;

	}
}