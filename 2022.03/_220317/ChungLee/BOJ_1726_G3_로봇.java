package _220317.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//메모리 : 14636KB, 140ms

public class BOJ_1726_G3_로봇 {
	static class Reader {
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

	static class yx {
		int y;
		int x;
		int dir;
		int cnt;

		public yx(int y, int x, int dir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	static int row;
	static int col;
	static int board[][];
	static boolean[][][] isVisit;
	static int robot[] = new int[3];
	static int dest[] = new int[3];
	static int dydx[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; // 상 우 하 좌
	static int crntRobot[];
	static int minSum = Integer.MAX_VALUE;
	static Queue<yx> q = new LinkedList<yx>();

//	static void goK(int cnt) {
//		crntRobot[0] = robot[0];
//		crntRobot[1] = robot[1];
//		crntRobot[2] = robot[2];
//		int turnSum = 0;
//		int next = 2;
//		while (isVisit[crntRobot[0]][crntRobot[1]] != cnt) {
//
//			// 동서남북 사방 탐색
//			for (int i = 1; i <= 4; i++) {
//				// 이동할 다음 위치 찾았을 때
//				if (isVisit[crntRobot[0] + dydx[i][0]][crntRobot[1] + dydx[i][1]] == next) {
//					// 방향 같을 때
//					if (crntRobot[2] == i) {
//						// 3칸씩 움직일 수 있을 때
//						for (int k = 3; k > 0; k--) {
//							if (1 <= crntRobot[0] + dydx[i][0] * k && crntRobot[0] + dydx[i][0] * k <= row
//									&& 1 <= crntRobot[1] + dydx[i][1] * k && crntRobot[1] + dydx[i][1] * k <= col) {
//								if (isVisit[crntRobot[0] + dydx[i][0] * k][crntRobot[1] + dydx[i][1] * k] == next - 1
//										+ k) {
//									crntRobot[0] += dydx[i][0] * k;
//									crntRobot[1] += dydx[i][1] * k;
//									turnSum++;
//									next += k;
//									k++;
//								}
//							}
//						}
//						break;
//					}
//					// 방향이 다를 때
//					else {
//						// 진행해야하는 방향이 원하는 방향과 반대일 때
//						if ((crntRobot[2] == 2 && i == 1) || (crntRobot[2] == 1 && i == 2)
//								|| (crntRobot[2] == 4 && i == 3) || (crntRobot[2] == 3 && i == 4)) {
//							turnSum += 2;
//						}
//						// 진행 방향의 좌, 우로 움직이면 될 때
//						else
//							turnSum++;
//
//						crntRobot[2] = i;
//						i--;
//					}
//				}
//			}
//			if (turnSum >= minSum)
//				return;
//
//		}
//		if (!(crntRobot[2] == dest[2])) {
//			if ((crntRobot[2] == 2 && dest[2] == 1) || (crntRobot[2] == 1 && dest[2] == 2)
//					|| (crntRobot[2] == 4 && dest[2] == 3) || (crntRobot[2] == 3 && dest[2] == 4)) {
//				turnSum += 2;
//			}
//			// 진행 방향의 좌, 우로 움직이면 될 때
//			else
//				turnSum++;
//		}
//		minSum = Math.min(minSum, turnSum);
//	}

	static void bfs() {
		isVisit[robot[0]][robot[1]][robot[2]] = true;

		q.add(new yx(robot[0], robot[1], robot[2], 0));
		while (!q.isEmpty()) {
			yx crntPos = q.poll();
			int y = crntPos.y;
			int x = crntPos.x;
			int cnt = crntPos.cnt;
			int dir = crntPos.dir;

			if (y == dest[0] && x == dest[1] && dir == dest[2]) {
				System.out.println(cnt);
				return;
			}

			for (int i = 1; i <= 3; i++) {
				int nextY = y + dydx[dir][0] * i;
				int nextX = x + dydx[dir][1] * i;
				if (!(1 <= nextY && nextY <= row && 1 <= nextX && nextX <= col))
					continue;

				if (board[nextY][nextX] == 0) {
					if (!isVisit[nextY][nextX][dir]) {
						isVisit[nextY][nextX][dir] = true;
						q.add(new yx(nextY, nextX, dir, cnt + 1));
					}
				} else
					break;
			}

			for (int i = 1; i <= 4; i++) {
				if (dir != i && !isVisit[y][x][i]) {
					int turn = 1;
					if (dir == 1) {
						if (i == 2) {
							turn++;
						}
					} else if (dir == 2) {
						if (i == 1) {
							turn++;
						}
					} else if (dir == 3) {
						if (i == 4) {
							turn++;
						}
					} else {
						if (i == 3) {
							turn++;
						}
					}

					isVisit[y][x][i] = true;
					q.add(new yx(y, x, i, cnt + turn));
				}
			}
		}
	}

//	static void dfs(int crntY, int crntX, int next) {
//
//		isVisit[crntY][crntX] = next;
//		board[crntY][crntX] = true;
//
//		if (crntY == dest[0] && crntX == dest[1]) {
//
//			// 방문길
//			// System.out.println("방문 길");
////			for (int i = 1; i <= row; i++) {
////				for (int j = 1; j <= col; j++) {
////					if (isVisit[i][j] != 0)
////						System.out.print(isVisit[i][j] + " ");
////					else
////						System.out.print(0 + " ");
////				}
////				System.out.println("");
////			}
////			System.out.println("");
//			goK(next);
////			System.out.println(minSum);
//			return;
//		}
//		for (int i = 1; i <= 4; i++) {
//			int nextY = crntY + dydx[i][0];
//			int nextX = crntX + dydx[i][1];
//			if (!board[nextY][nextX]) {
//
////				 현재 상황
////				System.out.println("현재 상황");
////				for (int k = 1; k <= row; k++) {
////					for (int j = 1; j <= col; j++) {
////						if (board[k][j])
////							System.out.print(1 + " ");
////						else
////							System.out.print(0 + " ");
////					}
////					System.out.println("");
////				}
////				System.out.println("");
//
////				System.out.println(nextY+" "+nextX);
//				dfs(nextY, nextX, next + 1);
//				isVisit[nextY][nextX] = 0;
//				board[nextY][nextX] = false;
//
//			}
//		}
//	}

	public static void main(String[] args) throws IOException {
		Reader s = new Reader();
		row = s.nextInt();
		col = s.nextInt();
		board = new int[row + 2][col + 2];
		isVisit = new boolean[row + 2][col + 2][5];
		crntRobot = new int[3];
		for (int i = 0; i <= row + 1; i++) {
			if (i == 0 || i == row + 1) {
				Arrays.fill(board[i], 1);
				continue;
			}

			for (int j = 0; j <= col + 1; j++) {
				if (j == 0 || j == col + 1)
					board[i][j] = 1;
				else
					board[i][j] = s.nextInt();

			}
		}
		robot[0] = s.nextInt();
		robot[1] = s.nextInt();
		robot[2] = s.nextInt();

		dest[0] = s.nextInt();
		dest[1] = s.nextInt();
		dest[2] = s.nextInt();
//		isVisit[crntRobot[0]][crntRobot[1]] = true;

//		for (int i = 1; i <= row; i++) {
//			for (int j = 1; j <= col; j++) {
//				if (board[i][j])
//					System.out.print(1 + " ");
//				else
//					System.out.print(0 + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("");

		bfs();
	}
}
