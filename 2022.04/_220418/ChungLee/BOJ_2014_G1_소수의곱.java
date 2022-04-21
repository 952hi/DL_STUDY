package _220418.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class BOJ_2014_G1_소수의곱 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferPos == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferPos = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];

		}

		int nextInt() {
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

	public static void main(String[] args) {
		Reader r = new Reader();
		int K = r.nextInt();
		int N = r.nextInt();

		// 초기 입력 소수의 길이만큼 배열 생성
		long[] V = new long[K];
		// 소수들의 곱의 값을 저장할 우선순위 큐 생성
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		// 시작 소수들을 입력받고 동시에 pq에 저장
		for (int i = 0; i < K; i++) {
			V[i] = r.nextInt();
			pq.add(V[i]);
		}

		long crnt, value;

		// 찾고자 하는 소수의 번지수만큼 반복
		for (int i = 0; i < N; i++) {
			crnt = pq.poll();
			// 꺼낸 값들을 처음 입력받은 소수들과 또 다시 반복하며 곱셈
			for (int j = 0; j < K; j++) {
				// pq에서 가장 작은 값과 입력 받은 소수들을 순서대로 곱셈 후 저장
				value = crnt * V[j];
				pq.add(value);
				// 곱셈한 값이 처음에 입력 받은 소수들로 나눠지는 경우(중복) 다음 탐색
				if (crnt % V[j] == 0)
					break;
			}

		}

	}

}
