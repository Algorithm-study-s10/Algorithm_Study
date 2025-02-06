package boj_11053;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int getIdxL(List<Integer> asc, int value) throws Exception {
		int i;
		if (asc.size() == 0) {
			return 0;
		} else if (asc.size() == 1) {
			return (asc.get(0) < value ? 1 : 0);
		} else if (asc.get(0) > value) {
			return 0;
		}
		for (i = 1; i < asc.size(); i++) {
			if ((value > asc.get(i - 1)) && (value <= asc.get(i))) {
				return i;
			} else if (value == asc.get(i - 1)) {
				throw new Exception();
			}
		}
		return asc.size();
	}

	public static void main(String[] args) {
		int n, idxL;
		int[] nums;
		Scanner sc = new Scanner(System.in);
		List<Integer> result = new ArrayList<>();
		n = sc.nextInt();
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}

		// 길이 구하기
		for (int num : nums) {
			try {
				idxL = getIdxL(result, num);
			} catch (Exception e) {
				continue;
			}
//			System.out.println(num + " " + idxL + " " + result.toString());
			if (idxL == result.size()) {
				result.add(num);
			} else {
				result.set(idxL, num);
			}
		}
		System.out.println(result.size());
		System.out.println(result.toString().replace("[",",").replace("]",",").replaceAll(",", "").strip());
		sc.close();
	}

}
