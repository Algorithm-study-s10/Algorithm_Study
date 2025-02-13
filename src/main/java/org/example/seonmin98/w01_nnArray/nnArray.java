package org.example.seonmin98.w01_nnArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class nnArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, 2, 5)));
        System.out.println(Arrays.toString(solution(4, 7, 14)));
    }

    static int[] solution(int n, long left, long right) {

        List<Integer> answer = new ArrayList<>();
        int size = (int) (right - left) + 1;
        for (int i = 0; i < size; i++) {
            int value = Math.max((int) ((left + i) / n), (int) ((left + i) % n)) + 1;
            answer.add(value);
        }

        int[] ret = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ret[i] = answer.get(i);
        }

        return ret;
    }
}
