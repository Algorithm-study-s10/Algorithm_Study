package org.example.rt8632.w11_Hacking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
public class boj_10282 {
    static ArrayList<Node>[] graph;
    static int ans, count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int i=0;i<T;i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n+1];

            for(int j=0;j<=n;j++) {
                graph[j] = new ArrayList<>();
            }
            ans = 0;
            count = 0;
            for(int j=0;j<d;j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            Dijkstra(n, c);
            System.out.println(count + " " + ans);
        }
    }

    static void Dijkstra(int n, int start) {
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().idx;

            if(check[nowVertex])
                continue;
            check[nowVertex] = true;
            count++;
            for(Node next : graph[nowVertex]) {
                if(dist[next.idx] > dist[nowVertex] + next.cost) {
                    dist[next.idx] = dist[nowVertex] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        for(int i=0;i<n+1;i++) {
            if(check[i])
                ans = Math.max(ans, dist[i]);
        }
    }
}


