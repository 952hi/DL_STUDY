package _220418.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

public class BOJ_12865_G5_평범한배낭 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferLeft = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferLeft == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferLeft = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferLeft++];
		}

		int nextInt() {
			int n = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean neg = (b == '-');
			if (neg)
				b = read();
			do
				n = n * 10 + b - '0';
			while ('0' <= (b = read()) && b <= '9');
			if (neg)
				return -n;
			return n;
		}
	}

	static int max = 0;
	static int K, N;
	static int[][] items;

	public static void main(String[] args) {
		Reader r = new Reader();
		int N = r.nextInt();
		int K = r.nextInt();
		int[][] data = new int[N][2];
		int[][] nap = new int[N][K + 1];

		for (int i = 0; i < N; i++) {
			data[i][0] = r.nextInt();
			data[i][1] = r.nextInt();
		}
		// 우선 첫 열을 배열에 저장
		for (int i = 0; i <= K; i++) {
			if (i >= data[0][0])
				nap[0][i] = data[0][1];
		}
		// 모든 물건을
		for (int i = 1; i < N; i++) {
			// 각 무게 별로 검사
			for (int j = 1; j <= K; j++) {

				// 현재 물건의 무게보다 작은 case일 때
				if (j < data[i][0]) {
					// 이전 값, 현재 들어와있는 값 중 큰 값을 저장
					nap[i][j] = nap[i - 1][j];
				}
				// 현재 물건의 무게보다 큰 case일 때
				else {
					nap[i][j] = Math.max(nap[i - 1][j], nap[i - 1][j - data[i][0]] + data[i][1]);
				}

			}
		}
		System.out.println(nap[N - 1][K]);

	}
}