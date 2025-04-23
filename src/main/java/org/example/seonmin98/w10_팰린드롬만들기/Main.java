package org.example.seonmin98.w10_팰린드롬만들기;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 String input = sc.next();
		 
		 int[] cnt = new int[26];
		 for (int i = 0; i < input.length(); i++) {
			  cnt[input.charAt(i) - 'A']++;
		 }
		 
		 Queue<Character> front = new LinkedList<>();
		 Stack<Character> back = new Stack<>();
		 
		 String mid = "";
		 for (int i = 0; i < 26; i++) {
			 while (cnt[i] >= 2) {
				 front.add((char)('A' + i));
				 back.add((char)('A' + i));
				 cnt[i] -= 2;
			 }
			 if (cnt[i] != 0) {
				 if (!mid.equals("")) {
					 System.out.println("I'm Sorry Hansoo");
					 return;					 
				 } else {
					 mid = (char)('A' + i) + "";
				 }
			 }
		 }
		 
		 StringBuilder sb = new StringBuilder();
		 while (!front.isEmpty()) {
			 sb.append(front.poll());
		 }
		 sb.append(mid);
		 while (!back.isEmpty()) {
			 sb.append(back.pop());
		 }
		 System.out.println(sb);
	}
}
