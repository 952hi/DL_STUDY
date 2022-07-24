package _0707.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_23791_G3_K번째음식찾기1 {
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

	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() throws IOException {
			if (bufferPos == bufferState) {
				bufferState = dis.read(buffer, bufferPos = 0, bfs);

				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];

		}

		int nextInt() throws IOException {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Reader br = new Reader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = br.nextInt();
		// 0 : 데이터, 1 : 순서, 2 : 양식, 한식 구분
		//한식, 양식 모두 저장하는 배열
		int[][] arr = new int[N * 2][3];

		//한식만 저장하는 배열
		int[][] arrA = new int[N][3];
		
		//양식만 저장하는 배열
		int[][] arrB = new int[N][3];
		int Acnt = 0, Bcnt = 0;

		// 한식, 양식을 입력 받음
		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < N; j++) {
				// 움삭 K의 값
				arr[(i - 1) * N + j][0] = br.nextInt();
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
		
		//모두 입력받은 배열은 음식값을 기준으로 오름차순 정렬 시행
		Arrays.sort(arr, (int[] o1, int[] o2) -> (o1[0] - o2[0]));

		boolean ok = false;
		int Q = br.nextInt(), Ai, Bi, k, left, right, mid, value;
		for (int i = 0; i < Q; i++) {
			ok = false;
			// 한식
			Ai = br.nextInt();
			// 양식
			Bi = br.nextInt();
			// 원하는 ~번째 요리
			k = br.nextInt();

			left = 0;
			right = N - 1;
			
			// 한식을 기준으로 특장 한식 값보다 더 작은 양식의 개수를 구해서 두 음식의 번호를 더했을 때 k가 되는지 확인
			while (left <= right) {
				mid = (left + right) / 2;

				if (mid >= Ai) {
					right = mid - 1;
					continue;
				}
				value = CntoppositeFood(arrB, Bi, arrA[mid][0]);
				if (k - 2 - mid == value) {
					sb.append(arrA[mid][1]).append(" ").append(arrA[mid][2] + 1).append("\n");
					ok = true;
					break;
				} else if (mid + value + 2 > k) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}

			if (ok == true)
				continue;

			left = 0;
			right = N - 1;
			
			// 양식을 기준으로 특장 양식 값보다 더 작은 한식의 개수를 구해서 두 음식의 번호를 더했을 때 k가 되는지 확인 
			while (left <= right) {
				mid = (left + right) / 2;

				if (mid >= Bi) {
					right = mid - 1;
					continue;
				}
				value = CntoppositeFood(arrA, Ai, arrB[mid][0]);
				if (k - 2 == value + mid) {
					sb.append(arrB[mid][1]).append(" ").append(arrB[mid][2] + 1).append("\n");
					break;
				} else if (mid + value + 2 > k) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
