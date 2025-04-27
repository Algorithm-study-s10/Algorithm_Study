package org.example.yhs3237.w11_hacking;

import java.io.*;
import java.util.*;

public class baekjoon10282 {
    static class Computer  implements Comparable<Computer>{
        int to, time;

        public Computer(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터의 번호

            List<Computer>[] computers = new ArrayList[n+1];
            for (int i = 0; i < n+1; i++) {
                computers[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()); // 컴퓨터 a
                int b = Integer.parseInt(st.nextToken()); // 컴퓨터 b
                int s = Integer.parseInt(st.nextToken()); // s초 후 감염

                // 컴퓨터 b가 감염되면 a도 s초 뒤에 감염 (b에 a가 연결, 가중치는 s)
                computers[b].add(new Computer(a, s));
            }

            // 다익스트라 부분
            boolean[] visited = new boolean[n+1];
            int[] dist = new int[n+1]; // 컴퓨터 c부터 시작해서 다른 컴퓨터가 감염되는데 걸리는 시간

            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[c] = 0; // 스스로의 가중치는 0
            PriorityQueue<Computer> pq = new PriorityQueue<>();
            pq.add(new Computer(c, 0));

            while (!pq.isEmpty()) {
                Computer now = pq.poll();
                int cur = now.to;

                if (visited[cur]) continue;
                visited[cur] = true;

                for (Computer next : computers[cur]) {
                    /*
                     *  현재 컴퓨터에서 연결된 다른 컴퓨터들 중
                     *  다음 컴퓨터까지의 가중치가(현재까지의 가중치 + 다음 컴퓨터의 가중치)보다 크다면
                     *  가중치를 최소로 해야 하므로 다음 컴퓨터까지의 가중치를 더 작은 값으로 갱신
                     */
                    if (dist[next.to] > dist[cur] + next.time) {
                        dist[next.to] = dist[cur] + next.time;
                        pq.offer(new Computer(next.to, dist[next.to]));
                    }
                }
            }

            int count = 0; // 감염된 컴퓨터의 수
            int last = -1; // 마지막 컴퓨터가 감염되기까지 걸리는 시간
            for (int i = 0; i < n+1; i++) {
                if (dist[i] != Integer.MAX_VALUE){
                    count++;
                    last = Math.max(dist[i], last);
                }
            }
            sb.append(count).append(" ").append(last).append("\n");
        }
        System.out.println(sb);
    }
}
