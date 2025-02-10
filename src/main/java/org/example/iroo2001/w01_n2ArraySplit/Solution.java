package org.example.iroo2001.w01_n2ArraySplit;

class Solution {
	public int[] solution(int n, long left, long right) {
		int gap, x, y;
		gap = (int) (right - left);
		int[] answer = new int[gap + 1];

		for (int i = 0; i <= gap; i++) {
			x = (int) ((left + i) / n);
			y = (int) ((left + i) % n);
			answer[i] = Math.max(x, y) + 1;
		}
		return answer;
	}
}