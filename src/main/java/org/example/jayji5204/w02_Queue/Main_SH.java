import java.io.File;

import java.util.ArrayDeque;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Deque;

import java.util.List;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

//		Scanner sc = new Scanner(System.in);

		Scanner sc = new Scanner(new File("input.txt"));

		int T = sc.nextInt();

		sc.nextLine();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();

			int M = sc.nextInt();

			int[] printer = new int[N]; // 문서 위치와 중요도 저장을 위해 배열 사용

			Deque<int[]> queue = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {

				printer[i] = sc.nextInt();

				queue.offer(new int[] { i, printer[i] }); // queue에 문서위치 중요도 저장

			}

			Arrays.sort(printer);

			int count = 0; // 출력 문서 갯수

			int num = 1;

			while (!queue.isEmpty()) {

				int[] idx = queue.poll();

				int firstIdx = idx[0]; // 문서 위치

				int important = idx[1]; // 문서 중요도

				if (important == printer[N - num]) { // 중요도랑 printer맨 끝이랑 같으면 출력

					count++;

					num++;

					if (firstIdx == M) { // 문서 위치랑 M이랑 같으면 종료

						System.out.println(count);

						break;

					}

				} else {

					queue.offer(idx);

				}

			}

		}

	}

}