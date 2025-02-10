package org.example.yhs3237;

public class n2Array {
//       public static int[] solution(int n, long left, long right) {
//       int[][] arrTwo = new int[n][n]; //n행 n열의 2차원 배열
//
//        // 2차원 배열에 규칙에 맞게 숫자 저장
//        int num = 1;
//        for (int i = 0; i < n; i++) {
//            arrTwo[i][i] = num;
//            for (int j = i-1; j >= 0; j--) {
//                arrTwo[i][j] = num;
//                arrTwo[j][i] = num;
//            }
//            num++;
//        }
//
//        // 1차원 배열에 2차원 배열 넣기
//        int[] arrOne = new int[n*n];
//        int indx = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                arrOne[indx] = arrTwo[i][j];
//                indx++;
//            }
//        }
//
//        int[] answer = new int[(int) (right-left+1)];
//        int index = 0;
//        for (int i = (int) left; i <= right; i++) {
//            answer[index] = arrOne[i];
//            index++;
//        }
//
//        return answer;
//    }
//    ==> 메모리 초과

    public static int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];

        // 2차원 배열에서 각 좌표(row, col)는 1차원 배열의 인덱스(index)와 "index = (row * n) + col" 의 관계를 가짐
        // row 찾기 = index / n (index의 값이 몇번째 행에 속하는지)
        // col 찾기 = index % n (index의 값이 몇번째 열에 속하는지)
        for (int i = 0; i < answer.length; i++) {
            long index = left + i; // left부터 시작 & i = 0이므로 (index는 left+0, left+1, left+2, ... , left+(right-left+1)=right-1까지)
            int row = (int) (index / n);
            int col = (int) (index % n);


            answer[i] = Math.max(row, col) + 1; // 2차원 배열에서 보면 col = i인 열에는 i+1값이 들어감
            // row = i인 행에도 i+1값이 들어감
            // 따라서 row와 col 중 더 큰 값에 +1한 값이 2차원 배열의 좌표 위치에 들어가게 되는 것!
        }

        return answer;
    }

}
