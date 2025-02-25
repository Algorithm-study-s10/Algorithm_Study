package org.example.yhs3237.w03_cardDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon20923 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 카드의 개수
        int M = Integer.parseInt(st.nextToken()); // 게임 진행 횟수

        Deque<Integer> card_do = new LinkedList<>(); // 도도의 카드 덱
        Deque<Integer> card_su = new LinkedList<>(); // 수연이의 카드 덱

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            card_do.add(Integer.parseInt(st.nextToken()));
            card_su.add(Integer.parseInt(st.nextToken()));
        }

        Deque<Integer> ground_do = new LinkedList<>(); // 도도의 그라운드
        Deque<Integer> ground_su = new LinkedList<>(); // 수연이의 그라운드

        // 게임 횟수만큼 반복
        for (int i = 0; i < M; i++) {
            if (card_do.size() == 0 || card_su.size() == 0) {
                break;
            }

            if (i % 2 == 0) {
                // 도도가 카드를 한 장 뒤집어 그라운드에 놓음
                ground_do.addLast(card_do.pollLast());

                if (card_do.size() == 0) {
                    break;
                }

                // 5인지 확인
                if (ground_do.peekLast() == 5) {
                    while (!ground_su.isEmpty()) {
                        card_do.addFirst(ground_su.pollFirst());
                    }

                    while (!ground_do.isEmpty()) {
                        card_do.addFirst(ground_do.pollFirst());
                    }
                }

                // 합이 5인지 확인
                if (!ground_do.isEmpty() && !ground_su.isEmpty() && ground_do.peekLast() + ground_su.peekLast() == 5) {
                    while (!ground_do.isEmpty()) {
                        card_su.addFirst(ground_do.pollFirst());
                    }

                    while (!ground_su.isEmpty()) {
                        card_su.addFirst(ground_su.pollFirst());
                    }
                }
            } else {
                // 수연이가 카드를 한 장 뒤집어 그라운드에 놓음
                ground_su.addLast(card_su.pollLast());

                if (card_su.size() == 0) {
                    break;
                }

                // 5인지 확인
                if (ground_su.peekLast() == 5) {
                    while (!ground_su.isEmpty()) {
                        card_do.addFirst(ground_su.pollFirst());
                    }

                    while (!ground_do.isEmpty()) {
                        card_do.addFirst(ground_do.pollFirst());
                    }
                }

                // 합이 5인지 확인
                if (!ground_do.isEmpty() && !ground_su.isEmpty() && ground_do.peekLast() + ground_su.peekLast() == 5) {
                    while (!ground_do.isEmpty()) {
                        card_su.addFirst(ground_do.pollFirst());
                    }

                    while (!ground_su.isEmpty()) {
                        card_su.addFirst(ground_su.pollFirst());
                    }
                }
            }
        }

        if (card_do.size() > card_su.size()) {
            System.out.println("do");
        } else if (card_do.size() == card_su.size()){
            System.out.println("dosu");
        } else {
            System.out.println("su");
        }
    }
}
