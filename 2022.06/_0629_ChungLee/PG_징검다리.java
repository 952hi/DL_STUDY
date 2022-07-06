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
		Arrays.sort(rocks);
		long ans = 0;
		long left = 1, right = distance, mid = 0;

		while (left <= right) {
			int cnt = 0;
			int prev = 0;
			mid = (left + right) / 2;

			// 매번 간격 조정 후 모든 돌들의 거리를 비교
			for (int i = 0; i < rocks.length; ++i) {
				// 만약 mid 값보다 작거나 현재 값으로 최신화한 값을 뺀 값이 mid값보다 작을 때
				if (rocks[i] - prev < mid) {
					// 제거할 수 있는 돌이 존재
					cnt++;
				}
				// 만약 mid값보다 크다면 현재값을 현재 돌의 위치로 지정
				else {
					prev = rocks[i];
				}
			}

			// 마지막 돌과 도착점 사이의 거리도 확인
			if (distance - prev < mid)
				cnt++;
			// 없애야 하는 돌 갯수보다 작거나 같을 때
			if (cnt <= n) {
				// 통과되었기 때문에 바위 사이 값인 mid값을 키우기 위해 left를 최신화
				left = mid + 1;
				ans = mid > ans ? mid : ans;
			} 
			//없애야 하는 돌보다 더 많은 돌을 없애야 한다면 최소최대값(mid)을 줄이기 위해 right를 최신화
			else {
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
