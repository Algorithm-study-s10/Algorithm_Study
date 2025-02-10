package org.example.iroo2001.w01_n2ArraySplit;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int n;
		long left, right;
		ArrayDeque<Object[]> input = new ArrayDeque<>();

		input.offer(new Object[] { 3, 2L, 5L });
		input.offer(new Object[] { 4, 7L, 14L });

		for (Object[] inp : input) {
			Solution s = new Solution();
			
			n = (int) inp[0];
			left = (long) inp[1];
			right = (long) inp[2];
			
			System.out.println(Arrays.toString(s.solution(n, left, right)));
		}
	}

}
