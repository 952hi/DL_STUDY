package _0620.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

public class BOJ_1799_G1_비숍 {
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

	static int[][] bd, bd2, eachV;
	static int N, row, col, Zmax = 0, Omax = 0, avil = 0, tmprow, tmpcol, next;

	// 위로 비숍 존재하는지 검사
	static boolean isVishop(int row, int col) {
		// mark top to bottom
		tmprow = row + 1;
		tmpcol = col - 1;
		// left down mark
		while (tmprow < N && tmpcol >= 0) {
			if (bd[tmprow][tmpcol] == 1)
				bd[tmprow][tmpcol] = 2;
			tmprow += 1;
			tmpcol -= 1;
		}
		tmprow = row + 1;
		tmpcol = col + 1;
		// right down mark
		while (tmprow < N && tmpcol < N) {
			if (bd[tmprow][tmpcol] == 1)
				bd[tmprow][tmpcol] = 2;
			tmprow += 1;
			tmpcol += 1;
		}
		return true;
	}

	static boolean isSet(int row, int col, int who) {

		
		
		if (who == 0) {
			// check can visit
			tmprow = row - 1;
			tmpcol = col - 1;
			// left top mark
			while (tmprow >= 0 && tmpcol >= 0) {
				if (bd[tmprow][tmpcol] == 2)
					return false;
				tmprow -= 1;
				tmpcol -= 1;
			}

			tmprow = row - 1;
			tmpcol = col + 1;
			// right top mark
			while (tmprow >= 0 && tmpcol < N) {
				if (bd[tmprow][tmpcol] == 2)
					return false;
				tmprow -= 1;
				tmpcol += 1;
			}
			bd[row][col] = 2;
		}
			
		else {
			// check can visit
			tmprow = row - 1;
			tmpcol = col - 1;
			// left top mark
			while (tmprow >= 0 && tmpcol >= 0) {
				if (bd2[tmprow][tmpcol] == 2)
					return false;
				tmprow -= 1;
				tmpcol -= 1;
			}

			tmprow = row - 1;
			tmpcol = col + 1;
			// right top mark
			while (tmprow >= 0 && tmpcol < N) {
				if (bd2[tmprow][tmpcol] == 2)
					return false;
				tmprow -= 1;
				tmpcol += 1;
			}
			bd2[row][col] = 2;
		}
			
		return true;
	}

	static void rollback(int row, int col) {
		bd[row][col] = 1;

		// check can visit
		tmprow = row - 1;
		tmpcol = col - 1;
		// left down mark
		while (tmprow >= 0 && tmpcol >= 0) {
			if (bd[tmprow][tmpcol] == 2)
				bd[tmprow][tmpcol] = 1;
			tmprow -= 1;
			tmpcol -= 1;
		}
		tmprow = row - 1;
		tmpcol = col + 1;
		// right down mark
		while (tmprow >= 0 && tmpcol < N) {
			if (bd[tmprow][tmpcol] == 2)
				bd[tmprow][tmpcol] = 1;
			tmprow -= 1;
			tmpcol += 1;
		}

		// check can visit
		tmprow = row + 1;
		tmpcol = col - 1;
		// left down mark
		while (tmprow < N && tmpcol >= 0) {
			if (bd[tmprow][tmpcol] == 2)
				bd[tmprow][tmpcol] = 1;
			tmprow += 1;
			tmpcol -= 1;
		}
		tmprow = row + 1;
		tmpcol = col + 1;
		// right down mark
		while (tmprow < N && tmpcol < N) {
			if (bd[tmprow][tmpcol] == 2)
				bd[tmprow][tmpcol] = 1;
			tmprow += 1;
			tmpcol += 1;
		}
	}

	// N*N개 반복
	static void dfs1(int crnt, int cnt) {
		if (crnt >= N * N) {
			Zmax = Math.max(cnt, Zmax);
			return;
		}

		int row = crnt / N;
		int col = crnt % N;

		if (N % 2 == 0 && row % 2 == 1) {
			col++;
		}

		// 현재 자리 가능 && can LT, RT
		if (bd[row][col] == 1 && isSet(row, col, 0)) {
			dfs1(crnt + 2, cnt + 1);
			bd[row][col] = 1;
		}

		dfs1(crnt + 2, cnt);
	}

	static void dfs2(int crnt, int cnt) {

		if (crnt >= N * N) {
			Omax = Math.max(cnt, Omax);
			return;
		}

		int row = crnt / N;
		int col = crnt % N;
		// N이 짝수, 홀수번째는 홀수행마다 -1
		if (N % 2 == 0 && row % 2 == 1) {
			col--;
		}

		// 현재 자리 가능 && can LT, RT
		if (bd2[row][col] == 1 && isSet(row, col, 1)) {
			dfs2(crnt + 2, cnt + 1);
			bd2[row][col] = 1;
		}

		dfs2(crnt + 2, cnt);

	}

	// 좌하, 우하 모두 2로 변경
	public static void main(String[] args) throws IOException {
		Reader br = new Reader();

		N = br.nextInt();

		bd = new int[N][N];
		bd2 = new int[N][N];
		eachV = new int[N * N][2];
		next = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bd[i][j] = br.nextInt();
				bd2[i][j] = bd[i][j];
			}
		}
		dfs1(0, 0);
		if (N != 1) {
			dfs2(1, 0);
		}

		System.out.println(Zmax + Omax);
	}
}
