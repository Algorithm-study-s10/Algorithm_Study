package swea_22979;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception{
		int t, op;
		long k, operation;
		String str, bef, aft;
		Scanner sc = new Scanner(System.in);
		
		t = sc.nextInt();
		for (int tc=1; tc<=t; tc++) {
			sc.nextLine();		// 한줄 버림
			str = sc.nextLine();
			k = sc.nextLong();
			operation = 0;
			for (int i=0; i<k; i++) {
				operation += sc.nextLong();
			}
			
			op = (int) (operation % str.length());
			
			if (operation==0) {
				System.out.println(str);
				continue;
			}
			
			if (operation>0) {
				aft = str.substring(0, op);
				bef = str.substring(op);
			}
			else {
				aft = str.substring(0, str.length()+op);
				bef = str.substring(str.length()+op);
			}
			System.out.println(bef+aft);
		}
		sc.close();
	}

}
