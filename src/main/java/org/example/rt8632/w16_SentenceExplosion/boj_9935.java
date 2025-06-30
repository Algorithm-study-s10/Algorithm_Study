package org.example.rt8632.w16_SentenceExplosion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String target = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            stack.push(s.charAt(i));
            if(stack.size() >= target.length()) {
                boolean flag = true;
                for(int j=0;j<target.length(); j++) {
                    if(target.charAt(j) != stack.get(stack.size()-target.length()+j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j=0;j<target.length(); j++)
                        stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character ch : stack) {
            sb.append(ch);
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        }
        else {
            System.out.println(sb.toString());
        }

    }
}

