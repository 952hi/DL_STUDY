package _220512.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

//https://www.acmicpc.net/problem/2096
public class BOJ_2096_G4_내려가기 {
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

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int N = r.nextInt();
		// 입력 따로
		int[] input = new int[3];
		int[][] cal_min = new int[2][3];
		int[][] cal_max = new int[2][3];
		// 계산 따로
		for (int i = 0; i < 3; i++) {
			cal_min[0][i] = r.nextInt();
			cal_max[0][i] = cal_min[0][i];
		}
		for (int i = 1; i < N; i++) {
			// 새 값 입력
			for (int j = 0; j < 3; j++) {
				input[j] = r.nextInt();
			}
			// 홀수일 때
			if (i % 2 == 1) {
				cal_min[1][0] = input[0] + Math.min(cal_min[0][0], cal_min[0][1]);
				cal_min[1][1] = input[1] + Math.min(Math.min(cal_min[0][0], cal_min[0][1]), cal_min[0][2]);
				cal_min[1][2] = input[2] + Math.min(cal_min[0][1], cal_min[0][2]);

				cal_max[1][0] = input[0] + Math.max(cal_max[0][0], cal_max[0][1]);
				cal_max[1][1] = input[1] + Math.max(Math.max(cal_max[0][0], cal_max[0][1]), cal_max[0][2]);
				cal_max[1][2] = input[2] + Math.max(cal_max[0][1], cal_max[0][2]);
			}
			// 짝수일 때
			else {
				cal_min[0][0] = input[0] + Math.min(cal_min[1][0], cal_min[1][1]);
				cal_min[0][1] = input[1] + Math.min(Math.min(cal_min[1][0], cal_min[1][1]), cal_min[1][2]);
				cal_min[0][2] = input[2] + Math.min(cal_min[1][1], cal_min[1][2]);

				cal_max[0][0] = input[0] + Math.max(cal_max[1][0], cal_max[1][1]);
				cal_max[0][1] = input[1] + Math.max(Math.max(cal_max[1][0], cal_max[1][1]), cal_max[1][2]);
				cal_max[0][2] = input[2] + Math.max(cal_max[1][1], cal_max[1][2]);
			}
		}
		if (N % 2 == 0)
			System.out.println(Math.max(cal_max[1][0], Math.max(cal_max[1][1], cal_max[1][2])) + " "
					+ Math.min(cal_min[1][0], Math.min(cal_min[1][1], cal_min[1][2])));
		else
			System.out.println(Math.max(cal_max[0][0], Math.max(cal_max[0][1], cal_max[0][2])) + " "
					+ Math.min(cal_min[0][0], Math.min(cal_min[0][1], cal_min[0][2])));
	}

}
