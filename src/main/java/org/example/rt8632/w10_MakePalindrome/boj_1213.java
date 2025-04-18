package org.example.rt8632.w10_MakePalindrome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1213 {
	static int L;
	static char[] pick, input;
	static boolean[] visited;
	static StringBuilder output;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		output = new StringBuilder();
		String s = st.nextToken();
		L = s.length();
		pick = new char[L];
		input = new char[L];
		visited = new boolean[L];
		for(int i=0;i<L;i++) {
			input[i] = s.charAt(i);
		}
		
		recur(0);
		if(output.length() > 0)
			System.out.println(output.substring(0, L));
		else {
			System.out.println("I'm Sorry Hansoo");
		}
	}
	
	static void recur(int idx) {
		StringBuilder sb = new StringBuilder();
		if(idx == L) {
			for(int i=0;i<L;i++) {
				sb.append(pick[i]);
			}
			if(sb.toString().equals(sb.reverse().toString())) {
				output.append(sb);
			}
		}
		
		for(int i=0;i<L;i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			pick[idx] = input[i];
			recur(idx+1);
			visited[i] = false;
		}
	}
}
