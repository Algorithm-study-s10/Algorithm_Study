package org.example.seonmin98.w11_해킹;

import java.util.*;
import java.io.*;

public class Main {
	static int N, D, C;
	
	static class Node implements Comparable<Node>{
		int to, cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	
	static List<Node>[] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //컴퓨터 개수
			D = Integer.parseInt(st.nextToken()); //의존성 개수
			C = Integer.parseInt(st.nextToken()); //해킹당한 컴퓨터의 번호
			
			graph = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to, cost));
			} //간선 입력받기
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean[] vis = new boolean[N + 1];
			int[] dist = new int[N + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			vis[C] = true;
			dist[C] = 0;
			for (Node n : graph[C]) {
				pq.add(n);
			}
			
			int cnt = 1;
			int ret = 0;
			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				if (vis[cur.to]) continue;
				
				cnt++;
				vis[cur.to] = true; 
				dist[cur.to]= cur.cost;
				ret = dist[cur.to];
				
				for (Node n : graph[cur.to]) {
					pq.add(new Node(n.to, (n.cost + dist[cur.to])));
				}
			}
			
			sb.append(cnt).append(" ").append(ret).append("\n");
		}
		
		System.out.println(sb);
	}
}
