package _0606.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_17472_G1_다리만들기2 {
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

	static int[][] bd;
	static boolean[][] visit;
	static int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] set;

	static int FindSet(int a) {
		if (set[a] < 0)
			return a;
		return set[a] = FindSet(set[a]);
	}

	static boolean UnionSet(int a, int b) {
		a = FindSet(a);
		b = FindSet(b);

		if (a == b)
			return false;
		if (set[a] < set[b]) {
			set[a] += set[b];
			set[b] = a;
		} else {
			set[b] += set[a];
			set[a] = b;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		int row = br.nextInt();
		int col = br.nextInt();
		int islandCnt = 2, crntX, crntY, nextX, nextY;
		int[][] landPos = new int[6][2];
		int[] crnt;
		bd = new int[row + 2][col + 2];
		visit = new boolean[row + 2][col + 2];
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				bd[i][j] = br.nextInt();
			}
		}
		Arrays.fill(bd[0], 9);
		Arrays.fill(bd[row + 1], 9);

		for (int i = 0; i <= row + 1; i++) {
			bd[i][0] = 9;
			bd[i][col + 1] = 9;
		}

		// 각 섬을 구분 짓기 위해 bfs로 각 섬을 숫자로 구분
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (bd[i][j] == 1) {
					landPos[islandCnt - 2][0] = i;
					landPos[islandCnt - 2][1] = j;

					q.add(new int[] { i, j });
					visit[i][j] = true;
					bd[i][j] = islandCnt;
					while (!q.isEmpty()) {
						crnt = q.poll();
						crntY = crnt[0];
						crntX = crnt[1];

						for (int k = 0; k < 4; k++) {
							nextY = crntY + dydx[k][0];
							nextX = crntX + dydx[k][1];

							if (bd[nextY][nextX] == 9)
								continue;
							if (visit[nextY][nextX])
								continue;
							if (bd[nextY][nextX] == 1) {
								q.add(new int[] { nextY, nextX });
								bd[nextY][nextX] = islandCnt;
								visit[nextY][nextX] = true;
							}
						}
					}
					islandCnt++;
				}
			}
		}
		
		int[][] islandConn = new int[islandCnt][islandCnt];
		for (int i = 0; i < islandCnt; i++) {
			Arrays.fill(islandConn[i], 11);
		}
		set = new int[islandCnt];
		Arrays.fill(set, -1);
		int crntV, nextV, distCnt = 0;
		visit = new boolean[row + 2][col + 2];

		// MST를 위해 가장 짧은 다리부터 찾아주기 위해 우선순위 큐 생성
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((int[] o1, int[] o2) -> (o1[2] - o2[2]));

		for (int i = 0; i < 6; i++) {
			if (landPos[i][0] != 0) {
				q.add(new int[] { landPos[i][0], landPos[i][1] });
				visit[landPos[i][0]][landPos[i][1]] = true;
				while (!q.isEmpty()) {
					crnt = q.poll();
					crntY = crnt[0];
					crntX = crnt[1];
					crntV = bd[crntY][crntX];
					for (int k = 0; k < 4; k++) {
						nextY = crntY + dydx[k][0];
						nextX = crntX + dydx[k][1];
						nextV = bd[nextY][nextX];
						if (nextV == 9)
							continue;
						else if (visit[nextY][nextX])
							continue;

						// 현재 섬과 같은 번호일 경우 Q에 저장
						else if (nextV == crntV) {
							q.add(new int[] { nextY, nextX });
							visit[nextY][nextX] = true;
						}

						// 바다일 경우
						else if (nextV == 0) {
							distCnt = 1;
							while (true) {
								nextY = nextY + dydx[k][0];
								nextX = nextX + dydx[k][1];
								if (bd[nextY][nextX] == 9)
									break;
								else if (bd[nextY][nextX] == 0) {
									distCnt++;
								}
								// 섬일 때
								else {
									// 같은 섬이거나 거리가 1이면 탈출
									if (bd[nextY][nextX] == crntV || distCnt == 1)
										break;

									// 저장된 거리보다 더 짧은 값으로만 최신화 후 저장
									if (islandConn[crntV][bd[nextY][nextX]] > distCnt) {
										pq.add(new int[] { crntV, bd[nextY][nextX], distCnt });
										islandConn[crntV][bd[nextY][nextX]] = distCnt;
										islandConn[bd[nextY][nextX]][crntV] = distCnt;
									}
									break;
								}
							}
						}
					}
				}
			} else
				break;
		}

		int ans = 0;

		// pq에 저장된 모든 값들을 연결해주고 연결되었을 때 다리의 길이를 저장
		while (!pq.isEmpty()) {
			crnt = pq.poll();
			if (UnionSet(crnt[0], crnt[1])) {
				ans += crnt[2];
			}
		}
		int connCnt = 0;

		// 결합한 뒤 마이너스값(정점)이 2개 이상있다면 모두 연결된 것이 아니기 때문에 -1 출력
		for (int i = 2; i < islandCnt; i++) {
			if (set[i] < 0) {
				connCnt++;
				if (connCnt == 2) {
					System.out.println(-1);
					return;
				}

			}
		}
		// 결과가 0일 때는 -1을 출력하고 아니면 그대로 출력
		System.out.println(ans = (ans == 0) ? -1 : ans);
	}
}
