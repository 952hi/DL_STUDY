package _0801.ChungLee;

import java.io.*;
import java.util.*;
//1. 테이블의 조각을 측적
//2. 측정한 각 조각을 순서대로 게임 보드의 0인 칸에 대입
//3. 0인 칸에 4회전한 값을 넣고 값이 들어가면 다음 0인 칸에 다음 조각을 시도
//4. 0에 조각을 대입하지 않는 경우도 고려.

//각 칸에 모든 조각을 4회전 한 값을 집어 넣는 시도 + 아무것도 넣지 않을 때
//백트래킹방식으로 구현

//핵심 : 조각 회전을 어떻게 구현할지 => 검사 진행 방향을 여러 방향으로

//가지치기 : 각 게임보드 빈칸 너비, 높이 기록 후 해당 값보다 큰 퍼즐 조각은 검사하지 않기 => X, 하나의 빈칸에 여러 값이 들어갈 수 있음

public class PG_퍼즐조각채우기 {

	static class Solution {

		static int[][] tbPz = new int[625][5];
		static int tbCnt = 0, gbCnt = 0;
		static int[][] gbPz = new int[625][5];
		static int[][] Sgame_board;
		static int[][] Stable;

		// 정 비교 좌상 시작
		static boolean square1(int gbNum, int tbNum) {
			boolean isSame = true;
			for (int i = 0; i < tbPz[tbNum][3]; i++) {
				for (int j = 0; j < tbPz[gbNum][4]; j++) {
					if (Sgame_board[gbPz[gbNum][0] + i][gbPz[gbNum][1] + j] != Stable[tbPz[tbNum][0] + i][tbPz[tbNum][1]
							+ j]) {
						isSame = false;
						break;
					}
				}
				if (!isSame)
					break;
			}
			return isSame;
		}

		// 정 비교 우상 시작
		static boolean square2() {

		}

		// 정 비교 우하 시작
		static boolean square3() {

		}

		// 정 비교 좌하 시작
		static boolean square4() {

		}

		static void dfs() {
			// 게임 보드의 빈칸에 퍼즐이 들어갈 수 있는지 검사
			for (int i = 0; i < gbCnt; i++) {

				for (int j = 0; j < tbCnt; j++) {
					// 사용된 퍼즐인 경우
					if (tbPz[j][2] == 0)
						continue;

					// 갯수가 맞지 않으면 넣을 수 없음
					if (gbPz[i][2] != tbPz[j][2])
						continue;

					// 둘 다 길이가 같은 정사각형일 때
					if (gbPz[i][3] == gbPz[i][4] && gbPz[i][4] == tbPz[j][3] && tbPz[j][3] == tbPz[j][4]) {

					}
					// 세로 길이, 가로 길이가 같은 직사각형
					else if (gbPz[i][3] == tbPz[j][3] && gbPz[i][4] == tbPz[j][4]) {

					}
					// 세로 가로 같고 가로 세로가 같을 때
					else if (gbPz[i][3] == tbPz[j][4] && gbPz[i][4] == tbPz[j][3]) {

					}
				}
			}
		}

