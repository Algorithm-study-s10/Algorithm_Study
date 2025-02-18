package org.example.rt8632.w02_Top;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
    int num;
    int height;

    Top(int num, int height){
        this.num = num;
        this.height = height;
    }
}
public class boj_2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Top> s = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++) {
            int height = Integer.parseInt(st.nextToken());

            if(s.isEmpty()) {
                sb.append("0 ");
                s.push(new Top(i, height));
            } else {
                while(true) {
                    if(s.isEmpty()) {
                        sb.append("0 ");
                        s.push(new Top(i, height));
                        break;
                    }

                    Top top = s.peek();

                    if(top.height > height) {
                        sb.append(top.num + " ");
                        s.push(new Top(i, height));
                        break;
                    } else {
                        s.pop();
                    }
                }
            }

        }
        System.out.println(sb.toString());
    }
}

