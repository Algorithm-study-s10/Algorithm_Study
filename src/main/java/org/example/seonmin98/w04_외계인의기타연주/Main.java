package org.example.seonmin98.w04_외계인의기타연주;

import java.io.*;
import java.util.*;

public class Main {
	static Stack<Integer>[] list = new Stack[7]; //6개의 줄
	static int cnt; //필요한 손가락 움직임
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //멜로디 개수
		int p = Integer.parseInt(st.nextToken()); //프렛 개수
		
		for (int i = 0; i < 7; i++) { //줄별 스택 생성
			list[i] = new Stack<>();
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()); //눌러야 하는 줄 
			int fret = Integer.parseInt(st.nextToken()); //눌러야 하는 프렛 
			if (list[idx].isEmpty()) { //해당 줄에 누르고 있는 프렛이 없으면 추가
				cnt++;
				list[idx].add(fret);
			} else {
				if (list[idx].peek() == fret) continue; //이미 해당 줄의 해당 프렛을 누르고 있는 경우
				else if (list[idx].peek() < fret) { //눌러야 하는 프렛보다 낮은 프렛을 누르고 있는 경우
					cnt++;
					list[idx].add(fret);
				} else { // 눌러야 하는 프렛보다 높은 프렛을 누르고 있는 경우
					while (!list[idx].isEmpty() && list[idx].peek() > fret) {
						list[idx].pop();
						cnt++;
					}
					if (list[idx].isEmpty() || (!list[idx].isEmpty() && list[idx].peek() != fret)) {
						cnt++;
						list[idx].add(fret);
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}
