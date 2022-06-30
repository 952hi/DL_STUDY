package _0629_ChungLee;

import java.util.*;
import java.io.*;

public class PG_징검다리 {
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

	public static int solution(int distance, int[] rocks, int n) {
		// 이분탐색은 오름차순으로 정렬되어있는 경우를 전제로한다.
		Arrays.sort(rocks);
		long ans = 0;
		long left = 1, right = distance, mid = 0;

		while (left <= right) {
			int cnt = 0;
			int prev = 0;
			mid = (left + right) / 2;

			for (int i = 0; i < rocks.length; ++i) {
				if (rocks[i] - prev < mid) {
					// mid보다 작은 값이 존재한다는 뜻으로
					// 해당 돌을 제거한다.
					cnt++;
				} else {
					// mid보다 크거나 같은 값이 존재하므로
					// prev를 현재 돌로 초기화한다.
					prev = rocks[i];
				}
			}

			// 마지막 돌과 도착점 사이의 거리도 확인한다.
			if (distance - prev < mid)
				cnt++;
			//없애야 하는 돌 갯수보다 작거나 같으면
			if (cnt <= n) {
				// 주어진 n 보다 작거나 같은 만큼 돌을 없애서
				// 최솟값 x를 만들 수 있다.
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return (int) ans;
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		int distance = br.nextInt();
		int rockCnt = br.nextInt();
		int[] rocks = new int[rockCnt];
		for (int i = 0; i < rockCnt; i++) {
			rocks[i] = br.nextInt();
		}
		int n = br.nextInt();

		System.out.println(solution(distance, rocks, n));
	}
}
