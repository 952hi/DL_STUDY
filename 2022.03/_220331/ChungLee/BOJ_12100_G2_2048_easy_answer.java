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
			//유효한 범위에서 진행방향으로 빈칸이 남아있다면 이동할 칸수를 카운트
			while (i - next - 1 >= 0 && board[i - next - 1][j] == 0) {
				next++;
			}
			//카운트된 수로 노드를 이동
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
	//합치는 구간
	static boolean move(int direct, int board[][]) {
		boolean mvCnt = false;
		if (direct == 1) {
			// 행을 순회
			for (int i = 0; i < N - 1; i++) {
				// 열을 순회
				for (int j = 0; j < N; j++) {
					// 0이 아닌 값이 들어있을 때
					if (board[i][j] > 0) {
						int next = 1;
						// 진행 방향 반대방향으로 자신과 같은 수를 탐색
						while (i + next < N) {
							// 빈칸이라면 다음 칸 탐색
							if (board[i + next][j] == 0) {
								next++;
								continue;
							}
							// 값이 같다면 자신의 값을 2배로 만들고 합쳤기 때문에 최대값을 갱신해준다.
							// 탐색한 노드는 0으로 초기화해주고 움직임이 있었음을 체크한다.
							else if (board[i + next][j] == board[i][j]) {
								board[i][j] *=2;
								max = Math.max(max, board[i][j]);
								board[i + next][j] = 0;
								mvCnt = true;
							}
							break;
						}
						//합친 후 진행방향으로 옮겨주는 구간
						if (MoveDirect(direct, board, i, j))
							mvCnt = true;
					}
				}
			}
			//마지막 자리는 자리만 옮겨줌.
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
		//5번 초과부터는 진행 X
		if (cnt == 5)
			return;

		int[][] copyBoard = new int[N][];

		for (int i = 1; i <= 4; i++) {
			//움직임이 없다면 배열을 사용하지 않았으므로 중복 복사 하지 않음.
			if (!notMove) {
				for (int j = 0; j < N; j++) {
					copyBoard[j] = Arrays.copyOf(crntBoard[j], crntBoard[j].length);
				}
				notMove = true;
			}
			//움직임이 있다면 cnt를 증가시켜준 뒤 다음 칸으로 이동
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
