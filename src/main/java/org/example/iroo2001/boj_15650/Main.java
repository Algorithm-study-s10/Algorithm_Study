package boj_15650;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Deque<List<Integer>> getArrays(List<Integer> numPool, int maxLen) {
		List<Integer> currentList, tempList;
		Deque<List<Integer>> queue = new ArrayDeque<>();
		for (int i = 0; i < numPool.size(); i++) {
			tempList = new ArrayList<>();
			tempList.add(numPool.get(i));
			queue.offer(tempList);
		}
		int breakCnt = 0;
		while (breakCnt < queue.size()) {
			currentList = queue.poll();
			if (currentList.size() == maxLen) {
				breakCnt++;
				queue.offer(currentList);
				continue;
			}
			for (int i = 0; i < numPool.size(); i++) {
				if (currentList.get(currentList.size()-1)>=numPool.get(i)) {
					continue;
				}
				tempList = new ArrayList<>();
				for (int value : currentList) {
					tempList.add(value);
				}
				tempList.add(numPool.get(i));
				queue.offer(tempList);
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		int n, m;
		Deque<List<Integer>> result;
		List<Integer> numPool = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			numPool.add(i);
		}
		Collections.sort(numPool);
		result = getArrays(numPool, m);
		while (result.size() > 0) {
			for (int num : result.poll()) {
				System.out.print(num + " ");
			}
			System.out.println("");
		}
		sc.close();
	}

}
