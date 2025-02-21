package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class boj_1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t= 0; t < T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			Queue<int[]> q = new LinkedList<>();
			
			for(int i=0;i<N;i++) {
				int imp = sc.nextInt();
				q.add(new int[] {i, imp});
			}
			
			int count = 0;
			
			while(true) {
				int[] p = q.poll();
				if(q.isEmpty()) {
					count++;
					break;
				}
				
				boolean isImp = false;
				for(int[] qq : q) {
					if(p[1] < qq[1])
						isImp = true;
				}
				
				if(isImp)
					q.add(p);
				else {
					count++;
					if(p[0] == M)
						break;
				}
			}
			System.out.println(count);
		}
	}
}
