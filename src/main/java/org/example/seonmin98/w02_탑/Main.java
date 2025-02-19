package org.example.seonmin98.w02_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Stack<int[]> stack = new Stack<>(); //[0] num [1] idx
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			int hit = 0;
			while (!stack.isEmpty()) {
//				System.out.println("i: " + i + ", num: " + num);
				if (num < stack.peek()[0]) {
					hit = stack.peek()[1];
					break ;
				} else {
					stack.pop();
				}
			}
			stack.add(new int[] {num, i});
			sb.append(hit).append(" ");
		}
		
		System.out.println(sb);
	}
}

//-----------메모리 초과-------------
//public class Main {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		
//		Stack<Integer> stack = new Stack<>();
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for (int i = 0; i < n; i++) {
//			stack.add(Integer.parseInt(st.nextToken()));
//		}
//		
//		Stack<Integer> ret = new Stack<>();
//		for (int i = 0; i < n; i++) {
//			int cur = stack.pop();
//			int hit = 0;
//			Stack<Integer> tmp = new Stack<>();
//			while (!stack.isEmpty()) {
//				if (stack.peek() > cur) {
//					hit = stack.size();
//					break ;
//				} else {
//					tmp.add(stack.pop());
//				}
//			}
//			while (!tmp.isEmpty()) {
//				stack.add(tmp.pop());
//			}
//			ret.add(hit);
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		while (!ret.isEmpty()) {
//			sb.append(ret.pop() + " ");
//		}
//		System.out.println(sb);
//	}
//}


//-------------------시간 초과 1------------------
//public class Main {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		
//		Stack<Integer> stack = new Stack<>();
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for (int i = 0; i < n; i++) {
//			stack.add(Integer.parseInt(st.nextToken()));
//		}
//		
//		Stack<Integer> ret = new Stack<>();
//		for (int i = 0; i < n; i++) {
//			int cur = stack.pop();
//			int hit = 0;
//			for (int k = n - 2 - i; k >= 0; k--) {
//				if (stack.get(k) > cur) {
//					hit = k + 1;
//					break ;
//				}
//			}
//			ret.add(hit);
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		while (!ret.isEmpty()) {
//			sb.append(ret.pop() + " ");
//		}
//		System.out.println(sb);
//	}
//}

//----------------시간 초과 2-------------------
//public class Main {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		
//		Stack<Integer> stack = new Stack<>();
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < n; i++) {
//			stack.add(Integer.parseInt(st.nextToken()));
//			int hit = 0;
//			for (int j = i; j >= 0; j--) {
//				if (stack.get(j) > stack.peek()) {
//					hit = j + 1;
//					break ;
//				}
//			}
//			sb.append(hit).append(" ");
//		}
//		
//		System.out.println(sb);
//	}
//}