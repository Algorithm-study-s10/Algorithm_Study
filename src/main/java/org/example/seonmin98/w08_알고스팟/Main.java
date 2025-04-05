package org.example.seonmin98.w08_알고스팟;

import java.util.*;
import java.io.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int width, height;
	static int[][] map;
	static boolean[][] vis;
	static int[][] dist;
	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};
	
	static class Node implements Comparable<Node>{
		int y, x, cost;

		public Node(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		map = new int[height][width];
		for (int y = 0; y < height; y++) {
			String input = br.readLine();
			for (int x = 0; x < width; x++) {
				map[y][x] = Integer.parseInt(input.charAt(x)+"");
			}
		} //지도 입력받기
		
		dist = new int[height][width];
		for (int y = 0; y < height; y++) {
			Arrays.fill(dist[y], INF);
		} //각 위치별 최단거리 저장할 2차원 배열 초기화
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		vis = new boolean[height][width];
		dist[0][0] = 0;
		vis[0][0] = true;
		pq.add(new Node(0, 0, 0)); //시작은 좌측상단
		
		while (!pq.isEmpty()) {
			int cy = pq.peek().y;
			int cx = pq.peek().x;
			int cost = pq.peek().cost;
			pq.poll();
			dist[cy][cx] = cost;
			
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || ny >= height || nx < 0 || nx >= width || vis[ny][nx]) continue;
				
				//어차피 우선순위큐니까 냅다 다 집어넣고 방문표시하기...^^
				vis[ny][nx] = true;
				pq.add(new Node(ny, nx, map[ny][nx] == 1 ? cost + 1 : cost));
			}
		}
		
		System.out.println(dist[height - 1][width - 1]);
	}
}