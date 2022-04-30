package _220428.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_20056_G4_마법사상어와파이어볼 {
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
		int[][] dydx = new int[][] { //
				{ -1, 0 }, { -1, 1 }, { 0, 1 }, //
				{ 1, 1 }, { 1, 0 }, { 1, -1 }, //
				{ 0, -1 }, { -1, -1 } };
		int N = r.nextInt();
		int fb = r.nextInt();
		int K = r.nextInt();
		int j = 0;
		int crntY, choose, crntX, mass, speed, direct, row, col, frCnt, nextY, nextX;
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> confirmVisit = new LinkedList<>();
		int[] fire, posYX;
		int[][][] tmp = new int[N + 2][N + 2][5];

		for (int i = 0; i < fb; i++) {
			row = r.nextInt();
			col = r.nextInt();
			mass = r.nextInt();
			speed = r.nextInt();
			direct = r.nextInt();
			q.add(new int[] { row, col, mass, speed, direct });
		}

		for (int i = 0; i < K; i++) {
			while (!q.isEmpty()) {
				fire = q.poll();
				nextY = fire[0] + fire[3] * dydx[fire[4]][0] - 1;
				nextX = fire[1] + fire[3] * dydx[fire[4]][1] - 1;

				nextY = nextY % N;
				if (nextY < 0)
					nextY += N;
				nextX = nextX % N;
				if (nextX < 0)
					nextX += N;
				nextY += 1;
				nextX += 1;
				if (tmp[nextY][nextX][4] == 0)
					confirmVisit.add(new int[] { nextY, nextX });
				tmp[nextY][nextX][0] += fire[2];
				tmp[nextY][nextX][1] += fire[3];
				tmp[nextY][nextX][2]++;
				if (tmp[nextY][nextX][4] == 0) {
					tmp[nextY][nextX][3] = fire[4];
					tmp[nextY][nextX][4] = 1;
				} else if (tmp[nextY][nextX][4] == 1) {
					if (tmp[nextY][nextX][3] % 2 != fire[4] % 2) {
						tmp[nextY][nextX][4] = 2;
					}
				}
			}
			while (!confirmVisit.isEmpty()) {
				posYX = confirmVisit.poll();

				crntY = posYX[0];
				crntX = posYX[1];

				mass = tmp[crntY][crntX][0];
				speed = tmp[crntY][crntX][1];
				frCnt = tmp[crntY][crntX][2];
				direct = tmp[crntY][crntX][3];
				choose = tmp[crntY][crntX][4];

				tmp[crntY][crntX] = new int[5];
				if (frCnt == 1) {
					q.add(new int[] { crntY, crntX, mass, speed, direct });
					continue;
				}
				if (mass < 5)
					continue;
				mass = Math.floorDiv(mass, 5);
				speed = Math.floorDiv(speed, frCnt);

				if (choose == 1) {
					for (j = 0; j < 8; j += 2)
						q.add(new int[] { crntY, crntX, mass, speed, j });
				} else {
					for (j = 1; j < 8; j += 2)
						q.add(new int[] { crntY, crntX, mass, speed, j });
				}
			}
		}
		int sum = 0;
		while (!q.isEmpty()) {
			sum += q.poll()[2];
		}
		System.out.println(sum);
	}
}
