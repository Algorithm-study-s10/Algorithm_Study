package org.example.yhs3237.w03_addOperator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // 숫자의 개수
    static int max = Integer.MIN_VALUE; // 연산 결과의 최대값
    static int min = Integer.MAX_VALUE; // 연산 결과의 최소값
    static int[] numbers; // 입력받은 숫자 저장
    static int[] operations; // 연산자 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 수의 개수

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        //System.out.println(Arrays.toString(numbers));

        operations = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            // [0] : +, [1] : -, [2] : *, [3] : /
            operations[i] = Integer.parseInt(st.nextToken());
        }

        recur(numbers[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void recur(int result, int index) {
        // 기저조건
        if (index == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i]--;

                int newResult = calculate(result, numbers[index], i); // 연산
                recur(newResult, index + 1); // 재귀 호출

                operations[i]++;  // 백트래킹 (연산자 복원)
            }
        }
    }

    // 연산
    public static int calculate(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;  // 덧셈
            case 1: return a - b;  // 뺄셈
            case 2: return a * b;  // 곱셈
            case 3: // 나눗셈
                if (a < 0) {
                    return (a * (-1) / b) * (-1);
                }
                return a / b;
            default: return 0;
        }
    }
}