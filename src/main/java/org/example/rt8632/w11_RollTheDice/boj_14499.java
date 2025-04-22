package org.example.rt8632.w11_RollTheDice;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14499 {
    //동, 서, 북, 남
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        // 0 : 위, 1 : 아래, 2 : 앞, 3 : 뒤, 4 : 좌 , 5 : 우
        dice = new int[6];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++) {
            int dir = Integer.parseInt(st.nextToken());
            int nx = x + dx[dir-1];
            int ny = y + dy[dir-1];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m)
                continue;
            roll(dir);

            if(map[nx][ny] == 0)
                map[nx][ny] = dice[1];
            else
                dice[1] = map[nx][ny];
            System.out.println(dice[0]);
        }
    }

    static void roll(int dir) {
        int temp = 0;
        switch (dir) {
            case 1: //동
                //우 -> 아래, 좌 -> 위, 위 -> 우, 아래 -> 좌
                temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                break;
            case 2: //서
                //우 -> 위, 좌 -> 아래, 위 -> 좌, 아래 -> 우
                temp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
                break;

            case 3: //북
                //위 -> 뒤, 뒤 -> 아래, 아래 -> 앞, 앞 -> 위
                temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[3];
                dice[3] = temp;
                break;
            case 4: //남
                //위 -> 앞, 앞 -> 아래, 아래 -> 뒤, 뒤 -> 위
                temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                break;
        }

    }
}