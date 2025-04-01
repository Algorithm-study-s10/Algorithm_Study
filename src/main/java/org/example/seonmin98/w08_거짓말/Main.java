package org.example.seonmin98.w08_거짓말;

import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] graph;
	static int[] parties;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 사람의 수 (1부터 시작)
		m = Integer.parseInt(st.nextToken()); // 파티의 수
		
		graph = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = i;
		} //그래프 초기화
		
		st = new StringTokenizer(br.readLine());
		int trues = Integer.parseInt(st.nextToken());
		int parent = trues == 0 ? -1 : Integer.parseInt(st.nextToken());
		for (int i = 1; i < trues; i++) {
			int child = Integer.parseInt(st.nextToken());
			graph[child] = parent;
		} //진실을 아는 사람들 하나의 집합으로 연결
		
		parties = new int[m]; //파티의 참석자 대표를 저장할 배열
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int owner = Integer.parseInt(st.nextToken()); //파티의 대표자
			for (int p = 1; p < size; p++) {
				int participant = Integer.parseInt(st.nextToken()); //파티의 참석자
				union(findSet(owner), findSet(participant)); //파티의 대표자와 참석자 그룹으로 연결
			}
			parties[i] = owner; //파티의 대표자 배열에 저장
		}
		
		int cnt = 0;
		if (parent != -1) parent = findSet(parent);
		for (int i = 0; i < m; i++) {
			if (findSet(parties[i]) != parent) cnt++;
		}

		System.out.println(cnt);
	}

	static void union(int a, int b) {
		graph[b] = a;
	}

	static int findSet(int v) {
		if (graph[v] != v) graph[v] = findSet(graph[v]);
		return graph[v];
	}
}