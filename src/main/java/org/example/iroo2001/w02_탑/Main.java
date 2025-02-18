package org.example.iroo2001.w02_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		boolean flag;
		int n, current;
		int[] nums;
		Stack<Integer> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			flag = true;
			current = Integer.parseInt(st.nextToken());
			nums[i] = current;
			while (stack.size()>0) {
				if (nums[stack.peek()]>current) {
					sb.append(stack.peek()+1).append(" ");
					flag = false;
					break;
				} else {
					stack.pop();
				}
			}
			if (flag) {
				sb.append(0).append(" ");
			}
			stack.push(i);
		}
		System.out.println(sb);
		br.close();
	}
}