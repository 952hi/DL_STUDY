package _1023.LC;

import java.io.*;
import java.util.*;

class BOJ_G3_모자이크 {
	static class reader {
		int bufferSize = 1 << 18;
		byte[] buffer = new byte[bufferSize];
		DataInputStream dis = new DataInputStream(System.in);
		int bufferPointer = 0, bytesRead = 0;

		byte read() throws IOException {
			if (bufferPointer == bytesRead) {
				bytesRead = dis.read(buffer, bufferPointer = 0, bufferSize);
				if (bytesRead == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPointer++];
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		reader br = new reader();
		int y = br.nextInt();
		int x = br.nextInt();

		boolean[] arr = new boolean[x + 1];

		int paper = br.nextInt();
		int wrong = br.nextInt();

		// 가장 높은 모자이크를 구하기(최소 종이의 길이가 됨)
		int maxHeight = 0;
		int wrongY, wrongX = 0;
		for (int i = 0; i < wrong; i++) {
			wrongY = br.nextInt();
			wrongX = br.nextInt();
			maxHeight = Math.max(maxHeight, wrongY);
			arr[wrongX] = true;
		}
		// left: 최소 종이는 틀린 모자이크 중 최대Y, right: 최대 높이, 최대 X 중 더 큰 것
		// 겹칠 수 있기 때문에 전체 불량 모자이크를 덮을 수 있는 사각형이라면 그냥 한 자리에서 중복 겹치기
		// 하지만 조금씩만 겹칠 수 있다면
		int cnt, answer = Integer.MAX_VALUE, left = Math.min(maxHeight, wrongX), right = Math.max(maxHeight, wrongX),
				mid = 0;

		// 특정
		while (left <= right) {
			cnt = 0;
			mid = (left + right) / 2;
			// 몇 장 가능한지 계산
			for (int i = 1; i <= x; i++) {
				// 불량 모자이크가 있는 열
				if (arr[i] == true) {
					cnt++;
					i += mid - 1;
				}
			}
			// 종이 개수와 동일하다면 길이를 줄여야 함.
			if (cnt == paper) {
				right = mid - 1;
				answer = Math.min(answer, mid);
			}
			// 더 많은 종이를 사용한 경우
			else if (cnt > paper) {
				left = mid + 1;
			}
			// 종이를 더 적게 사용한 경우
			else {
				right = mid - 1;
			}
		}
		if (answer == Integer.MAX_VALUE)
			System.out.println(maxHeight);
		else
			System.out.println(answer);
	}
}