import java.io.*;
import java.util.*;

public class MainBackup {
	static int N;
	static int[][] arr, arrA, arrB;

	static int CntoppositeFood(int[][] variable, int range, int limit) {
		int left = 0, right = range - 1, mid = 0, dest = -1;
		while (left <= right) {
			mid = (left + right) / 2;

			if (variable[mid][0] < limit) {
				dest = variable[mid][2];
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return dest;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		// 0 : 데이터, 1 : 순서, 2 : 양식, 한식 구분
		arr = new int[N * 2][3];
		arrA = new int[N][3];
		arrB = new int[N][3];
		int Acnt = 0, Bcnt = 0;
		List<int[]> list = new LinkedList<>();
		for (int i = 1; i <= 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 움삭 K의 값
				arr[(i - 1) * N + j][0] = Integer.parseInt(st.nextToken());
				// 한식 : 1, 양식 : 2
				arr[(i - 1) * N + j][1] = i;
				// 해당 분야의 음식 순서
				arr[(i - 1) * N + j][2] = j;
				if (arr[(i - 1) * N + j][1] == 1) {
					arrA[Acnt][0] = arr[(i - 1) * N + j][0];
					arrA[Acnt][1] = arr[(i - 1) * N + j][1];
					arrA[Acnt++][2] = arr[(i - 1) * N + j][2];
				} else {
					arrB[Bcnt][0] = arr[(i - 1) * N + j][0];
					arrB[Bcnt][1] = arr[(i - 1) * N + j][1];
					arrB[Bcnt++][2] = arr[(i - 1) * N + j][2];
				}
			}
		}
		Arrays.sort(arr, (int[] o1, int[] o2) -> (o1[0] - o2[0]));

		int returnV;
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
//			sb.append(i).append(" : ");
			st = new StringTokenizer(br.readLine());
			// 한식
			int Ai = Integer.parseInt(st.nextToken());
			// 양식
			int Bi = Integer.parseInt(st.nextToken());
			// ~번째 요리
			int k = Integer.parseInt(st.nextToken());

			int left1 = 0, right1 = N - 1, mid1;
			int left2 = 1, right2 = N - 1, mid2 = 0;

			for (int j = 0; j <= Ai; j++) {
				mid1 = j - 1;

				int ans = k - 2 - mid1;

				if (mid1 >= Ai || ans >= Bi)
					continue;
				// A가 하나도 없을 때
				if (mid1 == -1) {
					// B 밑으로 A가 하나도 없다면 OK
					returnV = CntoppositeFood(arrA, Ai, arrB[ans][0]);
					if (returnV == mid1) {
						sb.append(arrB[ans][1]).append(" ").append(arrB[ans][2] + 1).append("\n");
						break;
					}
				}
				// B가 하나도 없을 때
				else if (ans == -1) {
					// A 이하로 B가 하나도 없다면 OK
					returnV = CntoppositeFood(arrB, Bi, arrA[mid1][0]);
					if (returnV == ans) {
						sb.append(arrA[mid1][1]).append(" ").append(arrA[mid1][2] + 1).append("\n");
						break;
					}
				}

				else if (arrA[mid1][0] > arrB[ans][0]) {
					returnV = CntoppositeFood(arrB, Bi, arrA[mid1][0]);
					if (returnV == ans) {
						sb.append(arrA[mid1][1]).append(" ").append(arrA[mid1][2] + 1).append("\n");
						break;
					}
				}

				else {
					returnV = CntoppositeFood(arrA, Ai, arrB[ans][0]);
					if (returnV == mid1) {
						sb.append(arrB[ans][1]).append(" ").append(arrB[ans][2] + 1).append("\n");
						break;
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
