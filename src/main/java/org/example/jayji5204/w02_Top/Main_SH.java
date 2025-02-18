import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new File("input.txt"));

		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] result = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = N - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[i]) {
					result[i] = j + 1;
					break;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.print(result[i]+" ");
		}

	}
}
