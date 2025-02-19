package org.example.iroo2001.w02_탑;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		boolean flag;
		int n, current;
		int[] nums;
		Stack<Integer> stack = new Stack<>();
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		nums = new int[n];

		for (int i = 0; i < n; i++) {
			flag = true;
			current = sc.nextInt();
			nums[i] = current;

			while (stack.size() > 0) {
				if (nums[stack.peek()] > current) {
					System.out.print((stack.peek() + 1) + " ");
					flag = false;
					break;
				} else {
					stack.pop();
				}
			}
			
			if (flag) {
				System.out.print(0 + " ");
			}
			stack.push(i);
		}

		sc.close();
	}
}