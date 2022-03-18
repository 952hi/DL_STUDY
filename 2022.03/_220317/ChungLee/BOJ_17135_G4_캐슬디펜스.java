package _220317.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//41764kb, 360ms
class Reader {
	private int bfs = 1 << 16;
	private byte[] buffer = new byte[bfs];
	private DataInputStream dis = new DataInputStream(System.in);
	private int bufferLeft = 0, bufferState = 0;

	private byte read() throws IOException {
		if (bufferLeft == bufferState) {
			bufferState = dis.read(buffer, bufferLeft = 0, bfs);
			if (bufferState == -1)
				buffer[0] = -1;
		}
		return buffer[bufferLeft++];
	}

	public int nextInt() throws IOException {
		int rtn = 0;
		byte b = read();
		while (b <= ' ')
			b = read();
		boolean neg = (b == '-');
		if (neg)
			b = read();
		do
			rtn = rtn * 10 + b - '0';
		while ('0' <= (b = read()) && b <= '9');
		if (neg)
			return -rtn;
		return rtn;
	}
}

public class BOJ_17135_G4_캐슬디펜스 {

	static class yx {
		int y;
		int x;
		int depth;

		public yx(int y, int x, int depth) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
		}

	}

	static int N, M, D;
	static int[][] board;
	static int[] hunterPos = new int[3];
	static int maxHunt = 0;
	static Queue<yx> q = new LinkedList<>();
	static Queue<yx> huntedq = new LinkedList<>();
	static int dydx[][] = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 } };

	static void simul() {
		int sumHunt = 0;

		int tmp[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			tmp[i] = Arrays.copyOf(board[i], board[i].length);
		}
		// 사냥감이 다가오는 단계
		for (int k = 0; k < N; k++) {

			// 사냥꾼 순서대로 가장 가까운 사냥감을 찾기
			for (int i = 0; i < 3; i++) {
				boolean isVisit[][] = new boolean[N][M];
				isVisit[N - 1][hunterPos[i]] = true;
				if (tmp[N - 1][hunterPos[i]] == 1) {
					tmp[N - 1][hunterPos[i]] = -1;
					sumHunt++;
					huntedq.add(new yx(N - 1, hunterPos[i], 1));
					continue;
				}
				if (tmp[N - 1][hunterPos[i]] == -1) {
					continue;
				}
				q.add(new yx(N - 1, hunterPos[i], 1));
				LOOP: while (!q.isEmpty()) {
					yx tmpPos = q.poll();
					int tmpY = tmpPos.y;
					int tmpX = tmpPos.x;
					int dist = tmpPos.depth;

					if (dist == D)
						continue;

					for (int j = 0; j < 3; j++) {
						int nextY = tmpY + dydx[j][0];
						int nextX = tmpX + dydx[j][1];
						if (dist + 1 > D || 0 > nextY || 0 > nextX || nextX >= M)
							continue;

						if (tmp[nextY][nextX] == 1) {
							tmp[nextY][nextX] = -1;
							sumHunt++;
							huntedq.add(new yx(nextY, nextX, 0));
							break LOOP;
						} else if (tmp[nextY][nextX] == -1) {
							break;
						}
						// 방문확인
						else if (!isVisit[nextY][nextX]) {
							isVisit[nextY][nextX] = true;
							q.add(new yx(nextY, nextX, dist + 1));
						}
					}
				}
				q.clear();
			}

			while (!huntedq.isEmpty()) {
				yx huntedtmp = huntedq.poll();
				tmp[huntedtmp.y][huntedtmp.x] = 0;
			}

			// 사냥이 한턴 끝나면 사냥감은 내려옴.
			for (int i = N - 1; i > 0; i--) {
				for (int j = 0; j < M; j++) {
					tmp[i][j] = tmp[i - 1][j];
				}
			}
			// 가장 마지막 줄은 삭제해주기
			Arrays.fill(tmp[0], 0);
		}
		maxHunt = Math.max(maxHunt, sumHunt);
	}

	static void dfs(int next, int cnt) {
		if (cnt == 3) {
			// System.out.println(Arrays.toString(hunterPos));
			simul();
			return;
		}
		for (int i = next; i < M; i++) {
			hunterPos[cnt] = i;
			dfs(i + 1, cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader s = new Reader();
		// 행
		N = s.nextInt();
		M = s.nextInt();
		D = s.nextInt();
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = s.nextInt();
			}
		}
		dfs(0, 0);
		System.out.println(maxHunt);
	}
}
