package org.example.seonmin98.w01_배열돌리기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int r = Integer.parseInt(input[2]);

        int[][] arr = new int[n][m];
        for (int y = 0; y < n; y++) {
            String[] line = br.readLine().split(" ");
            for (int x = 0; x < m; x++) {
                arr[y][x] = Integer.parseInt(line[x]);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < Math.min(n, m) / 2; j++) {
                int tmp = arr[j][j];

                // left
                for (int k = 0; k < m - (2 * j) - 1; k++) {
                    arr[j][j + k] = arr[j][j + k + 1];
                }

                // up
                for (int k = 0; k < n - (2 * j) - 1; k++) {
                    arr[j + k][m - 1 - j] = arr[j + k + 1][m - 1 - j];
                }

                // right
                for (int k = 0; k < m - (2 * j) - 1; k++) {
                    arr[n - 1 - j][m - 1 - j - k] = arr[n - 1 - j][m - 1 - j - k - 1];
                }

                // down
                for (int k = 0; k < n - (2 * j) - 1; k++) {
                    arr[n - 1 - j - k][j] = arr[n - 1 - j - k - 1][j];
                }

                arr[j + 1][j] = tmp;
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                System.out.print(arr[y][x] + " ");
                sb.append(arr[y][x]).append(" ");
            }
            sb.append('\n');
        }
    }
}
