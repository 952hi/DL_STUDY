package _220512.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class BOJ_1194_G1_달이차오른다가자 {
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

		char nextChar() throws IOException {
			byte c = read();
			while (c <= ' ')
				c = read();
			return (char) c;
		}
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		int row = br.nextInt();
		int col = br.nextInt();
		int[][] dydx = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int[][][] check = new int[row + 2][col + 2][64];
		char[][] bd = new char[row + 2][col + 2];
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[2] - o2[2]);
		// 0 : 시작, 1 : 도착
		int[][] startendYX = new int[2][2];

		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bd[i][j] = br.nextChar();
				if (bd[i][j] == '0') {
					startendYX[0][0] = i;
					startendYX[0][1] = j;
					bd[i][j] = '.';
				} else if (bd[i][j] == '1') {
					startendYX[1][0] = i;
					startendYX[1][1] = j;
				}
			}
		}

		check[startendYX[0][0]][startendYX[0][1]][0] = 1;
		// 초기 상태 : 시작점, 0칸 이동, 0개 열쇠
		pq.add(new int[] { startendYX[0][0], startendYX[0][1], 1, 0 });
		int[] crnt;
		int crntY, crntX, crntMoved, crntKeyState, nextY, nextX, nextKeyState;
		while (!pq.isEmpty()) {
			crnt = pq.poll();
			crntY = crnt[0];
			crntX = crnt[1];
			crntMoved = crnt[2];
			crntKeyState = crnt[3];
			
			if(check[crntY][crntX][crntKeyState] < crntMoved + 1)
				continue;
			
			for (int i = 0; i < 4; i++) {
				nextY = crntY + dydx[i][0];
				nextX = crntX + dydx[i][1];
				// 갈 수 없는 곳 확인
				if (bd[nextY][nextX] == '\u0000' || bd[nextY][nextX] == '#')
					continue;

				// 현재 키값으로 방문을 한 경우
				if (check[nextY][nextX][crntKeyState] != 0)
					continue;

				// 열쇠나 문일 경우, 빈칸일 경우
				if (bd[nextY][nextX] != '1') {
					// 열쇠라면 해당 상태를 저장
					if ('a' <= bd[nextY][nextX] && bd[nextY][nextX] <= 'f') {
						nextKeyState = crntKeyState | (1 << bd[nextY][nextX] - 'a');
						// 0 == 방문하지 않은 상태이므로 바로 입력
						if (check[nextY][nextX][nextKeyState] == 0) {
							check[nextY][nextX][nextKeyState] = crntMoved + 1;
							pq.add(new int[] { nextY, nextX, crntMoved + 1, nextKeyState });
						}
						// 현재에서 한칸 더 전진한 값이 저장된 값보다 더 작을 경우 이 값으로 최신화하고 큐에 집어 넣기
						else if (check[nextY][nextX][nextKeyState] > crntMoved + 1) {
							check[nextY][nextX][nextKeyState] = crntMoved + 1;
							pq.add(new int[] { nextY, nextX, crntMoved + 1, nextKeyState });
						}
					}
					// 문일 때
					else if ('A' <= bd[nextY][nextX] && bd[nextY][nextX] <= 'F') {
						// 해당 문에 해당하는 키가 있을 경우
						if ((crntKeyState & 1 << bd[nextY][nextX] - 'A') > 0) {
							check[nextY][nextX][crntKeyState] = crntMoved + 1;
							pq.add(new int[] { nextY, nextX, crntMoved + 1, crntKeyState });
						}
					}
					// 한 칸 더 전진이 가능할 때
					else if (bd[nextY][nextX] == '.') {
						check[nextY][nextX][crntKeyState] = crntMoved + 1;
						pq.add(new int[] { nextY, nextX, crntMoved + 1, crntKeyState });
					}
				}
				// 열쇠인 경우
				else {
					System.out.println(crntMoved);
					return;
				}
			}
		}
		System.out.println(-1);
	}
}
