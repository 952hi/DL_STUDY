import java.util.*;
import java.io.*;


public class BOJ_1113_G1_수영장만들기 {
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
			byte c;
			byte[] tmp = new byte[bfs];
			while ((c = read()) != ' ') {
				if (c == '\n' || c == '\r') {
					if (cnt != 0)
						break;
					else
						continue;
				}
				tmp[cnt++] = c;
			}
			return new String(tmp, 0, cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		Queue<int[]> q = new LinkedList<>();
		int row = br.nextInt();
		int col = br.nextInt();

		int crntY, crntX, nextY, nextX, cnt = 0, fstLv, ans = 0, ceilHeight;
		int[] crntQ;
		int[][] bd = new int[row][col],
				// 상, 우, 하, 좌
				dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		char[] each;

		//방문검사 배열을 2개 생성
		//1. 수영장 물을 넣었거나 아예 검사 불가능한 타일을 저장하는 배열
		boolean[][] visitPerma = new boolean[row][col];
		//2. 새로운 물을 넣기 위해 매번 초기화되는 방문 배열
		boolean[][] visitTmp = new boolean[row][col];
		//더 낮은 위치가 존재하거나 외곽과 맡닿아있는지 확인
		boolean isOuter;
		//데이터 입력
		for (int i = 0; i < row; i++) {
			each = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				bd[i][j] = each[j] - '0';
			}
		}

		//모든 노드 순회하며 검사
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				
				//이전에 처리한 노드면 검사 X
				if (visitPerma[i][j])
					continue;
				//해당 위치에서 더 이상 물을 넣을 수 없을 때까지 반복해서 물을 넣음
				while (true) {
					// 현재 지역보다 높은 땅 중 가장 낮은 값을 구하기 위해
					ceilHeight = Integer.MAX_VALUE;

					// 외곽지역 닿은지 알 수 없기 때문에 초기화
					isOuter = false;

					// 시작 위치 트루
					visitTmp[i][j] = true;
					// 물을 채웠다면 높이가 계속 변하기 때문에 최신 값
					fstLv = bd[i][j];
					q.add(new int[] { i, j });

					// 한 번에 몇개가 체크가 되었는지 확인
					cnt = 1;
					// bfs로 가장 낮은 땅부터 한줄씩 물을 다 깔아봄
					Outer: while (!q.isEmpty()) {
						crntQ = q.poll();
						crntY = crntQ[0];
						crntX = crntQ[1];

						// 네방향
						for (int l = 0; l < 4; l++) {
							nextY = crntY + dydx[l][0];
							nextX = crntX + dydx[l][1];

							// 외곽과 닿아있는 부분
							if (nextY < 0 || nextY == row || nextX < 0 || nextX == col) {
								isOuter = true;
								break Outer;
							}

							// 방문을 했다면 건너띔
							else if (visitTmp[nextY][nextX])
								continue;

							// 첫 검사자리보다 더 낮은 자리라면 물이 내려가기때문에 탈출
							else if (bd[nextY][nextX] < fstLv) {
								isOuter = true;
								break Outer;
							}

							// 나보다 높은 땅인데
							else if (bd[nextY][nextX] > fstLv) {
								// 그중 가장 낮은 땅인 경우
								if (ceilHeight > bd[nextY][nextX])
									ceilHeight = bd[nextY][nextX];
								continue;
							}

							// 나랑 높이 같은 땅을 카운팅
							cnt++;
							// 방문처리
							visitTmp[nextY][nextX] = true;
							q.add(new int[] { nextY, nextX });
						}
					}

					// 외곽과 닿아있거나 더 낮은 자리가 존재한다면 다음 땅 검사
					if (isOuter) {
						q.clear();
						for (int j2 = 0; j2 < row; j2++) {
							for (int k = 0; k < col; k++) {
								if (visitTmp[j2][k]) {
									visitTmp[j2][k] = false;
									visitPerma[j2][k] = true;
								}

							}
						}
						break;
					}

					// 방문한 땅(같은 높이)의 높이를 증가
					for (int j2 = 0; j2 < row; j2++) {
						for (int k = 0; k < col; k++) {
							if (visitTmp[j2][k]) {
								bd[j2][k] += (ceilHeight - fstLv);
								visitTmp[j2][k] = false;
								ans += (ceilHeight - fstLv);
								visitPerma[j2][k] = true;
							}
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}