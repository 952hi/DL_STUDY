package _0801.ChungLee;

import java.util.*;
import java.io.*;

//1. 테케 다 통과해서 행복했다가 시간초과 받고 절망..
//2. DP방식
public class BOJ_1103_G2_게임 {
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

		String readLine() throws IOException {
			int cnt = 0;
			byte b;
			byte[] tmp = new byte[bfs];
			while ((b = read()) != ' ') {
				if (b == '\r' || b == '\n') {
					if (cnt != 0) {
						break;
					}
					continue;
						
				}
				tmp[cnt++] = b;
			}
			return new String(tmp, 0, cnt);
		}

	}

	static int row, col;
	static int[][] bd;
	static boolean[][] visit;
	// 0 : 상, 1 : 우, 2 : 하, 3: 좌
	static int[][][] dp;
	static int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int maxDpth = 0;

	static int dfs(int crntY, int crntX) {
		int nextY, nextX, crntV = bd[crntY][crntX], returned;

		for (int i = 0; i < 4; i++) {
			if (maxDpth == -1)
				return -1;

			nextY = crntY + dydx[i][0] * crntV;
			nextX = crntX + dydx[i][1] * crntV;

			// 범위 밖일 경우 게임 종료
			if (nextY >= row || nextX >= col || nextY < 0 || nextX < 0) {
				dp[crntY][crntX][i] = 1;
				continue;
			}
			// 다음 자리가 구멍이라면
			if (bd[nextY][nextX] == 0) {
				dp[crntY][crntX][i] = 1;
				continue;
			}

			// 이미 방문한 곳이 있다면 순환 가능
			if (visit[nextY][nextX]) {
				return -1;
			}

			// 방문 이력이 없는 경우
			if (dp[crntY][crntX][i] == 0) {
				visit[nextY][nextX] = true;
				returned = dfs(nextY, nextX);
				if (returned == -1)
					return -1;

				visit[nextY][nextX] = false;
				dp[crntY][crntX][i] = returned + 1;
			}
			// 방문이력이 있는 경우
		}

		return Math.max(dp[crntY][crntX][0],
				Math.max(dp[crntY][crntX][1], Math.max(dp[crntY][crntX][2], dp[crntY][crntX][3])));

	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		row = br.nextInt();
		col = br.nextInt();
		bd = new int[row][col];
		dp = new int[row][col][4];
		visit = new boolean[row][col];
		char[] each;
		for (int i = 0; i < row; i++) {
			each = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (each[j] == 'H') {
					bd[i][j] = 0;
					continue;
				}
				bd[i][j] = each[j] - '0';
			}
		}
		System.out.println(dfs(0, 0));
	}
}
