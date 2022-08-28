package _0826.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1917_정육면체전개도 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int[][] bd = new int[6][6];

		boolean[][] isVist = new boolean[6][6];

		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int[][][] eachDiceNum = new int[][][] { //
				{ { -1, 1, -1 }, { 4, 0, 2 }, { -1, 3, -1 } }, //
				{ { -1, 5, -1 }, { 4, 1, 2 }, { -1, 0, -1 } }, //
				{ { -1, 5, -1 }, { 1, 2, 3 }, { -1, 0, -1 } }, //
				{ { -1, 5, -1 }, { 2, 3, 4 }, { -1, 0, -1 } }, //
				{ { -1, 5, -1 }, { 3, 4, 1 }, { -1, 0, -1 } }, //
				{ { -1, 3, -1 }, { 4, 5, 2 }, { -1, 1, -1 } } };

		Queue<int[]> q = new LinkedList<int[]>();
		char a;
		// 다이스 중복 검사
		boolean[] dice = new boolean[6];
		int tmp = 0, dir = 0, cnt = 0, y = 0, x = 0, crntY = 0, crntX = 0, nextY = 0, nextX = 0, pos = 0, nextPos = 0;
		int[] tmpYX;
		boolean isDice = true;
		// 3번 나눠서 진행
		for (int l = 0; l < 3; l++) {
			bd = new int[6][6];
			isVist = new boolean[6][6];
			dice = new boolean[6];
			isDice = true;
			// 6행
			for (int i = 0; i < 6; i++) {
				st = new StringTokenizer(br.readLine());
				// 6열
				for (int j = 0; j < 6; j++) {
					bd[i][j] = -1;
					if (st.nextToken().equals("1")) {
						bd[i][j] = 0;
						// 마지막 1 위치 저장
						y = i;
						x = j;
					}
				}
			}

			q.add(new int[] { y, x, 0 });
			dice[0] = true;
			isVist[y][x] = true;
			cnt = 0;
			bd[y][x] = 0;
			while (!q.isEmpty()) {
				tmpYX = q.poll();
				crntY = tmpYX[0];
				crntX = tmpYX[1];
				pos = tmpYX[2];
				cnt++;
				dir = 0;

				// 기준점을 발견하기 위해 사용
				// 2번째 방문 위치부터 사용
				if (cnt != 1) {
					// 4방 탐색하며 이전에 방문한 이력이 있는지 확인
					LOOP: for (int i = 0; i < 4; i++) {
						nextY = crntY + dydx[i][0];
						nextX = crntX + dydx[i][1];

						// 갈 수 없는 경우, 방문하지 않은 경우, 주사위가 놓이지 않은 경우 제외
						if (nextY == -1 || nextY == 6 || nextX == -1 || nextX == 6 || !isVist[nextY][nextX]
								|| bd[nextY][nextX] == -1)
							continue;
						nextPos = bd[nextY][nextX];

						// i 는 방향
						// nextPos는 해당 방향에 저장된 숫자

						for (int j = 0; j < 4; j++) {
							// 사방탐색하며 같은 숫자를 통해 방향성을 발견해야 함
							if (eachDiceNum[pos][1 + dydx[j][0]][1 + dydx[j][1]] == nextPos) {
								// j는 해당 숫자 기준으로 방향성을 탐색
								//
								dir = j - i;

								break LOOP;
							}
						}
					}
				}
				// 상, 주, 하, 좌
				for (int i = 0; i < 4; i++) {
					nextY = crntY + dydx[i][0];
					nextX = crntX + dydx[i][1];

					// 이미 방문했다면 or 방문할 수 없다면
					if (nextY == -1 || nextY == 6 || nextX == -1 || nextX == 6 || isVist[nextY][nextX]
							|| bd[nextY][nextX] == -1)
						continue;

					// 다음 자리에 놓일 수 있는 수를 탐색
					// 상
					tmp = i;
					tmp += dir;
					tmp = (tmp + 4) % 4;
					
					if (tmp == 0) {
						if (pos == 0) {
							nextPos = 1;
						} else if (pos == 1) {
							nextPos = 5;
						} else if (pos == 2) {
							nextPos = 5;
						} else if (pos == 3) {
							nextPos = 5;
						} else if (pos == 4) {
							nextPos = 5;
						} else {
							nextPos = 3;
						}
					}
					// 우
					else if (tmp == 1) {
						if (pos == 0) {
							nextPos = 2;
						} else if (pos == 1) {
							nextPos = 2;
						} else if (pos == 2) {
							nextPos = 3;
						} else if (pos == 3) {
							nextPos = 4;
						} else if (pos == 4) {
							nextPos = 1;
						} else {
							nextPos = 2;
						}
					}
					// 하
					else if (tmp == 2) {
						if (pos == 0) {
							nextPos = 3;
						} else if (pos == 1) {
							nextPos = 0;
						} else if (pos == 2) {
							nextPos = 0;
						} else if (pos == 3) {
							nextPos = 0;
						} else if (pos == 4) {
							nextPos = 0;
						} else {
							nextPos = 1;
						}
					}
					// 좌
					else {
						if (pos == 0) {
							nextPos = 4;
						} else if (pos == 1) {
							nextPos = 4;
						} else if (pos == 2) {
							nextPos = 1;
						} else if (pos == 3) {
							nextPos = 2;
						} else if (pos == 4) {
							nextPos = 3;
						} else {
							nextPos = 4;
						}
					}

					// 중복될 시 중단
					if (dice[nextPos]) {
						isDice = false;
						break;
					}

					dice[nextPos] = true;
					if (!isVist[nextY][nextX]) {
						bd[nextY][nextX] = nextPos;
						isVist[nextY][nextX] = true;
						q.add(new int[] { nextY, nextX, nextPos });
					}

				}
			}
			if (isDice)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
