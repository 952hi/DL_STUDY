package _220411.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

public class BOJ_1135_G2_뉴스전하기 {
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
		int N = r.nextInt();
		// 입력 값 저장
		int[] arr = new int[N];

		int[] answer = new int[N];

		// 해당 상사의 부하직원 카운팅
		int[] arrV = new int[N];

		// 해당 직원의 차수
		int[] rank = new int[N];

		r.nextInt();
		arr[0] = 0;
		// i = 부하 직원의 번호
		for (int i = 1; i < N; i++) {
			// c = 상사의 번호
			arr[i] = r.nextInt();
			rank[i] = rank[arr[i]] + 1;
			// 각 상사의 자식 + 1
			arrV[arr[i]]++;
		}
		for (int i = 1; i < N; i++) {

		}
	}

}
