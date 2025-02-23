package org.example.seonmin98.w02_프린터큐;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			//value 값을 담을 우선순위 큐
			PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
			
			//각 value값을 가진 인덱스를 저장할 이중리스트(?)
			List<List<Integer>> list = new ArrayList<>();
			for (int i = 0; i <= 9; i++) {
				list.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int value = Integer.parseInt(st.nextToken());
				queue.add(value);
				list.get(value).add(i);
			}
			
			int i = 1; //출력순서
			int idx = 0; //바로 직전에 출력된 인덱스의 값 저장
			while (i <= n) {
				int cur = queue.peek(); //출력해야 할 value값 확인
				int cnt = 0; //출력이 뒤로 밀린 갯수
				boolean flag = false; //타깃에 도달했는지 체크
				for (int nxt : list.get(cur)) {
					if (nxt < idx) {
						cnt++; //바로 직전에 출력된 인덱스보다 작으면 출력이 뒤로 밀린 것!
						continue;
					}
					if (nxt == target) { // 타깃의 인덱스와 동일하다면
						System.out.println(i); //출력순서 출력!
						flag = true;
						break;
					} else { //해당사항 없으면 큐에서 꺼내기
						idx = nxt; //출력 인덱스 갱신
						queue.poll();
						i++;
					}
				}
				for (int j = 0; j < cnt; j++) { //출력이 뒤로 밀린 갯수만큼 나머지 꺼내기
					if (list.get(cur).get(j) == target) {
						System.out.println(i);
						flag = true;
						break;
					}
					idx = list.get(cur).get(j);
					queue.poll();
					i++;
				}
				if (flag) break;
			}
		}
	}
}

