import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class baekjoon17136 {
	static int[][] board = new int[10][10]; // 10 * 10인 종이
	static int[] paper = {0, 5, 5, 5, 5, 5};  // 크기별 색종이의 개수
	static int minCount = Integer.MAX_VALUE; // 색종이의 최소 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTrack(0, 0, 0);
		// minCount가 갱신되지 않았다면 색종이 붙이기가 불가능하다는 의미이므로 -1 출력. 아니라면 minCount 출력
		System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount); 
	}

	public static void backTrack(int x, int y, int used) {
		// 사용한 색종이의 수가 minCount보다 크다면 탐색할 필요 없음
		if (used >= minCount) return;
		
		// 모든 칸을 탐색한 경우
		if (x == 10) {
			minCount = Math.min(minCount, used);
			return;
		}
		
		// 아래로 이동
		if (y == 10) {
			backTrack(x+1, 0, used);
			return;
		}
		
		// 1을 찾으면 색종이 붙이기
		if (board[x][y] == 1) {
			for (int size = 5; size >= 1; size--) {
				if (canAttach(x, y, size)) { // 색종이를 붙일 수 있다면
					attach(x, y, size, 0); // 색종이 붙이기
					paper[size]--; // 사용한 색종이 수 감소
					backTrack(x, y+1, used+1); // 재귀 호출
					attach(x, y, size, 1); // 색종이 다시 떼기
					paper[size]++; // 색종이 떼었으므로 다시 색종이 수 증가
				}
			}
		} else { // 빈칸이면 (board[x][y]가 0이면)
			backTrack(x, y+1, used); // 다음 칸 탐색
		}
	}

	// 색종이를 붙이거나(0), 떼는(1) 코드
	public static void attach(int x, int y, int size, int state) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				board[i][j] = state;
			}
		}
	}
	
	// 색종이를 붙일 수 있는지 여부 확인
	public static boolean canAttach(int x, int y, int size) {
		if (paper[size] == 0) return false; // 남은 색종이 없으면 불가능
		
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (i >= 10 || j >= 10 || board[i][j] == 0) return false; 
			}
		}
		
		return true;
	}
}
