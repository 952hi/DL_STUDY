package _220331.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
//https://www.acmicpc.net/problem/12100
//메모리 : 18464KB, 시간 : 140ms
public class BOJ_12100_G2_2048_easy_answer {
	static class Reader {
		final private int BUFFER_SIZE = 1 << 7;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
	}

	static int Sboard[][];
	static int N;
	static int max = 0;

	static boolean MoveDirect(int direct, int[][] board, int i, int j) {
		int next = 0;

		if (direct == 1) {
			while (i - next - 1 >= 0 && board[i - next - 1][j] == 0) {
				next++;
			}
			if (next > 0) {
				board[i - next][j] = board[i][j];
				board[i][j] = 0;
			}
		} else if (direct == 2) {
			while (i + next + 1 < N && board[i + next + 1][j] == 0) {
				next++;
			}
			if (next > 0) {
				board[i + next][j] = board[i][j];
				board[i][j] = 0;
			}
		} else if (direct == 3) {
			while (j - next - 1 >= 0 && board[i][j - next - 1] == 0) {
				next++;
			}
			if (next > 0) {
				board[i][j - next] = board[i][j];
				board[i][j] = 0;
			}
		} else if (direct == 4) {
			while (j + next + 1 < N && board[i][j + next + 1] == 0) {
				next++;
			}
			if (next > 0) {
				board[i][j + next] = board[i][j];
				board[i][j] = 0;
			}
		}
		if (next > 0)
			return true;
		return false;
	}

	static boolean move(int direct, int board[][]) {
		boolean mvCnt = false;
		if (direct == 1) {
			for (int i = 0; i < N - 1; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] > 0) {
						int next = 1;
						while (i + next < N) {
							if (board[i + next][j] == 0) {
								next++;
								continue;
							} else if (board[i + next][j] == board[i][j]) {
								board[i][j] += board[i + next][j];
								max = Math.max(max, board[i][j]);
								board[i + next][j] = 0;
								mvCnt = true;
							}
							break;
						}
						if (MoveDirect(direct, board, i, j))
							mvCnt = true;
					}
				}
			}
			for (int j = 0; j < N; j++)
				if (board[N - 1][j] != 0)
					if (MoveDirect(direct, board, N - 1, j))
						mvCnt = true;
		} else if (direct == 2) {
			for (int i = N - 1; i > 0; i--) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] > 0) {
						int next = 1;
						while (i - next >= 0) {
							if (board[i - next][j] == 0) {
								next++;
								continue;
							} else if (board[i - next][j] == board[i][j]) {
								board[i][j] += board[i - next][j];
								max = Math.max(max, board[i][j]);
								board[i - next][j] = 0;
								mvCnt = true;
							}
							break;
						}
						if (MoveDirect(direct, board, i, j))
							mvCnt = true;
					}
				}
			}
			for (int i = 0; i < N; i++)
				if (board[0][i] != 0)
					if (MoveDirect(direct, board, 0, i))
						mvCnt = true;
		} else if (direct == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (board[i][j] > 0) {
						int next = 1;
						while (j + next < N) {
							if (board[i][j + next] == 0) {
								next++;
								continue;
							} else if (board[i][j + next] == board[i][j]) {
								board[i][j] += board[i][j + next];
								max = Math.max(max, board[i][j]);
								board[i][j + next] = 0;
								mvCnt = true;
							}
							break;
						}
						if (MoveDirect(direct, board, i, j))
							mvCnt = true;
					}
				}
			}
			for (int i = 0; i < N; i++)
				if (board[i][N - 1] != 0)
					if (MoveDirect(direct, board, i, N - 1))
						mvCnt = true;
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (board[i][j] > 0) {
						int next = 1;
						while (j - next >= 0) {
							if (board[i][j - next] == 0) {
								next++;
								continue;
							} else if (board[i][j - next] == board[i][j]) {
								board[i][j] += board[i][j - next];
								max = Math.max(max, board[i][j]);
								board[i][j - next] = 0;
								mvCnt = true;
							}
							break;
						}
						if (MoveDirect(direct, board, i, j))
							mvCnt = true;
					}
				}
			}
			for (int i = 0; i < N; i++)
				if (board[i][0] != 0)
					if (MoveDirect(direct, board, i, 0))
						mvCnt = true;
		}
		return mvCnt;
	}

	static void dfs(int cnt, int crntBoard[][]) {
		boolean notMove = false;
		if (cnt == 5)
			return;

		int[][] copyBoard = new int[N][];

		for (int i = 1; i <= 4; i++) {
			if (!notMove) {
				for (int j = 0; j < N; j++) {
					copyBoard[j] = Arrays.copyOf(crntBoard[j], crntBoard[j].length);
				}
				notMove = true;
			}
			if (move(i, copyBoard)) {
				notMove = false;
				dfs(cnt + 1, copyBoard);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		N = r.nextInt();

		Sboard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Sboard[i][j] = r.nextInt();
				max = Math.max(max, Sboard[i][j]);
			}
		}
		dfs(0, Sboard);
		System.out.println(max);
	}
}
