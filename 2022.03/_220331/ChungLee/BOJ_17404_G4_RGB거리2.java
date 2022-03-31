package _220331.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_17404_G4_RGB거리2 {
	static class Reader {
		int bfs = 1 << 5;
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
			int rtn = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean neg = (b == '-');
			do
				rtn = rtn * 10 + b - '0';
			while ('0' <= (b = read()) && b <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	public static void main(String[] args) {
		Reader r = new Reader();
		int N = r.nextInt();
		int[][][] rgbR = new int[N][3][3];
		rgbR[0][0][0] = r.nextInt();
		rgbR[0][1][1] = r.nextInt();
		rgbR[0][2][2] = r.nextInt();

		int[] crnt = new int[3];
		// 행을 순회
		for (int i = 1; i < N; i++) {
			
			for (int j = 0; j < 3; j++) {
				crnt[j] = r.nextInt();
			}
			// 각 열을 순회
			for (int j = 0; j < 3; j++) {
				// 각 열의 다음 자리
				for (int k = 0; k < 3; k++) {
					if (rgbR[i - 1][j][k] == 0)
						continue;

					if (k == 0) {
						if (rgbR[i][j][1] != 0)
							rgbR[i][j][1] = Math.min(rgbR[i][j][1], rgbR[i - 1][j][k] + crnt[1]);
						else {
							rgbR[i][j][1] = rgbR[i - 1][j][k] + crnt[1];
						}
						if (rgbR[i][j][2] != 0)
							rgbR[i][j][2] = Math.min(rgbR[i][j][2], rgbR[i - 1][j][k] + crnt[2]);
						else {
							rgbR[i][j][2] = rgbR[i - 1][j][k] + crnt[2];
						}
					} else if (k == 1) {
						if (rgbR[i][j][0] != 0)
							rgbR[i][j][0] = Math.min(rgbR[i][j][0], rgbR[i - 1][j][k] + crnt[0]);
						else {
							rgbR[i][j][0] = rgbR[i - 1][j][k] + crnt[0];
						}
						if (rgbR[i][j][2] != 0)
							rgbR[i][j][2] = Math.min(rgbR[i][j][2], rgbR[i - 1][j][k] + crnt[2]);
						else {
							rgbR[i][j][2] = rgbR[i - 1][j][k] + crnt[2];
						}
					} else if (k == 2) {
						if (rgbR[i][j][0] != 0)
							rgbR[i][j][0] = Math.min(rgbR[i][j][0], rgbR[i - 1][j][k] + crnt[0]);
						else {
							rgbR[i][j][0] = rgbR[i - 1][j][k] + crnt[0];
						}
						if (rgbR[i][j][1] != 0)
							rgbR[i][j][1] = Math.min(rgbR[i][j][1], rgbR[i - 1][j][k] + crnt[1]);
						else {
							rgbR[i][j][1] = rgbR[i - 1][j][k] + crnt[1];
						}
					}
				}
			}
		}
		int R = Math.min(rgbR[N - 1][0][1], rgbR[N - 1][0][2]);
		int G = Math.min(rgbR[N - 1][1][0], rgbR[N - 1][1][2]);
		int B = Math.min(rgbR[N - 1][2][0], rgbR[N - 1][2][1]);
		System.out.println(Math.min(Math.min(R, G), B));
	}
}
