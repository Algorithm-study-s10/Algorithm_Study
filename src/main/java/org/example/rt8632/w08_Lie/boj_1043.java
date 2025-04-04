package org.example.rt8632.w08_Lie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1043 {
	static int[] p,truth;
	static List<ArrayList<Integer>> graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//사람 수, 파티의 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//그래프 생성
		graph = new ArrayList<>();
		for(int i=0;i<=M;i++) {
			graph.add(new ArrayList<>());
		}
		
		p = new int[N+1];
		for(int i=1;i<=N;i++) {
			p[i] = i;
		}
		
		//진실을 아는 사람의 수와 번호
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		truth = new int[a];
		for(int i=0;i<a;i++) {
			truth[i] = Integer.parseInt(st.nextToken());
		}
		//파티마다 오는 사람의 수와 번호
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0;j<num;j++) {
				int input = Integer.parseInt(st.nextToken());
				graph.get(i).add(input);
				if(j > 0 || j < num)
					union(graph.get(i).get(0), input);
			}
		}
		
		int ans = 0;
		for(int i=0;i<M;i++) {
			boolean lie = false;
			//모임별로 진실을 아는 사람이 있는지 탐색
			for(int t : graph.get(i)) {
				for(int j=0;j<a;j++) {
					int x = findSet(t);
					int y = findSet(truth[j]);
					if(x == y)
						lie = true;
				}
			}
			
			if(!lie)
				ans++;
		}
		System.out.println(ans);
	}
	public static int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}
	
	public static void union(int x, int y) {
		int a = findSet(x);
		int b = findSet(y);
		p[b] = a;
	}
}
