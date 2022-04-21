package _220421.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_1520_G4_내리막길 {
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		Reader r = new Reader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int nY, nX, row, col;
		int[][] bd;
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2];
			}
		});
		int[][] dydx = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		row = r.nextInt();
		col = r.nextInt();
		bd = new int[row + 2][col + 2];
		int[][] checkbd = new int[row + 2][col + 2];
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bd[i][j] = r.nextInt();
			}
		}
		checkbd[1][1] = 1;
		q.add(new int[] { 1, 1, bd[1][1] });
		while (!q.isEmpty()) {
			int[] crnt = q.poll();
			for (int i = 0; i < 4; i++) {
				nY = crnt[0] + dydx[i][0];
				nX = crnt[1] + dydx[i][1];
				if (bd[nY][nX] == 0)
					continue;
				if (bd[nY][nX] < bd[crnt[0]][crnt[1]]) {
					if (checkbd[nY][nX] == 0) {
						checkbd[nY][nX] = checkbd[crnt[0]][crnt[1]];
						q.add(new int[] { nY, nX, bd[nY][nX] });
					} else {
						checkbd[nY][nX] += checkbd[crnt[0]][crnt[1]];
					}

				}
			}
		}
		System.out.println(checkbd[row][col]);
	}
}
