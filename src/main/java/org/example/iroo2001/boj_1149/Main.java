package boj_1149;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
dp

i번째 집까지 저장
i-1번째 집을 토대로 다음 계산
-> j를 0, 1, 2(집의 색)이라 할 때
-> 이전 집의 j는 현재 (j+1)%3, (j+2)%3중 값이 작은 것을 사용

dp[0] : 26, 40, 83
26, 40, 83

dp[1] : 49, 60, 57
89, 86, 83
 */

public class Main {
	
	static int[] findNext(int[] before, int[] current) {
		int[] result = new int[3];
		for (int i=0; i<3; i++) {
			result[i] = current[i] + Math.min(before[(i+1)%3], before[(i+2)%3]);
		}
		return result;
	}

	public static void main(String[] args) {
		int n;
		int[] result = new int[3];
		List<int[]> nums = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		for (int i=0; i<n; i++) {
			// 입력 받기
			nums.add(new int[3]);
			for (int j=0; j<3; j++) {
				nums.get(i)[j] = sc.nextInt();
			}
			
			// 바로 계산하기
			result = findNext(result, nums.get(i));
		}
		Arrays.sort(result);
		System.out.println(result[0]);
		sc.close();

	}

}
