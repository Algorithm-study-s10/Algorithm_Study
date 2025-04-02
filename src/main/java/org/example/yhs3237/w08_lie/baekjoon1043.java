package org.example.yhs3237.w08_lie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon1043 {
    static int N, M;
    static int[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사람의 수
        M = Integer.parseInt(st.nextToken()); // 파티의 수

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
        int[] knowP = new int[K]; // 진실을 아는 사람들
        for (int i = 0; i < K; i++) {
            knowP[i] = Integer.parseInt(st.nextToken());
        }

        people = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            people[i] = i; // 사람 -> 자기자신을 대표로 하도록 초기화
        }

        List<Integer>[] parties = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken()); // 파티 하나에 몇명이 있는지
            for (int j = 0; j < count; j++) {
                parties[i].add(Integer.parseInt(st.nextToken())); // 파티에 참석하는 사람들 저장
            }
        }

        // 파티 정보를 기반으로 같은 파티에 있는 사람들 연결
        for (int i = 0; i < M; i++) {
            if (parties[i].size() > 1) {
                int first = parties[i].get(0);
                for (int j = 1; j < parties[i].size(); j++) {
                    union(first, parties[i].get(j));
                }
            }
        }


//		System.out.println(Arrays.toString(knowP));
//		System.out.println(Arrays.toString(people));

        int count = 0; // 과장을 해도 되는 파티의 수

        if (knowP.length == 0) { // 진실을 아는 사람이 없다면 모든 파티에서 과장해도 되므로 M 출력
            System.out.println(M);
        } else {
            for (int i = 0; i < M; i++) {
                boolean connect = false; // 해당 파티에 진실을 아는 사람과 같은 집합인 사람이 있는지 (=파티의 상태)
                for (int person : parties[i]) {
                    for (int k = 0; k < K; k++) {
                        // 파티에 참석한 사람의 대표자가 진실을 아는 사람들 중 한 사람이라면 true로 상태 변경
                        // 그 사람이 참석한 파티에서는 무조건 진실을 말해야 하므로 해당 파티를 더 탐색할 필요 X (break)
                        if (find(person) == find(knowP[k])) {
                            connect = true;
                            break;
                        }
                    }
                    if (connect) break;
                }
                // 파티의 상태가 false라면 (= 파티에 진실을 아는 사람이 없고, 진실을 아는 사람과 같은 집합인 사람도 없다면)
                // 과장할 수 있으므로 count++
                if (!connect) count++;
            }
            System.out.println(count);
        }
    }

    static int find(int x) {
        if (people[x] == x) return x;
        return people[x] = find(people[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            people[rootB] = rootA;
        }
    }
}