		public int solution(int[][] game_board, int[][] table) {
			Sgame_board = game_board;
			Stable = table;
			int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

			Queue<int[]> q = new LinkedList<>();
			Queue<int[]> pzq = new LinkedList<>();
			int size = game_board.length, crntY, crntX, nextY, nextX, maxX, maxY, minX, minY, cnt = 0, pzCnt = 0;
			int[] crnt;

			// 검사 확인 위해
			boolean[][] visit = new boolean[size][size];

			// 50 * 50 배열에는 최대 625개 존재 가능
			boolean[][][] emptyGameBoard = new boolean[625][][];

			// 게임 보드, 테이블의 각 빈칸의 최소 y,x, 크기, width,height 저장

			// 테이블에 각각의 조각을 저장
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// 빈칸이라면 패스
					if (table[i][j] == 0) {
						visit[i][j] = true;
						continue;
					}
					pzCnt = 0;
					// 빈칸이 아니기 때문에 연결된 조각 검사
					q.add(new int[] { i, j });
					visit[i][j] = true;
					minY = maxY = i;
					minX = maxX = j;

					// bfs로 확인
					while (!q.isEmpty()) {
						crnt = q.poll();
						crntY = crnt[0];
						crntX = crnt[1];

						for (int l = 0; l < 4; l++) {
							nextY = crntY + dydx[l][0];
							nextX = crntX + dydx[l][1];

							// 보드를 벗어나면 검사 제외
							if (nextY == size || nextY < 0 || nextX == size || nextX < 0) {
								continue;
							}

							// 이미 방문했다면 검사 제외
							if (visit[nextY][nextX])
								continue;

							// 이어지는 퍼즐조각 존재
							if (table[nextY][nextX] == 1) {
								// 좌상, 우하를 확인해서 해당 퍼즐의 가로, 세로 길이 확인
								maxY = Math.max(maxY, nextY);
								minY = Math.min(minY, nextY);
								maxX = Math.max(maxX, nextX);
								minX = Math.min(minX, nextX);
								visit[nextY][nextX] = true;
								q.add(new int[] { nextY, nextX });
								pzCnt++;
							}
						}
					}
					// 테이블의 각 퍼즐 정보 저장
					tbPz[tbCnt][0] = i;
					tbPz[tbCnt][1] = j;
					tbPz[tbCnt][2] = pzCnt;
					tbPz[tbCnt][3] = maxY - minY;
					tbPz[tbCnt][4] = maxX - minX;
					tbCnt++;
				}
			}
			// 테이블의 퍼즐 추출 완료

			// 게임 테이블에 빈칸 조사
			cnt = 0;
			visit = new boolean[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// 빈칸이라면 패스
					if (game_board[i][j] == 1) {
						visit[i][j] = true;
						continue;
					}

					pzCnt = 0;
					// 빈칸이 아니기 때문에 연결된 조각 검사
					q.add(new int[] { i, j });
					visit[i][j] = true;
					minY = maxY = i;
					minX = maxX = j;

					// bfs로 확인
					while (!q.isEmpty()) {
						crnt = q.poll();
						crntY = crnt[0];
						crntX = crnt[1];

						for (int l = 0; l < 4; l++) {
							nextY = crntY + dydx[l][0];
							nextX = crntX + dydx[l][1];

							// 보드를 벗어나면 검사 제외
							if (nextY == size || nextY < 0 || nextX == size || nextX < 0) {
								continue;
							}

							// 이미 방문했다면 검사 제외
							if (visit[nextY][nextX])
								continue;

							// 이어지는 퍼즐조각 존재
							if (game_board[nextY][nextX] == 0) {
								// 좌상, 우하를 확인해서 해당 퍼즐의 가로, 세로 길이 확인
								maxY = Math.max(maxY, nextY);
								minY = Math.min(minY, nextY);
								maxX = Math.max(maxX, nextX);
								minX = Math.min(minX, nextX);
								visit[nextY][nextX] = true;
								q.add(new int[] { nextY, nextX });
								pzCnt++;
							}
						}
					}
					gbPz[gbCnt][0] = i;
					gbPz[gbCnt][1] = j;
					gbPz[gbCnt][2] = pzCnt;
					gbPz[gbCnt][3] = maxY - minY;
					gbPz[gbCnt][4] = maxX - minX;
					gbCnt++;
				}
			}
			// 게임 보드의 빈칸 데이터 조사 완료

			int answer = -1;
			return emptyGameBoard;
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		int[][] game_board = new int[][] { //
				{ 1, 1, 0, 0, 1, 0 }, //
				{ 0, 0, 1, 0, 1, 0 }, //
				{ 0, 1, 1, 0, 0, 1 }, //
				{ 1, 1, 0, 1, 1, 1 }, //
				{ 1, 0, 0, 0, 1, 0 }, //
				{ 0, 1, 1, 1, 0, 0 } //
		};
		int[][] table = new int[][] { //
				{ 1, 0, 0, 1, 1, 0 }, //
				{ 1, 0, 1, 0, 1, 0 }, //
				{ 0, 1, 1, 0, 1, 1 }, //
				{ 0, 0, 1, 0, 0, 0 }, //
				{ 1, 1, 0, 1, 1, 0 }, //
				{ 0, 1, 0, 0, 0, 0 } };

		s.solution(game_board, table);

	}
}
