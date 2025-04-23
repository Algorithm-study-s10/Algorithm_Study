package org.example.seonmin98.w10_탈출;

import java.io.*;
import java.util.*;

public class Main {
	static int height, width;
	static String[][] map;
	static boolean[][] vis;
	static int[] start;
	static int[] end;
	
	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};
	
	static class Node {
		int y, x, dist;
		
		public Node (int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		map = new String[height][width];
		vis = new boolean[height][width];
		start = new int[2]; //시작지점 (고슴도치의 위치)
		end = new int[2]; //도착지점 (비버의 굴)
		for (int y = 0; y < height; y++) {
			String input = br.readLine();
			for (int x = 0; x < width; x++) {
				map[y][x] = input.charAt(x) + "";
				if (map[y][x].equals("S")) {
					start[0] = y;
					start[1] = x;
				} else if (map[y][x].equals("D")) {
					end[0] = y;
					end[1] = x; 
				}
			}
		}
		
		List<Integer> time = new ArrayList<>(); //물 확장을 위한 시간계산용 리스트
		Queue<Node> queue = new LinkedList<>(); //최단거리 계산을 위한 큐
		queue.add(new Node(start[0], start[1], 0));
		vis[start[0]][start[1]] = true;
		
		int cnt = -1; //불가능 확인을 위한 초기화
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int cy = cur.y;
			int cx = cur.x;
			int cd = cur.dist;
			
			if (time.isEmpty() || time.size() < cd) { //물 확장 조건 (시작지점이거나 최단거리만큼 확장이 안된경우)
				waterflow(); //물 확장해주고
				time.add(1); //리스트에 시간 추가(값은 아무거나 상관 X)
			}
			
//			System.out.println("------------------- cy: " + cy + ", cx: " + cx);
//			printMap();
			
			if (time.size() != 1 && map[cy][cx].equals("*")) continue; //물이 찰 예정인 곳으로는 이동 불가
			
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if (ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
				if (vis[ny][nx] || map[ny][nx].equals("X") || map[ny][nx].equals("*")) continue;
				
				if (ny == end[0] && nx == end[1]) { //목적지 도착!!!
					cnt = cd + 1;
					break;
				}
				
				queue.add(new Node(ny, nx, cd + 1));
				vis[ny][nx] = true;
			}
			
			if (cnt != -1) break;
		}
		
		if (cnt == -1) System.out.println("KAKTUS");
		else System.out.println(cnt);
		
	}

//	static void printMap() {
//		for (int y = 0; y < height; y++) {
//			System.out.println(Arrays.toString(map[y]));
//		}
//	}

	static void waterflow() {
		
		List<int[]> waters = new ArrayList<>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x].equals("*")) waters.add(new int[] {y, x});
			}
		} //일단 현시점 기준으로 물위치를 파악
		
		for (int[] cur : waters) { //현시점 기준에서 물인 위치에서만 상하좌우 확장
			int cy = cur[0];
			int cx = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if (ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
				if (map[ny][nx].equals("X") || map[ny][nx].equals("D")) continue;
				
				map[ny][nx] = "*";
			}
		}
	}
}